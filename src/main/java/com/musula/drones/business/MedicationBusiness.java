package com.musula.drones.business;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.medication.entity.Medication;

import java.util.Map;

public interface MedicationBusiness {

  void checkDrone(Drone drone, Integer medicationWeight);

  void checkIfTheBatteryIsLessThanTwentyFivePercent(Double batteryPercentage);

  void checkIfTheDroneStateIsIdle(State droneState);

  void checkIfTheWeightLimitIsGreaterThanMedicationWeight(
      Integer droneWeight, Integer medicationWeight);

  State getNextMedicationState(State currentState, Map<State, State> stateMap);

  void setNextMedicationState(Medication medication, Map<State, State> stateMap);
}
