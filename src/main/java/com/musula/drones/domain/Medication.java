package com.musula.drones.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "medications")
@EqualsAndHashCode(callSuper = true)
public class Medication extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "weight")
  private Long weight;

  @Column(name = "code")
  private String code;

  @Column(name = "image_url")
  private String imageURL;
}
