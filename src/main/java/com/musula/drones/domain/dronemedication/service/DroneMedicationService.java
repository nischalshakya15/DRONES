package com.musula.drones.domain.dronemedication.service;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.dronemedication.dto.DroneMedicationDto;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;

public interface DroneMedicationService {

  DroneMedicationDto findAllMedicationsByDroneSerialNumberAndMedicationState(
      String serialNumber, State state);

  void save(DroneMedication droneMedication);
}
