package com.musula.drones.repository;

import com.musula.drones.domain.DroneMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {}
