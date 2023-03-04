package com.musula.drones.domain.dronemedication.repository;

import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.medication.dto.MedicationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {

  //  List<DroneMedication> findByDroneId(Long droneId);
}
