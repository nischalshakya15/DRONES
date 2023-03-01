package com.musula.drones.repository;

import com.musula.drones.domain.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Drone, Long> {}
