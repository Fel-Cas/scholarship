package com.api.scholarships.repositories;

import com.api.scholarships.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);
  boolean existsByDni(String dni);
  Optional<User> findByDni(String dni);
  boolean existsByEmailAndIdNot(String email, Long id);
  boolean existsByDniAndIdNot(String dni, Long id);
  Optional<User> findByEmail(String email);

}
