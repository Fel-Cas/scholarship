package com.api.scholarships.constants;

public class Endpoints {
  // Id
  public static final String ID = "/{id}";

  //User
  public static final String USERS = "/api/v1/scholarships/users";
  public static final String USERS_DNI ="/dni/{dni}";

  //Role
  public static final String ROLES = "/api/v1/scholarships/roles";

  //Company
  public static final String COMPANIES = "/api/v1/scholarships/companies";
  public static final String COMPANIES_USERS = "/users/{idCompany}/{idUser}";
  public static final String COMPANIES_IMAGES = "/images/{idCompany}";

  //Country
  public static final String COUNTRIES = "/api/v1/scholarships/countries";

  // Course Type
  public static  final String COURSE_TYPE = "/api/v1/scholarships/course-types";

  //Status
  public static final String STATUS = "/api/v1/scholarships/statuses";

  //Languages
  public static final String LANGUAGES = "/api/v1/scholarships/languages";

  //Careers
  public static final String CAREERS = "/api/v1/scholarships/careers";

  //Scholarships
  public static final String SCHOLARSHIPS = "/api/v1/scholarships";
  public static final String SCHOLARSHIPS_COUNTRY ="/country/{scholarshipId}/{countryId}";
  public static final String SCHOLARSHIPS_COURSE_TYPE ="/course-type/{scholarshipId}/{courseTypeId}";
  public static final String SCHOLARSHIPS_STATUS ="/status/{scholarshipId}/{statusId}";
  public static final String SCHOLARSHIPS_LANGUAGE ="/language/{scholarshipId}/{languageId}";
}
