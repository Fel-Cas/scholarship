package com.api.scholarships.services;

import com.api.scholarships.entities.Status;
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
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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
    Status statusFound=statusService.findId(status.getId());
    //then
    assertNotNull(statusFound);
    assertEquals(statusFound.getId(),status.getId());
    assertEquals(statusFound.getStatusName(),status.getStatusName());
  }

}