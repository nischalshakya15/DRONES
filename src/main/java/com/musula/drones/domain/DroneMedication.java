package com.musula.drones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "drone_medications")
@EqualsAndHashCode(callSuper = true)
public class DroneMedication extends BaseEntity {

  @ManyToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  @Column(name = "drone_id", insertable = false, updatable = false)
  private Long droneId;

  @ManyToOne
  @JoinColumn(name = "medication_id")
  private Medication medication;
}
