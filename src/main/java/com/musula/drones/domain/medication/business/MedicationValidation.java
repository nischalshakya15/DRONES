package com.musula.drones.domain.medication.business;

import com.musula.drones.common.enums.State;
import com.musula.drones.common.exception.DroneMedicationWeightExceedException;
import com.musula.drones.common.exception.InvalidDroneStateException;
import com.musula.drones.common.exception.NotEnoughBatteryException;
import com.musula.drones.domain.drone.constant.DroneExceptionConstant;
import com.musula.drones.domain.drone.entity.Drone;

public class MedicationValidation {

  /**
   * Check if the drone is ready to deliver medication.
   *
   * @param drone The drone that will be used to deliver the medication.
   * @param medicationWeight The weight of the medication that the drone is carrying.
   */
  public static void checkDrone(Drone drone, Integer medicationWeight) {
    checkIfTheDroneStateIsIdle(drone.getState());
    checkIfTheBatteryIsLessThanTwentyFivePercent(drone.getBatteryPercentage());
    checkIfTheWeightLimitIsGreaterThanMedicationWeight(drone.getWeightLimit(), medicationWeight);
  }

  /**
   * Check if the battery is less than 25%.
   *
   * @param batteryPercentage The percentage of the battery.
   */
  public static void checkIfTheBatteryIsLessThanTwentyFivePercent(Integer batteryPercentage) {
    if (batteryPercentage < 25) {
      throw new NotEnoughBatteryException(
          String.format(DroneExceptionConstant.NOT_ENOUGH_BATTER_PERCENTAGE, 25));
    }
  }

  /**
   * Check if the drone state is idle.
   *
   * @param droneState The current state of the drone.
   */
  public static void checkIfTheDroneStateIsIdle(State droneState) {
    if (droneState != State.IDLE) {
      throw new InvalidDroneStateException(
          String.format(
              DroneExceptionConstant.INVALID_DEFAULT_DRONE_STATE,
              droneState.name(),
              State.IDLE.name()));
    }
  }

  /**
   * Check if the weight limit is greater than medication weight.
   *
   * @param droneWeight The weight of the drone
   * @param medicationWeight The weight of the medication that the drone is carrying.
   */
  public static void checkIfTheWeightLimitIsGreaterThanMedicationWeight(
      Integer droneWeight, Integer medicationWeight) {
    if (droneWeight < medicationWeight) {
      throw new DroneMedicationWeightExceedException(
          DroneExceptionConstant.DRONE_MEDICATION_WEIGHT_EXCEED);
    }
  }
}
