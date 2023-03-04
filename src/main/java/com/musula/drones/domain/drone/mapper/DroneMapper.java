package com.musula.drones.domain.drone.mapper;

import com.musula.drones.common.base.BaseMapper;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.dto.DroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DroneMapper extends BaseMapper<Drone, DroneDto> {}
