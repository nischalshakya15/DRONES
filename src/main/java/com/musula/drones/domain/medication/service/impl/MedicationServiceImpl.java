package com.musula.drones.domain.medication.service.impl;

import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.mapper.MedicationMapper;
import com.musula.drones.domain.medication.repository.MedicationRepository;
import com.musula.drones.domain.medication.service.MedicationService;
import com.musula.drones.domain.dronemedication.repository.DroneMedicationRepository;
import com.musula.drones.domain.drone.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

  private final MedicationRepository medicationRepository;

  private final MedicationMapper medicationMapper;

  private final DroneService droneService;

  private final DroneMedicationRepository droneMedicationRepository;

  /**
   * The function saves a medication and creates a relationship between the medication and the drone
   *
   * @param medicationDto The medicationDto object that is passed in from the controller.
   * @return MedicationDto
   */
  @Override
  public MedicationDto save(MedicationDto medicationDto) {
    Drone drone = droneService.findBySerialNumber(medicationDto.getDroneSerialNumber());
    checkDrone(drone, medicationDto.getWeight());

    Medication medication = medicationMapper.toEntity(medicationDto);
    medication.setState(DroneConstant.LOADED_STATE);
    Medication savedMedication = medicationRepository.save(medication);

    DroneMedication droneMedication = new DroneMedication();
    droneMedication.setDrone(drone);
    droneMedication.setMedication(medication);
    droneMedicationRepository.save(droneMedication);

    MedicationDto savedMedicationDto = medicationMapper.toDto(savedMedication);
    savedMedicationDto.setDroneSerialNumber(drone.getSerialNumber());
    savedMedicationDto.setState(savedMedication.getState());

    return savedMedicationDto;
  }

  @Override
  public List<MedicationDto> findByMedicationIdIn(List<Long> ids, State state) {
    List<Medication> medications = medicationRepository.findAllByIdInAndState(ids, state);
    return medicationMapper.toDto(medications);
  }

  /**
   * Check if the drone is ready to deliver medication.
   *
   * @param drone The drone that will be used to deliver the medication.
   * @param medicationWeight The weight of the medication that the drone is carrying.
   */
  public void checkDrone(Drone drone, Integer medicationWeight) {
    checkIfTheBatteryIsLessThanTwentyFivePercent(drone.getBatteryPercentage());
    checkIfTheDroneStateIsIdle(drone.getState());
    checkIfTheWeightLimitIsGreaterThanMedicationWeight(drone.getWeightLimit(), medicationWeight);
  }

  /**
   * Check if the battery is less than 25%.
   *
   * @param batteryPercentage The percentage of the battery.
   */
  private void checkIfTheBatteryIsLessThanTwentyFivePercent(Integer batteryPercentage) {
    if (batteryPercentage < 25) {
      throw new RuntimeException("Invalid battery");
    }
  }

  /**
   * Check if the drone state is idle.
   *
   * @param droneState The current state of the drone.
   */
  private void checkIfTheDroneStateIsIdle(State droneState) {
    if (droneState != State.IDLE) {
      throw new RuntimeException("Invalid");
    }
  }

  /**
   * Check if the weight limit is greater than medication weight.
   *
   * @param droneWeight The weight of the drone
   * @param medicationWeight The weight of the medication that the drone is carrying.
   */
  private void checkIfTheWeightLimitIsGreaterThanMedicationWeight(
      Integer droneWeight, Integer medicationWeight) {
    if (droneWeight < medicationWeight) {
      throw new RuntimeException("Invalid weight");
    }
  }
}
