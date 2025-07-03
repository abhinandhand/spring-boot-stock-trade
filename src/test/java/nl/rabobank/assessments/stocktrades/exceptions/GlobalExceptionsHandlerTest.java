package nl.rabobank.assessments.stocktrades.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionsHandlerTest {
  @InjectMocks
  private GlobalExceptionsHandler globalExceptionsHandler;

  @Test
  void handleException() {

    ResponseEntity<Errors> constraintViolation= globalExceptionsHandler.handleException(mock(ConstraintViolationException.class));
    ResponseEntity<Errors> exception= globalExceptionsHandler.handleException(mock(Exception.class));
    assertNotNull(constraintViolation.getBody());
    assertEquals(400,constraintViolation.getBody().getStatus());
    assertEquals(500,exception.getBody().getStatus());
  }
}