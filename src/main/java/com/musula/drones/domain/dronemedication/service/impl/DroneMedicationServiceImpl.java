package com.musula.drones.domain.dronemedication.service.impl;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.service.DroneService;
import com.musula.drones.domain.dronemedication.dto.DroneMedicationDto;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.dronemedication.repository.DroneMedicationRepository;
import com.musula.drones.domain.dronemedication.service.DroneMedicationService;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.mapper.MedicationMapper;
import com.musula.drones.domain.medication.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DroneMedicationServiceImpl implements DroneMedicationService {

  private final DroneMedicationRepository droneMedicationRepository;

  private final DroneService droneService;

  private final MedicationRepository medicationRepository;

  private final MedicationMapper medicationMapper;

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
    List<MedicationDto> medicationDtos =
        medicationRepository.findAllMedicationsByDroneSerialNumberAndMedicationState(
            drone.getSerialNumber(), state);

    DroneMedicationDto droneMedicationDto = new DroneMedicationDto();
    droneMedicationDto.setId(drone.getId());
    droneMedicationDto.setCreatedAt(drone.getCreatedAt());
    droneMedicationDto.setUpdatedAt(drone.getUpdatedAt());
    droneMedicationDto.setSerialNumber(drone.getSerialNumber());
    droneMedicationDto.setModel(drone.getModel());
    droneMedicationDto.setWeightLimit(drone.getWeightLimit());
    droneMedicationDto.setBatteryPercentage(drone.getBatteryPercentage());
    droneMedicationDto.setState(drone.getState());
    droneMedicationDto.setMedications(medicationDtos);

    return droneMedicationDto;
  }

  /**
   * The function takes in a DroneMedication object and saves it to the database
   *
   * @param droneMedication The DroneMedication object that is being saved.
   */
  @Override
  public void save(DroneMedication droneMedication) {
    droneMedicationRepository.save(droneMedication);
  }
}
