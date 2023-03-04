package com.musula.drones.domain.drone.service.impl;

import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.enums.State;
import com.musula.drones.common.exception.NotFoundException;
import com.musula.drones.domain.drone.constant.DroneExceptionConstant;
import com.musula.drones.domain.drone.dto.DroneDto;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.mapper.DroneMapper;
import com.musula.drones.domain.drone.repository.DroneRepository;
import com.musula.drones.domain.drone.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DroneServiceImpl implements DroneService {

  private final DroneMapper droneMapper;

  private final DroneRepository droneRepository;

  /**
   * It saves the droneDto to the database.
   *
   * @param droneDto The DroneDto object that is passed in from the controller.
   * @return DroneDto
   */
  @Override
  public DroneDto save(DroneDto droneDto) {
    Drone drone = droneMapper.toEntity(droneDto);
    drone.setWeightLimit(DroneConstant.WEIGHT_LIMIT);
    drone.setBatteryPercentage(DroneConstant.BATTERY_PERCENTAGE);
    drone.setState(DroneConstant.DEFAULT_STATE);

    Drone savedDrone = droneRepository.save(drone);
    return droneMapper.toDto(savedDrone);
  }

  /**
   * Find a drone by its serial number, or throw an exception if it doesn't exist.
   *
   * @param serialNumber The serial number of the drone we want to find.
   * @return A drone object
   */
  @Override
  public Drone findBySerialNumber(String serialNumber) throws NotFoundException {
    return droneRepository
        .findBySerialNumber(serialNumber)
        .orElseThrow(
            () ->
                new NotFoundException(
                    String.format(DroneExceptionConstant.DRONE_NOT_FOUND, serialNumber)));
  }

  /**
   * This function returns a list of DroneDto objects that are in the given state
   *
   * @param droneState The state of the drone.
   * @return A list of DroneDto objects.
   */
  @Override
  public List<DroneDto> findAllByState(State droneState) {
    List<Drone> dronesByState = droneRepository.findAllByState(droneState);
    return droneMapper.toDto(dronesByState);
  }

  /**
   * Find a drone by its serial number and return it as a DTO
   *
   * @param serialNumber The serial number of the drone to be found.
   * @return DroneDto
   */
  @Override
  public DroneDto findDroneBySerialNumber(String serialNumber) {
    Drone drone = findBySerialNumber(serialNumber);
    return droneMapper.toDto(drone);
  }

  /**
   * This function returns a list of all the drones in the database
   *
   * @return A list of DroneDto objects.
   */
  @Override
  public List<DroneDto> findAll() {
    List<Drone> drones = droneRepository.findAll();
    return droneMapper.toDto(drones);
  }
}
