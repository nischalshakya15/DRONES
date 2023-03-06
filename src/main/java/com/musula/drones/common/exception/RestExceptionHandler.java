package com.musula.drones.common.exception;

import com.musula.drones.common.exception.drone.DroneAlreadyExistException;
import com.musula.drones.common.exception.drone.DroneChargingException;
import com.musula.drones.common.exception.drone.DroneMedicationWeightExceedException;
import com.musula.drones.common.exception.drone.InvalidDroneStateException;
import com.musula.drones.common.exception.drone.NotEnoughBatteryException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * If an exception is thrown, the function will return a response entity with the exception
   * message and the exception itself
   *
   * @param exception The exception that was thrown.
   * @return A ResponseEntity<APIException>
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<APIException> handleException(Exception exception) {
    APIException apiException =
        new APIException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * It takes an exception, creates an APIException object, and returns a ResponseEntity with the
   * APIException object
   *
   * @param exception The exception that was thrown.
   * @return A ResponseEntity object.
   */
  @ExceptionHandler(NotFoundException.class)
  protected ResponseEntity<APIException> handleNotFoundException(Exception exception) {
    APIException apiException =
        new APIException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * It handles the InvalidDroneStateException and returns a response with the message and the
   * status code.
   *
   * @param exception The exception that was thrown.
   * @return A ResponseEntity object is being returned.
   */
  @ExceptionHandler(InvalidDroneStateException.class)
  protected ResponseEntity<APIException> handleInvalidDroneStateException(
      InvalidDroneStateException exception) {
    APIException apiException =
        new APIException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * It handles the exception thrown by the DroneMedicationWeightExceedException class.
   *
   * @param exception The exception object that was thrown.
   * @return A ResponseEntity with the APIException
   */
  @ExceptionHandler(DroneMedicationWeightExceedException.class)
  protected ResponseEntity<APIException> handleDroneMedicationWeightExceedException(
      DroneMedicationWeightExceedException exception) {
    APIException apiException =
        new APIException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * It handles the exception and returns a response entity with the exception message.
   *
   * @param exception The exception that was thrown.
   * @return A ResponseEntity with the APIException as the body.
   */
  @ExceptionHandler(NotEnoughBatteryException.class)
  protected ResponseEntity<APIException> handleNotEnoughBatteryException(
      NotEnoughBatteryException exception) {
    APIException apiException =
        new APIException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * It handles the exception thrown by the DroneAlreadyExistException class.
   *
   * @param exception The exception object that was thrown.
   * @return A ResponseEntity object is being returned.
   */
  @ExceptionHandler(DroneAlreadyExistException.class)
  protected ResponseEntity<APIException> handleDroneAlreadyExistException(
      DroneAlreadyExistException exception) {
    APIException apiException =
        new APIException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * It handles the DroneChargingException and returns a response entity with the exception message.
   *
   * @param exception The exception object that was thrown.
   * @return A ResponseEntity object is being returned.
   */
  @ExceptionHandler(DroneChargingException.class)
  protected ResponseEntity<APIException> handleDroneChargingException(
      DroneChargingException exception) {
    APIException apiException =
        new APIException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
    return buildResponseEntity(apiException);
  }

  /**
   * We are overriding the default Spring Boot exception handler for MethodArgumentNotValidException
   * and returning a map of field names and error messages
   *
   * @param ex The exception that was thrown
   * @param headers HttpHeaders
   * @param status The status code to return.
   * @param request The current request.
   * @return A map of field names and error messages.
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    Map<String, String> validationErrorMap = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              String fieldName = ((FieldError) error).getField();
              String message = error.getDefaultMessage();
              validationErrorMap.put(fieldName, message);
            });
    return new ResponseEntity<>(validationErrorMap, HttpStatus.BAD_REQUEST);
  }

  /**
   * It returns a new ResponseEntity object with the APIException object and the status code of the
   * APIException object
   *
   * @param apiException The exception object that was thrown.
   * @return A ResponseEntity object is being returned.
   */
  private ResponseEntity<APIException> buildResponseEntity(APIException apiException) {
    return new ResponseEntity<>(apiException, apiException.getStatus());
  }
}
