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
    validateDates(scholarshipUpdateDTO.getStartDate(),scholarshipUpdateDTO.getFinishDate());
    Scholarship schorlarshipFound=getById(id);
    updateInformation(schorlarshipFound, scholarshipUpdateDTO);
    return scholarshipRepository.save(schorlarshipFound);
  }

  @Override
  public void delete(long id) throws IOException {
    Scholarship scholarshipFound=getById(id);
    imageService.delete(scholarshipFound.getImage().getId());
    scholarshipRepository.delete(scholarshipFound);
  }

  @Override
  public ScholarshipResponse findAll() {
    return null;
  }

  @Override
  public Scholarship changeCourType(Long scholarshipId, Long courseTypeId) {
    return null;
  }

  @Override
  public Scholarship changeCountry(Long scholarshipId, Long countryId) {
    Country countryFound=countryService.findById(countryId);
    Scholarship scholarshipFound=getById(scholarshipId);
    scholarshipFound.setCountry(countryFound);
    return scholarshipRepository.save(scholarshipFound);
  }

  @Override
  public Scholarship changeStatus(Long scholarshipId, Long statusId) {
    return null;
  }

  @Override
  public Scholarship changeLanguage(Long scholarshipId, Long languageId) {
    return null;
  }

  @Override
  public Scholarship changeImage(Long scholarshipId) {
    return null;
  }

  @Override
  public Scholarship addCareer(Long scholarshipId, Long careerId) {
    return null;
  }

  @Override
  public Scholarship removeCareer(Long scholarshipId, Long careerId) {
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

  private void updateInformation(Scholarship schorlarshipFound, ScholarshipUpdateDTO scholarshipUpdateDTO) {
    schorlarshipFound.setTitle(scholarshipUpdateDTO.getTitle().toUpperCase());
    schorlarshipFound.setDescription(scholarshipUpdateDTO.getDescription());
    schorlarshipFound.setLink(scholarshipUpdateDTO.getLink());
    schorlarshipFound.setStartDate(scholarshipUpdateDTO.getStartDate());
    schorlarshipFound.setFinishDate(scholarshipUpdateDTO.getFinishDate());
  }
}
