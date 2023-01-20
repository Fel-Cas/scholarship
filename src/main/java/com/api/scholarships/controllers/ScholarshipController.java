package com.api.scholarships.controllers;

import com.api.scholarships.constants.Endpoints;
import com.api.scholarships.dtos.ScholarshipDTO;
import com.api.scholarships.dtos.ScholarshipDTOResponse;
import com.api.scholarships.dtos.ScholarshipUpdateDTO;
import com.api.scholarships.entities.Scholarship;
import com.api.scholarships.mappers.ScholarshipMapper;
import com.api.scholarships.services.interfaces.ScholarshipService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<ScholarshipDTOResponse> delete(@PathVariable("id") long id) throws IOException {
    scholarshipService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_COUNTRY)
  public ResponseEntity<ScholarshipDTOResponse> updateCountry(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("countryId") Long countryId) {
    Scholarship scholarshipUpdated=scholarshipService.changeCountry(scholarshipId, countryId);
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_COURSE_TYPE)
  public ResponseEntity<ScholarshipDTOResponse> updateCourseType(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("courseTypeId") Long courseTypeId) {
    Scholarship scholarshipUpdated=scholarshipService.changeCourseType(scholarshipId, courseTypeId);
    return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_STATUS)
  public ResponseEntity<ScholarshipDTOResponse> updateStatus(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("statusId") Long statusId) {
     Scholarship scholarshipUpdated=scholarshipService.changeStatus(scholarshipId, statusId);
     return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_LANGUAGE)
  public ResponseEntity<ScholarshipDTOResponse> updateLanguage(@PathVariable("scholarshipId") Long scholarshipId, @PathVariable("languageId") Long languageId) {
     Scholarship scholarshipUpdated=scholarshipService.changeLanguage(scholarshipId, languageId);
     return ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }

  @PutMapping(Endpoints.SCHOLARSHIPS_IMAGE)
  public ResponseEntity<ScholarshipDTOResponse> updateImage(@PathVariable("scholarshipId") Long scholarshipId, @RequestParam("image")MultipartFile image) throws IOException {
    Scholarship scholarshipUpdated=scholarshipService.changeImage(scholarshipId, image);
    return  ResponseEntity.ok(scholarshipMapper.scholarshipToScholarshipDTOResponse(scholarshipUpdated));
  }
}
