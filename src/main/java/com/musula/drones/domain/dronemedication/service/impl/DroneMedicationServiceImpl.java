package com.musula.drones.domain.dronemedication.service.impl;

import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.dronemedication.service.DroneMedicationService;
import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.domain.dronemedication.dto.DroneMedicationDto;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.mapper.DroneMapper;
import com.musula.drones.domain.dronemedication.repository.DroneMedicationRepository;
import com.musula.drones.domain.drone.service.DroneService;
import com.musula.drones.domain.medication.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DroneMedicationServiceImpl implements DroneMedicationService {

  private final DroneService droneService;

  private final MedicationService medicationService;

  private final DroneMedicationRepository droneMedicationRepository;

  private final DroneMapper droneMapper;

  /**
   * Find all medications by drone serial number and medication state
   *
   * @param serialNumber The serial number of the drone
   * @param state The state of the medication.
   * @return A list of medications that are in a specific state.
   */
  @Override
  public DroneMedicationDto findAllMedicationsByDroneSerialNumberAndMedicationState(
      String serialNumber, State state) {
    Drone drone = droneService.findBySerialNumber(serialNumber);
    List<DroneMedication> droneMedications = droneMedicationRepository.findByDroneId(drone.getId());
    List<Long> medicationIds =
        droneMedications.stream()
            .map(DroneMedication::getMedication)
            .map(Medication::getId)
            .collect(Collectors.toList());

    List<MedicationDto> medicationsDtos =
        medicationService.findByMedicationIdIn(medicationIds, state);

    DroneMedicationDto droneMedicationDto = new DroneMedicationDto();
    droneMedicationDto.setSerialNumber(drone.getSerialNumber());
    droneMedicationDto.setMedications(medicationsDtos);
    return droneMedicationDto;
  }
}