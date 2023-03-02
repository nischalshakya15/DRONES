package com.musula.drones.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DroneMedicationDto extends DroneDto {

  private List<MedicationDto> medications;
}
