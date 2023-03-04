package com.musula.drones.domain.medication.mapper;

import com.musula.drones.common.base.BaseMapper;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicationMapper extends BaseMapper<Medication, MedicationDto> {

  @Mapping(target = "droneSerialNumber", source = "serialNumber")
  MedicationDto toDto(Medication medication, String serialNumber);
}
