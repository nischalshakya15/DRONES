package com.musula.drones.service;

import com.musula.drones.domain.Drone;
import com.musula.drones.dto.DroneDto;

public interface DroneService {

  DroneDto save(DroneDto droneDto);

  Drone findBySerialNumber(String serialNumber);
}
