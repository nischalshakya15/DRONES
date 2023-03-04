package com.musula.drones.domain.drone.dto;

import com.musula.drones.common.base.BaseDto;
import com.musula.drones.domain.drone.enums.DroneModel;
import com.musula.drones.common.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DroneDto extends BaseDto {

  private String serialNumber;

  private DroneModel model;

  private Integer weightLimit;

  private Integer batteryPercentage;

  private State state;
}
