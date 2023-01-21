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
import com.api.scholarships.services.strategyScholarships.ScholarshipContext;
import com.api.scholarships.services.strategyScholarships.ScholarshipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
  private StatusService statusService;
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
  @Autowired
  private ScholarshipContext scholarshipContext;
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
            .status(statusService.findByName(DEFAULT_STATUS))
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
  public ScholarshipResponse findAll(int page, int size, String sort, String order, ScholarshipType modelCondition, Long idCondition ) {
    Sort sortDirection=order.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sort).ascending() : Sort.by(sort).descending();
    Pageable pageable= PageRequest.of(page,size,sortDirection);

    Page<Scholarship> scholarshipsFound=scholarshipContext
        .getScholarshipStrategy(modelCondition)
        .findScholarshipsByCondition(pageable,idCondition);

    return ScholarshipResponse.builder()
        .content(scholarshipMapper.scholarshipListToScholarshipDTOResponseList(scholarshipsFound.getContent()))
        .totalPages(scholarshipsFound.getTotalPages())
        .totalElements(scholarshipsFound.getTotalElements())
        .lastOne(scholarshipsFound.isLast())
        .numberPage(scholarshipsFound.getNumber())
        .sizePage(scholarshipsFound.getSize())
        .build();
  }

  @Override
  public Scholarship changeCourseType(Long scholarshipId, Long courseTypeId) {
    CourseType courseTypeFound=courseTypeService.findById(courseTypeId);
    Scholarship scholarshipFound=getById(scholarshipId);
    scholarshipFound.setCourseType(courseTypeFound);
    return scholarshipRepository.save(scholarshipFound);
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
    Status statusFound=statusService.findById(statusId);
    Scholarship scholarshipFound=getById(scholarshipId);
    scholarshipFound.setStatus(statusFound);
    return scholarshipRepository.save(scholarshipFound);
  }

  @Override
  public Scholarship changeLanguage(Long scholarshipId, Long languageId) {
    Language languageFound=languageService.findById(languageId);
    Scholarship scholarshipFound=getById(scholarshipId);
    scholarshipFound.setLanguage(languageFound);
    return scholarshipRepository.save(scholarshipFound);
  }

  @Override
  public Scholarship changeImage(Long scholarshipId, MultipartFile image) throws IOException {
    Scholarship scholarshipFound=getById(scholarshipId);
    imageService.delete(scholarshipFound.getImage().getId());
    Image imageSaved=imageService.save(image);
    scholarshipFound.setImage(imageSaved);
    return scholarshipRepository.save(scholarshipFound);
  }

  @Override
  public Scholarship addCareer(Long scholarshipId, Long careerId) {
    Scholarship scholarshipFound=getById(scholarshipId);
    Career careerFound=careerService.findById(careerId);
    if(scholarshipFound.getCareers().contains(careerFound)){
      throw new BadRequestException(Messages.MESSAGE_DUPLICATE_CAREER.formatted(careerFound.getCareerName()));
    }
    scholarshipFound.getCareers().add(careerFound);
    return scholarshipRepository.save(scholarshipFound);
  }

  @Override
  public Scholarship removeCareer(Long scholarshipId, Long careerId) {
    Scholarship scholarshipFound=getById(scholarshipId);
    if(scholarshipFound.getCareers().size()==1){
      throw new BadRequestException(Messages.MESSAGE_CANNOT_REMOVE_CAREER);
    }
    Career careerFound=careerService.findById(careerId);
    if(!scholarshipFound.getCareers().contains(careerFound)){
      throw new BadRequestException(Messages.MESSAGE_REMOVE_NO_ASSOCIATE_CAREER.formatted(careerFound.getCareerName(),scholarshipId));
    }
    scholarshipFound.getCareers().remove(careerFound);
    return scholarshipRepository.save(scholarshipFound);
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
