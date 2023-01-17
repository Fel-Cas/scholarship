package com.api.scholarships.mappers;

import com.api.scholarships.dtos.StatusDTO;
import com.api.scholarships.entities.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class StatusMapperTest {

  private StatusMapper statusMapper=Mappers.getMapper(StatusMapper.class);

  @Test
  @DisplayName("Test StatusMapper, test to pass from Status class to StatusDTO class")
  void statusToStatusDTO() {
    //given
    Status status=Status.builder()
        .id(1L)
        .statusName("VIGENTE")
        .build();
    //when
    StatusDTO statusDTO=statusMapper.statusToStatusDTO(status);
    //then
    assertAll(
        ()->assertNotNull(statusDTO),
        ()->assertEquals(status.getId(),statusDTO.getId()),
        ()->assertEquals(status.getStatusName(),statusDTO.getStatusName())
    );
  }

}