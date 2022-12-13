package com.api.scholarships.repositories;

import com.api.scholarships.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;
  private User user;

  @BeforeEach
  void init(){
    user = User.builder()
      .name("Andrés Felipe")
      .surname("Castro Monsalve")
      .dni("1001233147")
      .email("andres.c@gmail.com")
      .password("123456")
      .createdAt(Instant.now())
      .updatedAt(Instant.now())
      .build();
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test save legal representative")
  void testSaveLegalRepresentative(){
    //given
    //when
    User userSaved = userRepository.save(user);
    //then
    assertNotNull(userSaved);
    assertThat(userSaved.getId()).isGreaterThan(0);
    assertNotNull(userSaved.getCreatedAt());
    assertNotNull(userSaved.getUpdatedAt());
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test find legal representative by id")
  void testFindOneLegalRepresentative(){
    //given
    User userSaved = userRepository.save(user);
    //when
    Optional<User> userFound = userRepository.findById(userSaved.getId());
    //then
    assertTrue(userFound.isPresent());
    assertEquals(userSaved.getId(),userFound.get().getId());
    assertEquals(userSaved.getName(),userFound.get().getName());
    assertEquals(userSaved.getSurname(),userFound.get().getSurname());
    assertNotNull(userFound.get());
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by email")
  void testExistsByEmail(){
    //given
    User userSaved = userRepository.save(user);
    //when
    boolean hasUser = userRepository.existsByEmail(userSaved.getEmail());
    boolean hasNotUser = userRepository.existsByEmail(" ");
    //then
    assertTrue(hasUser);
    assertFalse(hasNotUser);
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by dni")
  void testExistsByDni(){
    //given
    User userSaved = userRepository.save(user);
    //when
    boolean hasUser = userRepository.existsByDni(userSaved.getDni());
    boolean hasNotUser = userRepository.existsByDni(" ");
    //then
    assertTrue(hasUser);
    assertFalse(hasNotUser);
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to find a legal representative by dni")
  void testFindByDni(){
    //given
    user.setDni("12345678901234");
    User userSaved = userRepository.save(user);
    //when
    Optional<User> userFound = userRepository.findByDni(userSaved.getDni());
    //then
    assertTrue(userFound.isPresent());
    assertEquals(userSaved.getId(),userFound.get().getId());
    assertEquals(userSaved.getName(),userFound.get().getName());
    assertEquals(userSaved.getSurname(),userFound.get().getSurname());
    assertNotNull(userFound.get());
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by email and id not")
  void testExistsByEmailAndIdNot(){
    //given
    userRepository.save(
        User
            .builder()
            .dni("12345678901234")
            .name("Andrés Felipe")
            .surname("Castro Monsalve")
            .password("123456")
            .email("andrs@email.com")
            .build()
    );
    user.setEmail("juan@email.com");
    User userSaved = userRepository.save(user);
    //when
    boolean hasUser = userRepository.existsByEmailAndIdNot("andrs@email.com", userSaved.getId());
    boolean hasNotUser = userRepository.existsByEmailAndIdNot(userSaved.getEmail(), userSaved.getId());
    //then
    System.out.println(hasUser);
    assertTrue(hasUser);
    assertFalse(hasNotUser);
  }
  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to determinate if exists a legal representative by dni and id not")
  void testExistsByDniAndIdNot(){
    //given
    userRepository.save(
        User
            .builder()
            .dni("123456")
            .name("Andrés Felipe")
            .surname("Castro Monsalve")
            .password("123456")
            .email("andrs@email.com")
            .build()
    );
    user.setDni("1234567");
    User userSaved = userRepository.save(user);
    //when
    boolean hasUser= userRepository.existsByDniAndIdNot("123456", userSaved.getId());
    boolean hasNotUser= userRepository.existsByDniAndIdNot(userSaved.getDni(), userSaved.getId());
    //then
    assertTrue(hasUser);
    assertFalse(hasNotUser);
  }

  @Test
  @DisplayName("Test LegalRepresentativeRepository,Test to delete a legal representative")
  void testDeleteLegalRepresentative(){
    //given
    User userSaved = userRepository.save(user);
    //when
    userRepository.delete(userSaved);
    //then
    Optional<User> userFound = userRepository.findById(userSaved.getId());
    assertFalse(userFound.isPresent());
  }
}