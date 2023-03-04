package com.musula.drones.domain.dronemedication.service;

import com.musula.drones.domain.dronemedication.dto.DroneMedicationDto;
import com.musula.drones.common.enums.State;

public interface DroneMedicationService {

  DroneMedicationDto findAllMedicationsByDroneSerialNumberAndMedicationState(
      String serialNumber, State state);
}
