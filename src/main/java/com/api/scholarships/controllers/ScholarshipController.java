package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.constants.PaginationRequest;
import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.dtos.ScholarshipResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.exceptions.BadRequestException;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.services.interfaces.ScholarshipService;
import com.api.scholarships.services.strategyScholarships.ScholarshipType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping(Endpoints.SCHOLARSHIPS)
public class ScholarshipController {
  @Autowired
  private ScholarshipService scholarshipService;
  @Autowired
  private ScholarshipMapper scholarshipMapper;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> create(@Valid @ModelAttribute ScholarshipDTO scholarshipDTO) throws IOException, ParseException {
    Scholarship scholarshipSaved=scholarshipService.create(scholarshipDTO);
    return new ResponseEntity<>(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipSaved), HttpStatus.CREATED);
  }

  @GetMapping(Endpoints.ID)
  public ResponseEntity<ScholarshipDTOResponse> findById(@PathVariable("id") long id) {
    Scholarship scholarshipFound=scholarshipService.getById(id);
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipFound));
  }

  @PutMapping(Endpoints.ID)
  public ResponseEntity<ScholarshipDTOResponse> update(@PathVariable("id") long id, @Valid @RequestBody ScholarshipUpdateDTO scholarshipUpdateDTO){
    Scholarship scholarshipUpdated=scholarshipService.update(scholarshipUpdateDTO, id);
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @DeleteMapping(Endpoints.ID)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> delete(@PathVariable("id") long id) throws IOException {
    scholarshipService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_COUNTRY)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")

  public ResponseEntity<ScholarshipDTOResponse> updateCountry(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("countryId") Long countryId) {
    Scholarship scholarshipUpdated=scholarshipService.changeCountry(scholarshipId, countryId);
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_COURSE_TYPE)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> updateCourseType(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("courseTypeId") Long courseTypeId) {
    Scholarship scholarshipUpdated=scholarshipService.changeCourseType(scholarshipId, courseTypeId);
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_STATUS)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> updateStatus(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("statusId") Long statusId) {
     Scholarship scholarshipUpdated=scholarshipService.changeStatus(scholarshipId, statusId);
     return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_LANGUAGE)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> updateLanguage(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("languageId") Long languageId) {
     Scholarship scholarshipUpdated=scholarshipService.changeLanguage(scholarshipId, languageId);
     return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_IMAGE)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> updateImage(@PathVariable("scholarshipId") Long scholarshipId, @RequestParam("image")MultipartFile image) throws IOException {
    Scholarship scholarshipUpdated=scholarshipService.changeImage(scholarshipId, image);
    return  ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_CAREER)
  @PreAuthorize("hasRole('ROLE_LEGAL_REPRESENTATIVE')")
  public ResponseEntity<ScholarshipDTOResponse> updateCareers(
      @PathVariable("scholarshipId") Long scholarshipId,
      @PathVariable("careerId") Long careerId,
      @RequestParam(value = "action") String action
  ){
    Scholarship scholarshipUpdated;
    switch (action){
      case "ADD":
        scholarshipUpdated=scholarshipService.addCareer(scholarshipId, careerId);
        break;
      case "REMOVE":
        scholarshipUpdated=scholarshipService.removeCareer(scholarshipId, careerId);
        break;
      default:
        throw new BadRequestException("That action %s is no valid".formatted(action));

    }
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @GetMapping()
  public ResponseEntity<ScholarshipResponse> getAll(
    @RequestParam(name="numberPage", defaultValue = PaginationRequest.DEFAULT_NUMBER_PAGE,required = false) int page,
    @RequestParam(name="pageSize", defaultValue = PaginationRequest.DEFAULT_PAGE_SIZE,required = false) int pageSize,
    @RequestParam(name="sort",defaultValue = PaginationRequest.DEFAULT_SORT_BY,required = false) String sort,
    @RequestParam(name = "order", defaultValue = PaginationRequest.DEFAULT_SORT_DIR,required = false) String order,
    @RequestParam(name = "modelCondition", defaultValue=PaginationRequest.DEFAULT_MODEL_CONDITION , required = false) ScholarshipType modelCondition,
    @RequestParam(name = "id", defaultValue =PaginationRequest.DEFAULT_ID, required = false) Long id
  ){
    return ResponseEntity.ok(scholarshipService.findAll(page,pageSize,sort,order,modelCondition,id));
  }
}
