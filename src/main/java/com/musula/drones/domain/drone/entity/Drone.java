package com.musula.drones.domain.drone.entity;

import com.musula.drones.common.base.BaseEntity;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.enums.DroneModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "drones")
@EqualsAndHashCode(callSuper = true)
public class Drone extends BaseEntity {

  @Column(name = "serial_number", length = 100, unique = true)
  private String serialNumber;

  @Enumerated(EnumType.STRING)
  @Column(name = "model", length = 100)
  private DroneModel model;

  @Column(name = "weight_limit")
  private Integer weightLimit;

  @Column(name = "battery_percentage")
  private Double batteryPercentage;

  @Enumerated(EnumType.STRING)
  @Column(name = "state", length = 100)
  private State state;
}
