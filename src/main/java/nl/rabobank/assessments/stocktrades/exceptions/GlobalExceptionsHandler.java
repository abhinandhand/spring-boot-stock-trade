package nl.rabobank.assessments.stocktrades.exceptions;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Errors> handleException(Exception ex) {
      Errors errors=evaluateExceptionKind(ex);
      return ResponseEntity.status(errors.getStatus()).body(errors);

    }
    private  Errors evaluateExceptionKind(Exception ex)
    {
      switch (ex.getClass().getSimpleName()) {
        case "ConstraintViolationException" -> {
          List<String> messages = ((ConstraintViolationException) ex).getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .toList();
          String combined = String.join("; ", messages);
          log.error("Exception occured when processing the request:" + combined);
          return new Errors(HttpStatus.BAD_REQUEST.value(), combined, LocalDateTime.now());
        }
        case "MethodArgumentNotValidException" -> {
          List<String> messages = ((MethodArgumentNotValidException) ex).getBindingResult()
            .getFieldErrors()
            .stream()
            .map(FieldError::getDefaultMessage)
            .toList();
          String combined = String.join("; ", messages);
          log.error("Exception occured when processing the request:" + combined);
          return new Errors(HttpStatus.BAD_REQUEST.value(), combined, LocalDateTime.now());
        }
        case "StockNotFountException" -> {
          StockNotFountException notFountException = (StockNotFountException) ex;
          log.error("Exception occured when processing the request:" + notFountException.getMessage());
          return new Errors(HttpStatus.NOT_FOUND.value(), notFountException.getMessage(), LocalDateTime.now());
        }
        case "ResponseStatusException" -> {
          ResponseStatusException responseStatusException = (ResponseStatusException) ex;
          log.error("Exception occured when processing the request:" + responseStatusException.getReason());
          return new Errors(responseStatusException.getStatusCode().value(), responseStatusException.getReason(), LocalDateTime.now());
        }
        case "HttpRequestMethodNotSupportedException" -> {
          HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException = (HttpRequestMethodNotSupportedException) ex;
          log.error("Exception occured when processing the request:" + httpRequestMethodNotSupportedException.getMessage());
          return new Errors(httpRequestMethodNotSupportedException.getStatusCode().value(), httpRequestMethodNotSupportedException.getMessage(), LocalDateTime.now());
        }
        default -> {
          System.out.println(ex.getClass().getSimpleName());
          log.error("Exception occured when processing the request:" + ex.getMessage());
          return new Errors(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
            LocalDateTime.now());
        }
      }

    }

  }

