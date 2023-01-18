package com.api.scholarships.repositories;

import com.api.scholarships.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
   Optional<Language> findByLanguageName(String name);
}
