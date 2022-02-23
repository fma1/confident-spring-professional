package com.marcobehler.myfancypdfinvoices.web

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class GlobalControllerExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[MethodArgumentNotValidException]))
  def handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): String = { //
    // TODO you can choose to return your custom object here, which will then get transformed to json/xml etc.
    "Sorry, that was not quite right: " + exception.getMessage
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[ConstraintViolationException]))
  def handleConstraintViolation(exception: ConstraintViolationException): String = {
    "Sorry, that was not quite right: " + exception.getMessage
  }
}
