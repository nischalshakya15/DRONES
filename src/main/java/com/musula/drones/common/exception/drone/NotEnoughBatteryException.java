package com.musula.drones.common.exception.drone;

public class NotEnoughBatteryException extends RuntimeException {

  public NotEnoughBatteryException(String message) {
    super(message);
  }
}
