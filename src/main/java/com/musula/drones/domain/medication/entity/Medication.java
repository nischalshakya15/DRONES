package com.musula.drones.domain.medication.entity;

import com.musula.drones.common.base.BaseEntity;
import com.musula.drones.common.enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "medications")
@EqualsAndHashCode(callSuper = true)
public class Medication extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "weight")
  private Integer weight;

  @Column(name = "code")
  private String code;

  @Column(name = "image_url")
  private String imageURL;

  @Enumerated(EnumType.STRING)
  @Column(name = "state")
  private State state;
}
