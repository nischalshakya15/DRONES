package com.musula.drones.domain.medication.repository;

import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.common.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

  List<Medication> findAllByIdInAndState(List<Long> ids, State state);
}
