package com.api.scholarships.services.strategyScholarships;

import java.util.Arrays;

public enum ScholarshipType {
  STATUS,LANGUAGE,CAREER,COMPANY,COUNTRY,COURSETYPE,DEFAULT;
  public static boolean exists(ScholarshipType type ){
    return Arrays.stream(ScholarshipType.values()).anyMatch(type::equals);
  }
}
