package com.api.scholarships.constants;

public class Messages {
  //User
  public static final String MESSAGE_NAME_REQUIRED = "Name is required";
  public static final String MESSAGE_NAME_SIZE = "Name must be between 2 and 50 characters";
  public static final String MESSAGE_SURNAME_REQUIRED = "Surname is required";
  public static final String MESSAGE_SURNAME_SIZE = "Surname must be between 2 and 50 characters";
  public static final String MESSAGE_EMAIL_REQUIRED = "Email is required";
  public static final String MESSAGE_EMAIL_SIZE = "Email must be between 11 and 50 characters";
  public static final String MESSAGE_EMAIL_VALID = "Email must be valid";
  public static final String MESSAGE_DNI_REQUIRED = "DNI is required";
  public static final String MESSAGE_DNI_SIZE = "DNI must be between 10 and 15 characters";
  public static final String MESSAGE_PASSWORD_REQUIRED = "Password is required";
  public static final String MESSAGE_PASSWORD_SIZE = "Password must be between 8 and 50 characters";
  public static final String MESSAGE_ROLE_REQUIRED = "Role is required";
  public static final String MESSAGE_ROLE_SIZE = "Role must be between 4 and 50 characters";

  //User Service
  public static final String MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_DNI ="Already exists a legal representative with this DNI";
  public static final String MESSAGE_USER_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL ="Already exists a legal representative with this email";
  public static final String MESSAGE_USER_NOT_FOUND ="Not exists a legal representative with this id %s";
  public static final String MESSAGE_USER_NOT_FOUND_BY_DNI ="Not exists a legal representative with this dni %s";

  //Role Service
  public static final String MESSAGE_ROLE_NOT_FOUND ="Not exists a role with this id %s";
  public static final String MESSAGE_ROLE_NOT_FOUND_BY_NAME ="Not exists a role with this name %s";

  //Image Service
  public static final String MESSAGE_IMAGE_NOT_FOUND ="Not exists a image with this id %s";
  public static final String MESSAGE_IMAGE_NOT_VALID ="Image is not valid";

  //Company
  public static final String MESSAGE_ADDRESS_REQUIRED = "Address is required";
  public static final String MESSAGE_ADDRESS_SIZE = "Address must be between 8 and 50 characters";
  public static final String MESSAGE_PHONE_REQUIRED = "Phone is required";
  public static final String MESSAGE_PHONE_SIZE = "Phone must be between 7 and 15 characters";
  public static final String MESSAGE_IMAGE_REQUIRED = "Image is required";
  public static final String MESSAGE_USERS_REQUIRED = "Users is required";
  public static final String MESSAGE_USERS_SIZE = "Lists of user must have at least one user";
  public static final String MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_NAME="Already exists a company with this name %s";
  public static final String MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL="Already exists a company with this email %s";
  public static final String MESSAGE_COMPANY_BAD_REQUEST_CREATE_WITH_WRONG_PHONE="Already exists a company with this phone %s";
  public static final String MESSAGE_COMPANY_NOT_FOUND="Not exists a company with this id %s";
  public static final String MESSAGE_COMPANY_ADD_USER="User %s already has a company assigned";
  public static final String MESSAGE_COMPANY_REMOVE_USER="User %s has not a company assigned";

  //Country
  public static final String MESSAGE_COUNTRY_REQUIRED = "Country is required";
  public static final String MESSAGE_COUNTRY_SIZE = "Country must be between 3 and 50 characters";
  public static final String MESSAGE_COUNTRY_ABBREVIATION_REQUIRED="Abbreviation is required";
  public static final String MESSAGE_COUNTRY_ABBREVIATION_SIZE = "Abbreviation must be between 2 and 3 characters";
  public static final String MESSAGE_CREATE_COUNTRY_WITH_WRONG_NAME="Already exists a country saved with the same name %s";
  public static final String MESSAGE_CREATE_COUNTRY_WITH_WRONG_ABBREVIATION="Already exists a country saved with the same abbreviation %s";
  public static final String MESSAGE_CREATE_COUNTRY_WITH_WRONG_NAME_AND_ABBREVIATION="Already exists a counrty saved with the same name %s and abbreviation %s";
  public static final String MESSAGE_COUNTRY_NOT_FOUND="Not exists a country with this id %s";
  public static final String MESSAGE_COUNTRY_NOT_FOUND_BY_NAME="Not exists a country with this name %s";

  // course type
  public static final String MESSAGE_COURSE_TYPE_NOT_FOUND="Not exists a course type with this id %s";
  public static final String MESSAGE_COURSE_TYPE_NOT_FOUND_BY_NAME="Not exists a course type with this name %s";

  //Status
  public static final String MESSAGE_STATUS_NOT_FOUND="Not exists a status with this id %s";
  public static final String MESSAGE_STATUS_NOT_FOUND_BY_NAME="Not exists a status with this name %s";

  //languages
  public static final String MESSAGE_LANGUAGE_NOT_FOUND="There is no registered language with this id %s";
  public static final String MESSAGE_LANGUAGE_NOT_FOUND_BY_NAME="There is no registered language with this name %s";

  //Careers
  public static final String MESSAGE_CAREER_NAME_REQUIRED="Career name is required";
  public static final String MESSAGE_CAREER_SIZE="Career name must be between 5 and 255 characters long";
}
