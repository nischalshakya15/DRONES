package com.musula.drones.domain.dronemedication.dto;

import com.musula.drones.domain.drone.dto.DroneDto;
import com.musula.drones.domain.medication.dto.MedicationDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class DroneMedicationDto extends DroneDto {

  private List<MedicationDto> medications;

}
