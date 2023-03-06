package com.musula.drones.domain.drone.repository;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.entity.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

  Optional<Drone> findBySerialNumber(String serialNumber);

  List<Drone> findAllByState(State droneState);

  List<Drone> findAllByStateAndBatteryPercentageLessThan(State droneState, Double batterPercentage);
}
