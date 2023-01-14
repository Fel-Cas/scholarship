package com.api.scholarships.repositories;

import com.api.scholarships.entities.Country;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles(profiles = "test")
class CountryRepositoryTest {

  @Autowired
  private  CountryRepository countryRepository;
  private Country country;
  @BeforeEach
  void setUp() {
    country=Country
        .builder()
        .countryName("Nigeria")
        .abbreviation("NG")
        .build();
  }
}