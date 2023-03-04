package com.musula.drones.common.exception;

public class NotEnoughBatteryException extends RuntimeException {

  public NotEnoughBatteryException(String message) {
    super(message);
  }
}
