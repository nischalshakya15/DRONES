package com.musula.drones.domain.dronemedication.repository;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {

  @Query(
      "select dm from DroneMedication dm join Medication m on dm.medicationID = m.id where not m.state = :state")
  List<DroneMedication> findAllByMedicationStateNot(State state);
}
