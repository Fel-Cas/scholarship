package com.api.scholarships.exceptions;


import org.springframework.http.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

}
