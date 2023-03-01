package com.musula.drones.service.impl;

import com.musula.drones.domain.Drone;
import com.musula.drones.domain.DroneMedication;
import com.musula.drones.domain.Medication;
import com.musula.drones.dto.MedicationDto;
import com.musula.drones.enums.DroneState;
import com.musula.drones.mapper.MedicationMapper;
import com.musula.drones.repository.DroneMedicationRepository;
import com.musula.drones.repository.MedicationRepository;
import com.musula.drones.service.DroneService;
import com.musula.drones.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

  private final MedicationRepository medicationRepository;

  private final MedicationMapper medicationMapper;

  private final DroneService droneService;

  private final DroneMedicationRepository droneMedicationRepository;

  @Override
  public MedicationDto save(MedicationDto medicationDto) {
    Drone drone = droneService.findBySerialNumber(medicationDto.getDroneSerialNumber());
    checkDrone(drone, medicationDto.getWeight());

    Medication medication = medicationMapper.toEntity(medicationDto);
    Medication savedMedication = medicationRepository.save(medication);

    DroneMedication droneMedication = new DroneMedication();
    droneMedication.setDrone(drone);
    droneMedication.setMedication(medication);
    droneMedicationRepository.save(droneMedication);

    return medicationMapper.toDto(savedMedication);
  }

  public void checkDrone(Drone drone, Integer medicationWeight) {
    checkIfTheBatteryIsLessThanTwentyFivePercent(drone.getBatteryPercentage());
    checkIfTheDroneStateIsIdle(drone.getState());
    checkIfTheWeightLimitIsGreaterThanMedicationWeight(drone.getWeightLimit(), medicationWeight);
  }

  private void checkIfTheBatteryIsLessThanTwentyFivePercent(Integer batteryPercentage) {
    if (batteryPercentage < 25) {
      throw new RuntimeException("Invalid battery");
    }
  }

  private void checkIfTheDroneStateIsIdle(DroneState droneState) {
    if (droneState != DroneState.IDLE) {
      throw new RuntimeException("Invalid");
    }
  }

  private void checkIfTheWeightLimitIsGreaterThanMedicationWeight(
      Integer droneWeight, Integer medicationWeight) {
    if (droneWeight < medicationWeight) {
      throw new RuntimeException("Invalid weight");
    }
  }
}
