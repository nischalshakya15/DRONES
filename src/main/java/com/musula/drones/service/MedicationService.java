package com.musula.drones.service;

import com.musula.drones.dto.MedicationDto;
import com.musula.drones.enums.State;

import java.util.List;

public interface MedicationService {

  MedicationDto save(MedicationDto medicationDto);

  List<MedicationDto> findByMedicationIdIn(List<Long> ids, State state);
}
