package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.CourseTypeDTO;
import com.api.scholarships.dtos.CourseTypeResponse;
import com.api.scholarships.entities.CourseType;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.CourseTypeMapper;
import com.api.scholarships.repositories.CourseTypeRepository;
import com.api.scholarships.services.implementation.CourseTypeServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class CourseTypeServiceTest {

  @Mock
  private CourseTypeRepository courseTypeRepository;
  @Mock
  private CourseTypeMapper courseTypeMapper;
  @InjectMocks
  private CourseTypeServiceImp courseTypeService;
  private CourseType courseType;

  @BeforeEach
  void setUp(){
    courseType=CourseType
        .builder()
        .id(1L)
        .courseType("CURSO CORTO")
        .build();
  }

  @Test
  @DisplayName("Test CourseTypeService, test to find a course by its id")
  void testFindId(){
    //given
    given(courseTypeRepository.findById(anyLong())).willReturn(Optional.of(courseType));
    //when
    CourseType courseTypeFound=courseTypeService.findById(anyLong());
    //then
    assertNotNull(courseTypeFound);
    assertEquals(courseType.getId(),courseTypeFound.getId());
    assertEquals(courseType.getCourseType(),courseTypeFound.getCourseType());
  }

  @Test
  @DisplayName("Test CourseTypeService, test to check  an exception when a course type is searched by its id and does not exist")
  void  testFailFindById(){
    //given
    given(courseTypeRepository.findById(1L)).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class, ()->courseTypeService.findById(1L));
    //then
    assertEquals(Messages.MESSAGE_COURSE_TYPE_NOT_FOUND.formatted(1L), exception.getMessage());
  }

  @Test
  @DisplayName("Test CourseTypeService, test to find all course types")
  void testFindAll(){
    //given
    CourseTypeDTO courseTypeDTO=CourseTypeDTO.builder()
        .id(1L)
        .courseType("CURSO CORTO")
        .build();
    Page<CourseType> courseTypes=new PageImpl<>(List.of(courseType));
    given(courseTypeRepository.findAll(any(Pageable.class))).willReturn(courseTypes);
    given(courseTypeMapper.typeCoursesToTypeCourseDTOs(List.of(courseType))).willReturn(List.of(courseTypeDTO));
    //when
    CourseTypeResponse courseTypeResponse=courseTypeService.findAll(0,10,"id","ASC");
    //then
    assertAll(
        ()->assertNotNull(courseTypeResponse),
        ()->assertThat(courseTypeResponse.getContent().size()).isGreaterThan(0),
        ()->assertEquals(0,courseTypeResponse.getNumberPage()),
        ()->assertEquals(1,courseTypeResponse.getSizePage()),
        ()->assertEquals(1,courseTypeResponse.getTotalElements()),
        ()->assertEquals(1,courseTypeResponse.getTotalPages()),
        ()->assertTrue(courseTypeResponse.isLastOne())
    );
  }

  @Test
  @DisplayName("Test CourseTypeService, test to find a course type by name")
  void testFindByName(){
    //given
    given(courseTypeRepository.findByCourseType(anyString())).willReturn(Optional.of(courseType));
    //when
    CourseType courseTypeFound=courseTypeService.findByCourseType(anyString());
    //then
    assertAll(
        ()->assertNotNull(courseTypeFound),
        ()->assertEquals(courseType.getCourseType(),courseTypeFound.getCourseType()),
        ()->assertEquals(courseType.getId(),courseTypeFound.getId())
    );
  }

  @Test
  @DisplayName("Test CourseTypeService, test to check an exception when searching a course type that doesn't exist")
  void testFailFindByName(){
    //given
    given(courseTypeRepository.findByCourseType(anyString())).willReturn(Optional.empty());
    //when
    NotFoundException exception= assertThrows(NotFoundException.class,()->courseTypeService.findByCourseType("CURSO CORTO"));
    //then
    assertEquals(Messages.MESSAGE_COURSE_TYPE_NOT_FOUND_BY_NAME.formatted("CURSO CORTO"),exception.getMessage());
  }
}