package com.api.scholarships.repositories;

import com.api.scholarships.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
  Optional<Status> findByStatusName(String statusName);
}
