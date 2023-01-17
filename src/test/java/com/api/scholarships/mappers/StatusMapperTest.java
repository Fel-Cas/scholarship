package com.api.scholarships.mappers;

import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.entities.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatusMapperTest {

  private StatusMapper statusMapper=Mappers.getMapper(StatusMapper.class);
  private Status status;

  @BeforeEach
  void setUp(){
    status=Status.builder()
        .id(1L)
        .statusName("VIGENTE")
        .build();
  }

  @Test
  @DisplayName("Test StatusMapper, test to pass from Status class to StatusDTO class")
  void statusToStatusDTO() {
    //given
    //when
    StatusDTO statusDTO=statusMapper.statusToStatusDTO(status);
    //then
    assertAll(
        ()->assertNotNull(statusDTO),
        ()->assertEquals(status.getId(),statusDTO.getId()),
        ()->assertEquals(status.getStatusName(),statusDTO.getStatusName())
    );
  }

  @Test
  @DisplayName("Test StatusMapper, test to pass from a list of Status items to the list of StatusDTO items")
  void  listStatusToStatusDTO() {
    //given
    List<Status> statuses=List.of(status);
    //when
    List<StatusDTO> statusDTOs=statusMapper.statusToStatusDTO(statuses);
    //then
    assertAll(
        ()->assertNotNull(statusDTOs),
        ()->assertEquals(1,statusDTOs.size()),
        ()->assertEquals(status.getId(),statusDTOs.get(0).getId()),
        ()->assertEquals(status.getStatusName(),statusDTOs.get(0).getStatusName())
    );
  }

}