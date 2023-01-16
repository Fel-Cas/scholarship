package com.api.scholarships.repositories;

import com.api.scholarships.entities.CourseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseType,Long> {
  Optional<CourseType> findByCourseType(String courseType);
}
