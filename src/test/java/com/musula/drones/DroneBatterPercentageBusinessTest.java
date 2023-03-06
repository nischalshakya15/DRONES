package com.musula.drones;

import com.musula.drones.business.DroneBatteryPercentageBusiness;
import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.exception.drone.NotEnoughBatteryException;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.enums.DroneModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class DroneBatterPercentageBusinessTest {

  @Autowired private DroneBatteryPercentageBusiness droneBatteryPercentageBusiness;

  private final List<DroneModel> droneModels =
      List.of(
          DroneModel.LIGHT_WEIGHT,
          DroneModel.MIDDLE_WEIGHT,
          DroneModel.HEAVY_WEIGHT,
          DroneModel.CRUISER_WEIGHT);

  private final Map<DroneModel, Double> droneModelBatteryConsumptionMap =
      Map.of(
          DroneModel.LIGHT_WEIGHT,
          49.0d,
          DroneModel.MIDDLE_WEIGHT,
          42.0d,
          DroneModel.HEAVY_WEIGHT,
          35.0d,
          DroneModel.CRUISER_WEIGHT,
          56.0d);

  /** This function tests the battery consumption percentage for every drone model. */
  @Test
  void assertBatteryConsumptionPercentageForEveryDrone() {
    for (DroneModel droneModel : droneModels) {
      Double batteryConsumptionPercentage =
          droneBatteryPercentageBusiness.getBatteryConsumptionPercentage(
              droneModel,
              DroneConstant.DISTANCE_COVERED,
              500,
              DroneConstant.batteryConsumptionMap,
              DroneConstant.distanceCoverageMap);
      Assertions.assertEquals(
          droneModelBatteryConsumptionMap.get(droneModel), batteryConsumptionPercentage);
    }
  }

  /** It checks if the drone has enough battery to cover the distance and carry the weight. */
  @Test
  void assertThrowsNotEnoughBatteryException() {
    for (DroneModel droneModel : droneModels) {
      Drone drone = new Drone();
      drone.setModel(droneModel);
      drone.setBatteryPercentage(30.0d);

      Assertions.assertThrows(
          NotEnoughBatteryException.class,
          () ->
              droneBatteryPercentageBusiness.checkBatteryPercentage(
                  drone,
                  DroneConstant.DISTANCE_COVERED,
                  DroneConstant.WEIGHT_LIMIT,
                  DroneConstant.batteryConsumptionMap,
                  DroneConstant.distanceCoverageMap));
    }
  }

  /** It checks if the drone has enough battery to cover the distance and carry the weight. */
  @Test
  void assertThrowsDoesNotEnoughBatteryException() {
    for (DroneModel droneModel : droneModels) {
      Drone drone = new Drone();
      drone.setModel(droneModel);
      drone.setBatteryPercentage(90.0d);

      Assertions.assertDoesNotThrow(
          () ->
              droneBatteryPercentageBusiness.checkBatteryPercentage(
                  drone,
                  DroneConstant.DISTANCE_COVERED,
                  DroneConstant.WEIGHT_LIMIT,
                  DroneConstant.batteryConsumptionMap,
                  DroneConstant.distanceCoverageMap));
    }
  }

  /** Check if the batterPercentage is set to 65 and isCharged to false. */
  @Test
  void assertSetBatteryPercentageAndIsChargingTrue() {
    Drone drone = new Drone();
    drone.setModel(DroneModel.LIGHT_WEIGHT);
    drone.setBatteryPercentage(55.0d);

    droneBatteryPercentageBusiness.setBatteryPercentageAndIsCharging(
        drone, DroneConstant.BATTERY_TO_RECHARGE_BY, DroneConstant.TOTAL_BATTERY_PERCENTAGE);

    Assertions.assertEquals(65.0d, drone.getBatteryPercentage());
    Assertions.assertTrue(drone.isCharging());
  }

  /** Check if the batteryPercentage is set to 100 and isCharged to true. */
  @Test
  void assertSetBatteryPercentageAndIsChargingFalse() {
    Drone drone = new Drone();
    drone.setModel(DroneModel.HEAVY_WEIGHT);
    drone.setBatteryPercentage(95.0d);

    droneBatteryPercentageBusiness.setBatteryPercentageAndIsCharging(
        drone, DroneConstant.BATTERY_TO_RECHARGE_BY, DroneConstant.TOTAL_BATTERY_PERCENTAGE);

    Assertions.assertEquals(100.0d, drone.getBatteryPercentage());
    Assertions.assertFalse(drone.isCharging());
  }
}
