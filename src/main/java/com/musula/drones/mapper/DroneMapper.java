package com.musula.drones.mapper;

import com.musula.drones.domain.Drone;
import com.musula.drones.dto.DroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DroneMapper extends BaseMapper<Drone, DroneDto> {}
