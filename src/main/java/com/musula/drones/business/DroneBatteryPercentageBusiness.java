package com.musula.drones.business;

import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.enums.DroneModel;

import java.util.Map;

public interface DroneBatteryPercentageBusiness {

  Double getBatteryConsumptionPercentage(
      DroneModel droneModel,
      Integer distanceCovered,
      Integer weight,
      Map<DroneModel, Double> batteryConsumptionMap,
      Map<DroneModel, Double> distanceCoverageMap);

  void setBatteryPercentageAndIsCharging(
      Drone drone, Double batteryPercentageToRechargeBy, Double totalBatteryPercentage);

  void checkBatteryPercentage(
      Drone droneModel,
      Integer distanceCovered,
      Integer weight,
      Map<DroneModel, Double> batteryConsumptionMap,
      Map<DroneModel, Double> distanceCoverageMap);
}
