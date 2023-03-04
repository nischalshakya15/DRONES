package com.musula.drones.domain.drone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musula.drones.common.base.BaseDto;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.enums.DroneModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DroneDto extends BaseDto {

  private String serialNumber;

  private DroneModel model;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Integer weightLimit;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private Integer batteryPercentage;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private State state;
}
