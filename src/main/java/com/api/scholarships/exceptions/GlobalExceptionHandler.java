package com.api.scholarships.exceptions;


import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(BadRequestException.class)
  public final ProblemDetail handlerBadRequestException(BadRequestException badRequest){
    ProblemDetail body=ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,"BAD REQUEST");
    body.setDetail(badRequest.getMessage());
    return body;
  }

  @ExceptionHandler(NotFoundException.class)
  public final ProblemDetail handlerNotFoundException(NotFoundException notFoundException){
    ProblemDetail body=ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,"NOT FOUND");
    body.setDetail(notFoundException.getMessage());
    return body;
  }
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Map<String,String> errors= new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error->{
      String fieldName= ((FieldError) error).getField();
      String message=error.getDefaultMessage();
      errors.put(fieldName,message);
    });
    return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
  }

}
