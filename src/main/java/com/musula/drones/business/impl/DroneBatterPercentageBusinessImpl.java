package com.musula.drones.business.impl;

import com.musula.drones.business.DroneBatteryPercentageBusiness;
import com.musula.drones.domain.drone.enums.DroneModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DroneBatterPercentageBusinessImpl implements DroneBatteryPercentageBusiness {

  /**
   * "Given a drone model, distance covered, weight, and two maps, return the battery percentage."
   *
   * @param droneModel The model of the drone.
   * @param distanceCovered The distance covered by the drone in meters.
   * @param weight The weight of the package to be delivered
   * @param batteryConsumptionMap A map of drone models and their battery consumption.
   * @param distanceCoverageMap This is a map of drone models and their distance coverage.
   * @return The battery percentage of the drone.
   */
  public Double getBatteryPercentage(
      DroneModel droneModel,
      Integer distanceCovered,
      Integer weight,
      Map<DroneModel, Double> batteryConsumptionMap,
      Map<DroneModel, Double> distanceCoverageMap) {
    return (batteryConsumptionMap.get(droneModel) * distanceCovered * 2)
        + (distanceCoverageMap.get(droneModel) * weight);
  }
}
