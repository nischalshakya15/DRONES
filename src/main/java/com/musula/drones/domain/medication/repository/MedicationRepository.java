package com.musula.drones.domain.medication.repository;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {

  List<Medication> findAllByIdInAndState(List<Long> ids, State state);

  @Query(
      value =
          "select new com.musula.drones.domain.medication.dto.MedicationDto(m.id, m.name, m.weight, m.code, m.imageURL, d.serialNumber, m.state, m.createdAt, m.updatedAt) "
              + "from Medication m join DroneMedication dm on dm.medicationID = m.id join Drone d on d.id = dm.droneId "
              + "where d.serialNumber = :serialNumber and m.state = :state")
  List<MedicationDto> findAllMedicationsByDroneSerialNumberAndMedicationState(
      String serialNumber, State state);
}
