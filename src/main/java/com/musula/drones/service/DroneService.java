package com.musula.drones.service;

import com.musula.drones.domain.Drone;
import com.musula.drones.dto.DroneDto;
import com.musula.drones.enums.State;

import java.util.List;

public interface DroneService {

  DroneDto save(DroneDto droneDto);

  Drone findBySerialNumber(String serialNumber);

  List<DroneDto> findAllByState(State droneState);

  DroneDto findDroneBySerialNumber(String serialNumber);

  List<DroneDto> findAll();
}
