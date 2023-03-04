package com.musula.drones.domain.drone.service;

import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.dto.DroneDto;
import com.musula.drones.common.enums.State;

import java.util.List;

public interface DroneService {

  DroneDto save(DroneDto droneDto);

  Drone findBySerialNumber(String serialNumber);

  List<DroneDto> findAllByState(State droneState);

  DroneDto findDroneBySerialNumber(String serialNumber);

  List<DroneDto> findAll();
}
