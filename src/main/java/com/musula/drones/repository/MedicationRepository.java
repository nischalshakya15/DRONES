package com.musula.drones.repository;

import com.musula.drones.domain.Medication;
import com.musula.drones.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

  List<Medication> findAllByIdInAndState(List<Long> ids, State state);
}
