package com.api.scholarships.exceptions;

public class BadRequestException extends RuntimeException{
  public BadRequestException(String message){
    super(message);
  }
}
