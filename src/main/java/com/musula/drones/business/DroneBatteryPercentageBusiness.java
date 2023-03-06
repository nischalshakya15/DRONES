package com.musula.drones.business;

import com.musula.drones.domain.drone.enums.DroneModel;

import java.util.Map;

public interface DroneBatteryPercentageBusiness {

  Double getBatteryPercentage(
      DroneModel droneModel,
      Integer distanceCovered,
      Integer weight,
      Map<DroneModel, Double> batteryConsumptionMap,
      Map<DroneModel, Double> distanceCoverageMap);
}
