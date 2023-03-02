package com.musula.drones.service;

import com.musula.drones.dto.DroneMedicationDto;
import com.musula.drones.enums.State;

public interface DroneMedicationService {

  DroneMedicationDto findAllMedicationsByDroneSerialNumberAndMedicationState(
      String serialNumber, State state);
}
