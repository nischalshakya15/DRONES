package com.musula.drones.domain.medication.mapper;

import com.musula.drones.common.base.BaseMapper;
import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.domain.medication.dto.MedicationDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicationMapper extends BaseMapper<Medication, MedicationDto> {}
