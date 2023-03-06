package com.musula.drones.business.impl;

import com.musula.drones.business.DroneBatteryPercentageBusiness;
import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.exception.drone.NotEnoughBatteryException;
import com.musula.drones.domain.drone.constant.DroneExceptionConstant;
import com.musula.drones.domain.drone.entity.Drone;
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
  public Double getBatteryConsumptionPercentage(
      DroneModel droneModel,
      Integer distanceCovered,
      Integer weight,
      Map<DroneModel, Double> batteryConsumptionMap,
      Map<DroneModel, Double> distanceCoverageMap) {
    return (batteryConsumptionMap.get(droneModel) * distanceCovered * 2)
        + (distanceCoverageMap.get(droneModel) * weight);
  }

  /**
   * The function takes in a drone object, a battery percentage to recharge by, and a total battery
   * percentage. It then checks if the current battery percentage is not equal to the total battery
   * percentage. If it is not, it then calculates the battery recharge by, and if the battery
   * recharge by plus the battery recharge by is greater than or equal to the total battery
   * percentage, it sets the battery percentage to the difference between the battery percentage and
   * the current battery percentage. Otherwise, it sets the battery percentage to the battery
   * recharge by plus the battery recharge by
   *
   * @param drone The drone object that needs to be recharged.
   * @param batteryPercentageToRechargeBy The percentage of battery to be recharged by.
   * @param totalBatteryPercentage The total battery percentage of the drone.
   */
  @Override
  public void setBatteryPercentageAndIsCharging(
      Drone drone, Double batteryPercentageToRechargeBy, Double totalBatteryPercentage) {
    Double currentBatteryPercentage = drone.getBatteryPercentage();

    if (!currentBatteryPercentage.equals(totalBatteryPercentage)) {
      double batteryRechargeBy =
          (currentBatteryPercentage % totalBatteryPercentage) + batteryPercentageToRechargeBy;
      if (batteryRechargeBy >= totalBatteryPercentage) {
        drone.setBatteryPercentage(
            currentBatteryPercentage + (totalBatteryPercentage - currentBatteryPercentage));
        drone.setCharging(false);
      } else {
        drone.setBatteryPercentage(batteryRechargeBy);
        drone.setCharging(true);
      }
    }
  }

  /**
   * It checks if the drone has enough battery to cover the distance
   *
   * @param drone The drone object
   * @param distanceCovered The distance to be covered by the drone.
   * @param weight The weight of the package to be delivered
   * @param batteryConsumptionMap This is a map of drone model and the battery consumption for that
   *     model.
   * @param distanceCoverageMap This is a map of drone model and the distance it can cover.
   */
  @Override
  public void checkBatteryPercentage(
      Drone drone,
      Integer distanceCovered,
      Integer weight,
      Map<DroneModel, Double> batteryConsumptionMap,
      Map<DroneModel, Double> distanceCoverageMap) {
    Double batteryConsumption =
        getBatteryConsumptionPercentage(
            drone.getModel(),
            DroneConstant.DISTANCE_COVERED,
            weight,
            DroneConstant.batteryConsumptionMap,
            DroneConstant.distanceCoverageMap);

    if (drone.getBatteryPercentage() < batteryConsumption) {
      throw new NotEnoughBatteryException(DroneExceptionConstant.NOT_ENOUGH_BATTERY);
    }
  }
}
