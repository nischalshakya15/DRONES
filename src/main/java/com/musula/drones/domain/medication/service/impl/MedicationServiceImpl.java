package com.musula.drones.domain.medication.service.impl;

import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.service.DroneService;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.dronemedication.service.DroneMedicationService;
import com.musula.drones.domain.medication.business.MedicationValidation;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.domain.medication.mapper.MedicationMapper;
import com.musula.drones.domain.medication.repository.MedicationRepository;
import com.musula.drones.domain.medication.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationServiceImpl implements MedicationService {

  private final MedicationRepository medicationRepository;

  private final MedicationMapper medicationMapper;

  private final DroneService droneService;

  private final DroneMedicationService droneMedicationService;

  /**
   * The function saves a medication and creates a relationship between the medication and the drone
   *
   * @param medicationDto The medicationDto object that is passed in from the controller.
   * @return MedicationDto
   */
  @Override
  public MedicationDto save(MedicationDto medicationDto) {
    Drone drone = droneService.findBySerialNumber(medicationDto.getDroneSerialNumber());
    MedicationValidation.checkDrone(drone, medicationDto.getWeight());

    Medication medication = saveMedication(medicationDto);

    saveDroneMedication(drone, medication);

    MedicationDto savedMedicationDto = medicationMapper.toDto(medication);
    savedMedicationDto.setDroneSerialNumber(drone.getSerialNumber());
    savedMedicationDto.setState(medication.getState());

    return savedMedicationDto;
  }

  /**
   * It takes a medicationDto, converts it to a medication, sets the state to loaded, and saves it
   * to the database
   *
   * @param medicationDto The DTO object that contains the data to be saved.
   * @return A Medication object
   */
  private Medication saveMedication(MedicationDto medicationDto) {
    Medication medication = medicationMapper.toEntity(medicationDto);
    medication.setState(DroneConstant.LOADING_STATE);
    return medicationRepository.save(medication);
  }

  /**
   * This function saves a new DroneMedication object to the database.
   *
   * @param drone The drone object that we want to save the medication to.
   * @param medication The medication that the drone will be delivering.
   */
  private void saveDroneMedication(Drone drone, Medication medication) {
    DroneMedication droneMedication = new DroneMedication();
    droneMedication.setDrone(drone);
    droneMedication.setMedication(medication);
    droneMedicationService.save(droneMedication);
  }
}
