package com.musula.drones.common.constant;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.enums.DroneModel;

import java.util.Map;

public class DroneConstant {

  public static final Integer WEIGHT_LIMIT = 500;

  public static final State DEFAULT_STATE = State.IDLE;

  public static final State LOADED_STATE = State.LOADED;

  public static final State LOADING_STATE = State.LOADING;

  public static final Integer DISTANCE_COVERED = 1000;

  public static final Double TOTAL_BATTERY_PERCENTAGE = 100.0d;

  public static final Double MINIMUM_BATTERY_PERCENTAGE = 25.0d;

  public static final Double BATTERY_TO_RECHARGE_BY = 10.0d;

  public static final Map<State, State> stateMap =
      Map.of(
          State.LOADING,
          State.LOADED,
          State.LOADED,
          State.DELIVERING,
          State.DELIVERING,
          State.DELIVERED);

  public static final Map<DroneModel, Double> batteryConsumptionMap =
      Map.of(
          DroneModel.LIGHT_WEIGHT,
          0.007,
          DroneModel.MIDDLE_WEIGHT,
          0.006,
          DroneModel.HEAVY_WEIGHT,
          0.005,
          DroneModel.CRUISER_WEIGHT,
          0.008);

  public static final Map<DroneModel, Double> distanceCoverageMap =
      Map.of(
          DroneModel.LIGHT_WEIGHT,
          0.07,
          DroneModel.MIDDLE_WEIGHT,
          0.06,
          DroneModel.HEAVY_WEIGHT,
          0.05,
          DroneModel.CRUISER_WEIGHT,
          0.08);
}
