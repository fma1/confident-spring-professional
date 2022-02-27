package com.marcobehler.mybank.web

import com.marcobehler.mybank.dto.ErrorDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.{ExceptionHandler, ResponseStatus, RestControllerAdvice}

import javax.validation.ConstraintViolationException
import scala.jdk.CollectionConverters._

@RestControllerAdvice
class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[MethodArgumentNotValidException]))
  def handleMethodArgumentNotValid(exception: MethodArgumentNotValidException): ErrorDTO = {
    val invalidFields = exception.getBindingResult.getFieldErrors.asScala.map(_.getField).toList
    new ErrorDTO(exception.getMessage, invalidFields)
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(Array(classOf[ConstraintViolationException]))
  def handleMethodArgumentNotValid(exception: ConstraintViolationException): ErrorDTO = {
    val invalidFields = exception.getConstraintViolations.asScala.map(_.getPropertyPath.toString).toList
    new ErrorDTO(exception.getMessage, invalidFields)
  }
}
