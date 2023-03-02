package com.musula.drones.dto;

import com.musula.drones.enums.State;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedicationDto extends BaseDto {

  private String name;

  private Integer weight;

  private String code;

  private String imageURL;

  private String droneSerialNumber;

  private State state;
}
