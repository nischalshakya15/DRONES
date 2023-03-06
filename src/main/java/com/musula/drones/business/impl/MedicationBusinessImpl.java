package com.musula.drones.business.impl;

import com.musula.drones.business.MedicationBusiness;
import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.enums.State;
import com.musula.drones.common.exception.drone.DroneMedicationWeightExceedException;
import com.musula.drones.common.exception.drone.InvalidDroneStateException;
import com.musula.drones.common.exception.drone.NotEnoughBatteryException;
import com.musula.drones.domain.drone.constant.DroneExceptionConstant;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.medication.entity.Medication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MedicationBusinessImpl implements MedicationBusiness {

  /**
   * Check if the drone is ready to deliver medication.
   *
   * @param drone The drone that will be used to deliver the medication.
   * @param medicationWeight The weight of the medication that the drone is carrying.
   */
  public void checkDrone(Drone drone, Integer medicationWeight) {
    checkIfTheDroneStateIsIdle(drone.getState());
    checkIfTheBatteryIsLessThanTwentyFivePercent(drone.getBatteryPercentage());
    checkIfTheWeightLimitIsGreaterThanMedicationWeight(drone.getWeightLimit(), medicationWeight);
  }

  /**
   * Check if the battery is less than 25%.
   *
   * @param batteryPercentage The percentage of the battery.
   */
  public void checkIfTheBatteryIsLessThanTwentyFivePercent(Double batteryPercentage) {
    Double minimumBatteryPercentage = DroneConstant.MINIMUM_BATTERY_PERCENTAGE;
    if (batteryPercentage < minimumBatteryPercentage) {
      throw new NotEnoughBatteryException(
          String.format(
              DroneExceptionConstant.NOT_ENOUGH_BATTERY_PERCENTAGE, minimumBatteryPercentage));
    }
  }

  /**
   * Check if the drone state is idle.
   *
   * @param droneState The current state of the drone.
   */
  public void checkIfTheDroneStateIsIdle(State droneState) {
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
  public void checkIfTheWeightLimitIsGreaterThanMedicationWeight(
      Integer droneWeight, Integer medicationWeight) {
    if (droneWeight < medicationWeight) {
      throw new DroneMedicationWeightExceedException(
          DroneExceptionConstant.DRONE_MEDICATION_WEIGHT_EXCEED);
    }
  }

  /**
   * If the current state is in the map, return the next state. Otherwise, return the current state
   *
   * @param currentState The current state of the medication.
   * @param stateMap A map of the current state to the next state.
   * @return The next state of the medication.
   */
  public State getNextMedicationState(State currentState, Map<State, State> stateMap) {
    if (stateMap.containsKey(currentState)) {
      return stateMap.get(currentState);
    }
    return currentState;
  }

  /**
   * Given a medication and a map of states, set the medication's state to the next state in the
   * map.
   *
   * @param medication The medication object that you want to change the state of.
   * @param stateMap A map of the current state to the next state.
   */
  public void setNextMedicationState(Medication medication, Map<State, State> stateMap) {
    medication.setState(getNextMedicationState(medication.getState(), stateMap));
  }
}
