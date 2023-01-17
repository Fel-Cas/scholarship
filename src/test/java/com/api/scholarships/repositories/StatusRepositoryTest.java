package com.api.scholarships.repositories;

import com.api.scholarships.entities.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class StatusRepositoryTest {
  @Autowired
  private StatusRepository statusRepository;

  @Test
  @DisplayName("Test StatusRepository, test to create a status")
  void testCreate(){
    //given
    Status status=Status.builder()
        .statusName("PROCESANDO")
        .build();
    //when
    Status statusSaved= statusRepository.save(status);
    //then
    assertNotNull(statusSaved);
    assertThat(statusSaved.getId()).isGreaterThan(4);
    assertEquals(statusSaved.getStatusName(),status.getStatusName());
  }

  @Test
  @DisplayName("Test StatusRepository, test to get all statutes")
  void testGetAllStatuses(){
    //given
    //when
    List<Status> statusesFound=statusRepository.findAll();
    //then
    assertNotNull(statusesFound);
    assertEquals(4, statusesFound.size());
  }
}