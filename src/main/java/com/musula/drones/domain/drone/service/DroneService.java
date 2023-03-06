package com.musula.drones.domain.drone.service;

import com.musula.drones.common.enums.State;
import com.musula.drones.common.exception.NotFoundException;
import com.musula.drones.domain.drone.dto.DroneDto;
import com.musula.drones.domain.drone.entity.Drone;

import java.util.List;

public interface DroneService {

  DroneDto save(DroneDto droneDto);

  Drone saveDrone(Drone drone);

  Drone findBySerialNumber(String serialNumber) throws NotFoundException;

  List<DroneDto> findAllByState(State droneState);

  DroneDto findDroneBySerialNumber(String serialNumber);

  List<DroneDto> findAll();
}
