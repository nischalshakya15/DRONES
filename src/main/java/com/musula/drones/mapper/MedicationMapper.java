package com.musula.drones.mapper;

import com.musula.drones.domain.Medication;
import com.musula.drones.dto.MedicationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicationMapper extends BaseMapper<Medication, MedicationDto> {}
