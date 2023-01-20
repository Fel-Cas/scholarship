package com.api.scholarships.services.implementation;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.*;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.repositories.ScholarshipRepository;
import com.api.scholarships.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScholarshipServiceImp implements ScholarshipService {
  @Autowired
  private ScholarshipRepository scholarshipRepository;
  @Autowired
  private CareerService careerService;
  @Autowired
  private LanguageService languageService;
  @Autowired
  private StatusService service;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private CountryService countryService;
  @Autowired
  private CourseTypeService courseTypeService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private ScholarshipMapper scholarshipMapper;
  private SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd");
  private final String DEFAULT_STATUS="VIGENTE";


  @Override
  public Scholarship create(ScholarshipDTO scholarshipDTO) throws IOException, ParseException {
    validateDates(simpleDateFormat.parse(scholarshipDTO.getStartDate()),
        simpleDateFormat.parse(scholarshipDTO.getFinishDate()));
    List<Career> careers=loadCareers(scholarshipDTO.getCareers());
    return scholarshipRepository.save(
        Scholarship.builder()
            .title(scholarshipDTO.getTitle().toUpperCase())
            .description(scholarshipDTO.getDescription())
            .startDate(simpleDateFormat.parse(scholarshipDTO.getStartDate()))
            .finishDate(simpleDateFormat.parse(scholarshipDTO.getFinishDate()))
            .link(scholarshipDTO.getLink())
            .courseType(courseTypeService.findByCourseType(scholarshipDTO.getCourseType()))
            .country(countryService.findByName(scholarshipDTO.getCountry()))
            .status(service.findByName(DEFAULT_STATUS))
            .language(languageService.findByName(scholarshipDTO.getLanguage()))
            .image(imageService.save(scholarshipDTO.getImage()))
            .company(companyService.getOne(scholarshipDTO.getCompany()))
            .careers(careers)
            .build()
    );
  }

  @Override
  public Scholarship getById(long id) {
    Optional<Scholarship> scholarshipFound=scholarshipRepository.findById(id);
    if(scholarshipFound.isEmpty()){
      throw new NotFoundException(Messages.MESSAGE_SCHOLARSHIP_NOT_FOUND.formatted(id));
    }
    return scholarshipFound.get();
  }

  @Override
  public Scholarship update(ScholarshipUpdateDTO scholarshipUpdateDTO, long id) {
    return null;
  }

  @Override
  public void delete(long id) {

  }

  @Override
  public ScholarshipResponse findAll() {
    return null;
  }

  private void validateDates(Date startDate, Date finishDate) {
    if(!startDate.before(finishDate)){
      throw new BadRequestException(Messages.MESSAGE_WRONG_DATES);
    }
  }

  private List<Career> loadCareers(List<String> careers) {
    return new HashSet<>(careers)
        .stream().map(career->careerService.findByName(career))
        .collect(Collectors.toList());
  }
}
