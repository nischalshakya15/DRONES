package com.musula.drones.domain.medication.dto;

import com.musula.drones.common.base.BaseDto;
import com.musula.drones.common.enums.State;
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
