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
}
