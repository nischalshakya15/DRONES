package com.musula.drones.dto;

import com.musula.drones.enums.DroneModel;
import com.musula.drones.enums.DroneState;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DroneDto extends BaseDto {

  private String serialNumber;

  private DroneModel model;

  private Integer weightLimit;

  private Integer batteryPercentage;

  private DroneState state;
}
