package com.api.scholarships.exceptions;

public class NotFoundException  extends RuntimeException{
  public NotFoundException(String message){
    super(message);
  }
}
