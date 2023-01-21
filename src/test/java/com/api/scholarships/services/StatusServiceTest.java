package com.api.scholarships.services;

import com.api.scholarships.constants.Messages;
import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.dtos.StatusResponse;
import com.api.scholarships.entities.Status;
import com.api.scholarships.exceptions.NotFoundException;
import com.api.scholarships.mappers.StatusMapper;
import com.api.scholarships.repositories.StatusRepository;
import com.api.scholarships.services.implementation.StatusServiceImp;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles(profiles = "test")
class StatusServiceTest {

  @Mock
  private StatusRepository statusRepository;
  @Mock
  private StatusMapper statusMapper;
  @InjectMocks
  private StatusServiceImp statusService;
  private Status status;

  @BeforeEach
  void setUp(){
    status=Status.builder()
        .id(1L)
        .statusName("VIGENTE")
        .build();
  }

  @Test
  @DisplayName("Test StatusService, test to find a status by id")
  void testGetStatusById(){
    //given
    given(statusRepository.findById(status.getId())).willReturn(Optional.of(status));
    //when
    Status statusFound=statusService.findById(status.getId());
    //then
    assertNotNull(statusFound);
    assertEquals(statusFound.getId(),status.getId());
    assertEquals(statusFound.getStatusName(),status.getStatusName());
    verify(statusRepository,times(1)).findById(status.getId());
  }

  @Test
  @DisplayName("Test StatusService, test to check for an exception when trying to find a status and it doesn't exist")
  void fialGetStatusById(){
    //given
    given(statusRepository.findById(status.getId())).willReturn(Optional.empty());
    //when
    NotFoundException notFoundException=assertThrows(NotFoundException.class,()->statusService.findById(status.getId()));
    //then
    assertEquals(Messages.MESSAGE_STATUS_NOT_FOUND.formatted(status.getId()),notFoundException.getMessage());
    verify(statusRepository,times(1)).findById(status.getId());
  }

  @Test
  @DisplayName("Test StatusService, test to find all statuses")
  void testGetAllStatuses(){
    //given
    Page<Status> statuses=new PageImpl<>(List.of(status));
    given(statusRepository.findAll(any(Pageable.class))).willReturn(statuses);

    StatusDTO statusesDTO=StatusDTO.builder()
        .id(1L)
        .statusName("VIGENTE")
        .build();
    given(statusMapper.statusToStatusDTO(List.of(status))).willReturn(List.of(statusesDTO));
    //when
    StatusResponse statusesFound=statusService.findAll(0, 10, "id", "ASC");
    //then
    assertAll(
        () -> assertNotNull(statusesFound),
        ()->assertThat(statusesFound.getContent().size()).isGreaterThan(0),
        ()->assertEquals(0,statusesFound.getNumberPage()),
        ()->assertEquals(1,statusesFound.getSizePage()),
        ()->assertEquals(1,statusesFound.getTotalPages()),
        ()->assertEquals(1,statusesFound.getTotalElements()),
        ()->assertTrue(statusesFound.isLastOne()),
        ()->verify(statusRepository,times(1)).findAll(any(Pageable.class))
    );
  }

  @Test
  @DisplayName("Test StatusService, test to find a status by name")
  void testGetStatusByName(){
    //given
    given(statusRepository.findByStatusName(anyString())).willReturn(Optional.of(status));
    //when
    Status statusFound=statusService.findByName(anyString());
    //then
    assertAll(
        ()-> assertNotNull(statusFound),
        ()->assertEquals(status.getId(),statusFound.getId()),
        ()->assertEquals(status.getStatusName(),statusFound.getStatusName()),
        ()->verify(statusRepository,times(1)).findByStatusName(anyString())
    );
  }

  @Test
  @DisplayName("Test StatusService, test to check for an exception when trying to find a status by name and it doesn't exist ")
  void testGetStatusByNameFail(){
    //given
    given(statusRepository.findByStatusName("NUEVO")).willReturn(Optional.empty());
    //when
    NotFoundException exception=assertThrows(NotFoundException.class,()->statusService.findByName("NUEVO"));
    //then
    assertEquals(Messages.MESSAGE_STATUS_NOT_FOUND_BY_NAME.formatted("NUEVO"), exception.getMessage());
    verify(statusRepository,times(1)).findByStatusName("NUEVO");
  }
}