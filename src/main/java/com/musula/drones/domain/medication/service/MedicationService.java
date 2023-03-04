package com.musula.drones.domain.medication.service;

import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.common.enums.State;

import java.util.List;

public interface MedicationService {

  MedicationDto save(MedicationDto medicationDto);

  List<MedicationDto> findByMedicationIdIn(List<Long> ids, State state);
}
