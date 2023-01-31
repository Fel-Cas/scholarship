package com.api.scholarships.repositories;

import com.api.scholarships.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ScholarshipRepository extends JpaRepository<Scholarship, Long> {
  Page<Scholarship> findByCourseType(CourseType courseType, Pageable pageable);
  Page<Scholarship> findByCountry(Country country, Pageable pageable);
  Page<Scholarship> findByStatus(Status status, Pageable pageable);
  Page<Scholarship> findByLanguage(Language language, Pageable pageable);
  Page<Scholarship> findByCompany(Company company, Pageable pageable);
  Page<Scholarship> findByCareers(Career career, Pageable pageable);
  @Procedure("update_scholarships_status")
  void updateScholarshipStatus();
  Page<Scholarship> findByStatusAndCareers(Status status, Career career, Pageable pageable);
  Page<Scholarship> findByStatusAndCompany(Status status, Company company, Pageable pageable);
  Page<Scholarship> findByStatusAndCountry(Status status, Country country, Pageable pageable);
  Page<Scholarship> findByStatusAndCourseType(Status status, CourseType courseType, Pageable pageable);
  Page<Scholarship> findByStatusAndLanguage(Status status, Language language, Pageable pageable);
}
