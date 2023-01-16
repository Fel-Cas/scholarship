package com.api.scholarships.repositories;

import com.api.scholarships.entities.CourseType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class CourseTypeRepositoryTest {
  @Autowired
  private CourseTypeRepository courseTypeRepository;

  @Test
  @DisplayName("Test CourseTypeReposiotry, test to create a course type")
  void testCreate(){
    //given
    CourseType courseType = CourseType.builder()
        .courseType("SEMILLERO")
        .build();
    //when
    CourseType courseTypeSaved=courseTypeRepository.save(courseType);
    //then
    assertNotNull(courseTypeSaved);
    assertEquals(courseType.getCourseType(), courseTypeSaved.getCourseType());
    assertThat(courseTypeSaved.getId()).isGreaterThan(0);
  }

  @Test
  @DisplayName("Test CourseTypeReposiotry, test to find a course type by id")
  void testFindById(){
    //given
    //when
    Optional<CourseType> courseTypeFound=courseTypeRepository.findById(1L);
    //then
    assertAll(
        ()->assertTrue(courseTypeFound.isPresent()),
        ()->assertEquals(1L, courseTypeFound.get().getId()),
        ()->assertEquals("CURSO CORTO", courseTypeFound.get().getCourseType())
    );
  }

  @Test
  @DisplayName("Test CourseRepository, test to find all courses")
  void testFindAll(){
    //given
    //when
    List<CourseType> allCourseTypes=courseTypeRepository.findAll();
    //then
    assertNotNull(allCourseTypes);
    assertThat(allCourseTypes.size()).isGreaterThan(4);
  }
}