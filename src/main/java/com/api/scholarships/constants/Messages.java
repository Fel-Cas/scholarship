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

  //Company
  public static final String MESSAGE_ADDRESS_REQUIRED = "Address is required";
  public static final String MESSAGE_ADDRESS_SIZE = "Address must be between 8 and 50 characters";
  public static final String MESSAGE_PHONE_REQUIRED = "Phone is required";
  public static final String MESSAGE_PHONE_SIZE = "Phone must be between 7 and 15 characters";
  public static final String MESSAGE_IMAGE_REQUIRED = "Image is required";
  public static final String MESSAGE_USERS_REQUIRED = "Users is required";
  public static final String MESSAGE_USERS_SIZE = "Lists of user must have at least one user";

}
