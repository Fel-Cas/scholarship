package com.api.scholarships.repositories;

import com.api.scholarships.entities.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

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
  @DisplayName("Test StatusRepository, test to get all statuses")
  void testGetAllStatuses(){
    //given
    //when
    List<Status> statusesFound=statusRepository.findAll();
    //then
    assertNotNull(statusesFound);
    assertEquals(4, statusesFound.size());
  }

  @Test
  @DisplayName("Test StatusRepository, test to get a status by id")
  void testGetStatusById(){
    //given
    //when
    Optional<Status> statusFound=statusRepository.findById(4L);
    //then
    assertAll(
        ()->assertTrue(statusFound.isPresent()),
        ()->assertEquals(statusFound.get().getId(),4L),
        ()->assertEquals(statusFound.get().getStatusName(),"CANCELADO")
    );
  }

  @Test
  @DisplayName("Test StatusRepository, test to get a status by name")
  void testGetStatusByName(){
    //given
    //when
    Optional<Status> statusFound=statusRepository.findByStatusName("APLAZADO");
    //then
    assertAll(
        ()->assertNotNull(statusFound),
        ()->assertEquals(3L, statusFound.get().getId()),
        ()->assertEquals("APLAZADO",statusFound.get().getStatusName())
    );
  }

  @Test
  @DisplayName("Test StatusRepository, test to delete a status")
  void testDelete(){
    //given
    //when
    statusRepository.deleteById(2L);
    //then
    assertTrue(statusRepository.findById(2L).isEmpty());
    assertTrue(statusRepository.findAll().size()<5);
  }
}