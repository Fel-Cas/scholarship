package com.api.scholarships.constants;

public class Messages {
  //legalRepresentative
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

  //Legal Representative Service
  public static final String MESSAGE_LEGAL_REPRESENTATIVE_BAD_REQUEST_CREATE_WITH_WRONG_DNI="Already exists a legal representative with this DNI";
  public static final String MESSAGE_LEGAL_REPRESENTATIVE_BAD_REQUEST_CREATE_WITH_WRONG_EMAIL="Already exists a legal representative with this email";
  public static final String MESSAGE_LEGAL_REPRESENTATIVE_NOT_FOUND="Not exists a legal representative with this id %s";
  public static final String MESSAGE_LEGAL_REPRESENTATIVE_NOT_FOUND_BY_DNI="Not exists a legal representative with this dni %s";
}
