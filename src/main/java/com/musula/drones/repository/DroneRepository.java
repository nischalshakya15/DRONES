package com.musula.drones.repository;

import com.musula.drones.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

  Optional<Drone> findBySerialNumber(String serialNumber);
}
