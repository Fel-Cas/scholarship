package com.api.scholarships.repositories;

import com.api.scholarships.entities.LegalRepresentative;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LegalRepresentativeRepositoryTest {

  @Autowired
  private LegalRepresentativeRepository legalRepresentativeRepository;
  private LegalRepresentative legalRepresentative;

  @BeforeEach
  void init(){
    legalRepresentative= LegalRepresentative.builder()
      .name("Andrés Felipe")
      .surname("Castro Monsalve")
      .dni("1001233147")
      .email("andres.c@gmail.com")
      .password("123456")
      .createdAt(LocalDateTime.now())
      .updatedAt(LocalDateTime.now())
      .build();
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test save legal representative")
  void testSaveLegalRepresentative(){
    //given
    //when
    LegalRepresentative legalRepresentativeSaved = legalRepresentativeRepository.save(legalRepresentative);
    //then
    assertNotNull(legalRepresentativeSaved);
    assertThat(legalRepresentativeSaved.getId()).isGreaterThan(0);
    assertNotNull(legalRepresentativeSaved.getCreatedAt());
    assertNotNull(legalRepresentativeSaved.getUpdatedAt());
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test find legal representative by id")
  void testFindOneLegalRepresentative(){
    //given
    LegalRepresentative legalRepresentativeSaved = legalRepresentativeRepository.save(legalRepresentative);
    //when
    Optional<LegalRepresentative> legalRepresentativeFound = legalRepresentativeRepository.findById(legalRepresentativeSaved.getId());
    //then
    assertTrue(legalRepresentativeFound.isPresent());
    assertEquals(legalRepresentativeSaved.getId(),legalRepresentativeFound.get().getId());
    assertEquals(legalRepresentativeSaved.getName(),legalRepresentativeFound.get().getName());
    assertEquals(legalRepresentativeSaved.getSurname(),legalRepresentativeFound.get().getSurname());
    assertNotNull(legalRepresentativeFound.get());
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by email")
  void testExistsByEmail(){
    //given
    LegalRepresentative legalRepresentativeSaved=legalRepresentativeRepository.save(legalRepresentative);
    //when
    boolean hasLegalRepresentative = legalRepresentativeRepository.existsByEmail(legalRepresentativeSaved.getEmail());
    boolean hasNotLegalRepresentative = legalRepresentativeRepository.existsByEmail(" ");
    //then
    assertTrue(hasLegalRepresentative);
    assertFalse(hasNotLegalRepresentative);
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by dni")
  void testExistsByDni(){
    //given
    LegalRepresentative legalRepresentativeSaved=legalRepresentativeRepository.save(legalRepresentative);
    //when
    boolean hasLegalRepresentative = legalRepresentativeRepository.existsByDni(legalRepresentativeSaved.getDni());
    boolean hasNotLegalRepresentative = legalRepresentativeRepository.existsByDni(" ");
    //then
    assertTrue(hasLegalRepresentative);
    assertFalse(hasNotLegalRepresentative);
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to find a legal representative by dni")
  void testFindByDni(){
    //given
    legalRepresentative.setDni("12345678901234");
    LegalRepresentative legalRepresentativeSaved=legalRepresentativeRepository.save(legalRepresentative);
    //when
    Optional<LegalRepresentative> legalRepresentativeFound = legalRepresentativeRepository.findByDni(legalRepresentativeSaved.getDni());
    //then
    assertTrue(legalRepresentativeFound.isPresent());
    assertEquals(legalRepresentativeSaved.getId(),legalRepresentativeFound.get().getId());
    assertEquals(legalRepresentativeSaved.getName(),legalRepresentativeFound.get().getName());
    assertEquals(legalRepresentativeSaved.getSurname(),legalRepresentativeFound.get().getSurname());
    assertNotNull(legalRepresentativeFound.get());
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by email and id not")
  void testExistsByEmailAndIdNot(){
    //given
    legalRepresentativeRepository.save(
        LegalRepresentative
            .builder()
            .dni("12345678901234")
            .name("Andrés Felipe")
            .surname("Castro Monsalve")
            .password("123456")
            .email("andrs@email.com")
            .build()
    );
    legalRepresentative.setEmail("juan@email.com");
    LegalRepresentative legalRepresentativeSaved=legalRepresentativeRepository.save(legalRepresentative);
    //when
    boolean hasLegalRepresentative = legalRepresentativeRepository.existsByEmailAndIdNot("andrs@email.com",legalRepresentativeSaved.getId());
    boolean hasNotLegalRepresentative = legalRepresentativeRepository.existsByEmailAndIdNot(legalRepresentativeSaved.getEmail(),legalRepresentativeSaved.getId());
    //then
    System.out.println(hasLegalRepresentative);
    assertTrue(hasLegalRepresentative);
    assertFalse(hasNotLegalRepresentative);
  }
  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by dni and id not")
  void testExistsByDniAndIdNot(){
    //given
    legalRepresentativeRepository.save(
        LegalRepresentative
            .builder()
            .dni("123456")
            .name("Andrés Felipe")
            .surname("Castro Monsalve")
            .password("123456")
            .email("andrs@email.com")
            .build()
    );
    legalRepresentative.setDni("1234567");
    LegalRepresentative legalRepresentativeSaved=legalRepresentativeRepository.save(legalRepresentative);
    //when
    boolean hasLegalRepresentative=legalRepresentativeRepository.existsByDniAndIdNot("123456",legalRepresentativeSaved.getId());
    boolean hasNotLegalRepresentative=legalRepresentativeRepository.existsByDniAndIdNot(legalRepresentativeSaved.getDni(),legalRepresentativeSaved.getId());
    //then
    assertTrue(hasLegalRepresentative);
    assertFalse(hasNotLegalRepresentative);
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to delete a legal representative")
  void testDeleteLegalRepresentative(){
    //given
    LegalRepresentative legalRepresentativeSaved=legalRepresentativeRepository.save(legalRepresentative);
    //when
    legalRepresentativeRepository.delete(legalRepresentativeSaved);
    //then
    Optional<LegalRepresentative> legalRepresentativeFound = legalRepresentativeRepository.findById(legalRepresentativeSaved.getId());
    assertFalse(legalRepresentativeFound.isPresent());
  }
}