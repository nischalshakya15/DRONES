package com.musula.drones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

  @Column(name = "weight_limit")
  private Long weightLimit;

  @Column(name = "battery_percentage")
  private Long batteryPercentage;

  @Column(name = "state")
  private String state;
}
