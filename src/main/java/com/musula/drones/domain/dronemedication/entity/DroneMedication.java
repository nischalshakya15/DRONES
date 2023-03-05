package com.musula.drones.domain.dronemedication.entity;

import com.musula.drones.common.base.BaseEntity;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.medication.entity.Medication;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "drone_medications")
@EqualsAndHashCode(callSuper = true)
public class DroneMedication extends BaseEntity {

  @OneToOne
  @JoinColumn(name = "drone_id")
  private Drone drone;

  @Column(name = "drone_id", insertable = false, updatable = false)
  private Long droneId;

  @OneToOne
  @JoinColumn(name = "medication_id")
  private Medication medication;

  @Column(name = "medication_id", insertable = false, updatable = false)
  private Long medicationID;
}
