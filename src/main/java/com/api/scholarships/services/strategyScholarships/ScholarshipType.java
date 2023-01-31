package com.api.scholarships.services.strategyScholarships;

import java.util.Arrays;

public enum ScholarshipType {
  STATUS,LANGUAGE,CAREER,COMPANY,COUNTRY,COURSETYPE,DEFAULT,STATUS_LANGUAGE,STATUS_CAREER,STATUS_COMPANY,STATUS_COUNTRY,STATUS_COURSETYPE;
  public static boolean exists(ScholarshipType type ){
    return Arrays.stream(ScholarshipType.values()).anyMatch(type::equals);
  }
}
