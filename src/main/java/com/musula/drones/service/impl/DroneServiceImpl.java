package com.musula.drones.service.impl;

import com.musula.drones.constant.DroneConstant;
import com.musula.drones.domain.Drone;
import com.musula.drones.dto.DroneDto;
import com.musula.drones.mapper.DroneMapper;
import com.musula.drones.repository.DroneRepository;
import com.musula.drones.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    Drone drone = droneRepository.save(droneMapper.toEntity(droneDto));
    drone.setWeightLimit(DroneConstant.WEIGHT_LIMIT);
    drone.setBatteryPercentage(DroneConstant.BATTERY_PERCENTAGE);
    drone.setState(DroneConstant.DEFAULT_STATE);
    return droneMapper.toDto(drone);
  }
}
