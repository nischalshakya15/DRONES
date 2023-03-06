package com.musula.drones;

import com.musula.drones.business.MedicationBusiness;
import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.enums.State;
import com.musula.drones.common.exception.drone.DroneChargingException;
import com.musula.drones.common.exception.drone.DroneMedicationWeightExceedException;
import com.musula.drones.common.exception.drone.InvalidDroneStateException;
import com.musula.drones.common.exception.drone.NotEnoughBatteryException;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.enums.DroneModel;
import com.musula.drones.domain.medication.entity.Medication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicationBusinessTests {

  @Autowired private MedicationBusiness medicationBusiness;

  private static final String DRONE_SERIAL_NUMBER = "T18SN1234567890";

  private static final Integer DRONE_WEIGHT = 500;

  /** It checks if a drone is valid for a given weight */
  @Test
  void checkValidDrone() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setModel(DroneModel.LIGHT_WEIGHT);
    drone.setWeightLimit(DRONE_WEIGHT);
    drone.setState(State.IDLE);
    drone.setBatteryPercentage(100.0d);
    drone.setCharging(false);
    Assertions.assertDoesNotThrow(() -> medicationBusiness.checkDrone(drone, 500));
  }

  /** This function checks if the drone is charging */
  @Test
  void assertDoesNotThrowDroneChargingException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setCharging(false);
    Assertions.assertDoesNotThrow(() -> medicationBusiness.checkIfTheDroneIsCharging(drone));
  }

  /** This function tests if the drone is charging */
  @Test
  void assertThrowDroneChargingException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setCharging(true);
    Assertions.assertThrows(
        DroneChargingException.class, () -> medicationBusiness.checkIfTheDroneIsCharging(drone));
  }

  /** The function checks if the drone state is idle */
  @Test
  void assertDoesNotThrowInvalidDroneStateException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setState(State.IDLE);
    Assertions.assertDoesNotThrow(
        () -> medicationBusiness.checkIfTheDroneStateIsIdle(drone.getState()));
  }

  /**
   * It asserts that the method `checkIfTheDroneStateIsIdle` throws an `InvalidDroneStateException`
   * when the drone state is not `IDLE`
   */
  @Test
  void assertThrowInvalidDroneStateException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setState(State.LOADED);
    Assertions.assertThrows(
        InvalidDroneStateException.class,
        () -> medicationBusiness.checkIfTheDroneStateIsIdle(drone.getState()));
  }

  /**
   * The function checks if the battery percentage is less than 25% and throws a
   * NotEnoughBatteryException if it is
   */
  @Test
  void assertDoesNotThrowNotEnoughBatteryException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setBatteryPercentage(90.0d);
    Assertions.assertDoesNotThrow(
        () ->
            medicationBusiness.checkIfTheBatteryIsLessThanTwentyFivePercent(
                drone.getBatteryPercentage()));
  }

  /**
   * It asserts that the method `checkIfTheBatteryIsLessThanTwentyFivePercent` throws a
   * `NotEnoughBatteryException` when the battery percentage is less than 25%
   */
  @Test
  void assertThrowNotEnoughBatteryException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setBatteryPercentage(20.0d);
    Assertions.assertThrows(
        NotEnoughBatteryException.class,
        () ->
            medicationBusiness.checkIfTheBatteryIsLessThanTwentyFivePercent(
                drone.getBatteryPercentage()));
  }

  /**
   * This function checks if the weight limit of the drone is greater than the weight of the
   * medication
   */
  @Test
  void assertDoesNotThrowDroneMedicationWeightLimitException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setWeightLimit(DRONE_WEIGHT);
    Assertions.assertDoesNotThrow(
        () ->
            medicationBusiness.checkIfTheWeightLimitIsGreaterThanMedicationWeight(
                drone.getWeightLimit(), 500));
  }

  /** It checks if the weight limit of the drone is greater than the weight of the medication */
  @Test
  void assertThrowDroneMedicationWeightLimitException() {
    Drone drone = new Drone();
    drone.setSerialNumber(DRONE_SERIAL_NUMBER);
    drone.setWeightLimit(DRONE_WEIGHT);
    Assertions.assertThrows(
        DroneMedicationWeightExceedException.class,
        () ->
            medicationBusiness.checkIfTheWeightLimitIsGreaterThanMedicationWeight(
                drone.getWeightLimit(), 700));
  }

  /** It tests that the next state of a medication is the one expected */
  @Test
  void assertNextMedicationState() {
    for (State state : DroneConstant.stateMap.keySet()) {
      Assertions.assertEquals(
          DroneConstant.stateMap.get(state),
          medicationBusiness.getNextMedicationState(state, DroneConstant.stateMap));
    }
  }

  /** This function tests that the current state of the medication is not in the state map */
  @Test
  void assertCurrentMedicationStateNotInStateMap() {
    Assertions.assertEquals(
        State.IDLE, medicationBusiness.getNextMedicationState(State.IDLE, DroneConstant.stateMap));
  }

  /** This function tests the state transition of a medication object from one state to another */
  @Test
  void assertSetMedicationState() {
    Medication medication = new Medication();
    medication.setState(State.LOADING);

    while (!medication.getState().equals(State.DELIVERED)) {
      State nextState = DroneConstant.stateMap.get(medication.getState());
      medicationBusiness.setNextMedicationState(medication, DroneConstant.stateMap);
      Assertions.assertEquals(nextState, medication.getState());
    }
  }
}
