package com.musula.drones.domain.medication.service.impl;

import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.service.DroneService;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.dronemedication.repository.DroneMedicationRepository;
import com.musula.drones.domain.medication.business.MedicationValidation;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.domain.medication.mapper.MedicationMapper;
import com.musula.drones.domain.medication.repository.MedicationRepository;
import com.musula.drones.domain.medication.service.MedicationService;
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
    MedicationValidation.checkDrone(drone, medicationDto.getWeight());

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

  /**
   * Find all medications by id and state
   *
   * @param ids The list of medication ids to search for.
   * @param state The state of the medication.
   * @return A list of MedicationDto objects.
   */
  @Override
  public List<MedicationDto> findByMedicationIdIn(List<Long> ids, State state) {
    List<Medication> medications = medicationRepository.findAllByIdInAndState(ids, state);
    return medicationMapper.toDto(medications);
  }
}
