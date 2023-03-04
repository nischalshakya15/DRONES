package com.musula.drones.domain.drone.constant;

public class DroneExceptionConstant {

  public static final String DRONE_NOT_FOUND = "Drone with serialNumber %s not found.";

  public static final String NOT_ENOUGH_BATTER_PERCENTAGE =
      "Battery level is less than %s percent.";

  public static final String INVALID_DEFAULT_DRONE_STATE =
      "Current Drone state is %s.It should be %s to load the medication items.";

  public static final String DRONE_MEDICATION_WEIGHT_EXCEED =
      "Drone can only carry up to 500gr max.";
}
