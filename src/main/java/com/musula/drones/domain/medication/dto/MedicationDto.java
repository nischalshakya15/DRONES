package com.musula.drones.domain.medication.dto;

import com.musula.drones.common.base.BaseDto;
import com.musula.drones.common.enums.State;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MedicationDto extends BaseDto {

  @NotNull(message = "Name is required.")
  @NotBlank(message = "Name cannot be empty.")
  @Pattern(
      regexp = "^[A-Za-z0-9_-]*$",
      message = "Name should contain only letters, numbers, underscore and dash.")
  private String name;

  @NotNull(message = "Weight is required.")
  @Min(value = 1, message = "Weight must be at least 1 gr.")
  @Max(value = 500, message = "Weight should not be more than 500 gr.")
  @Positive(message = "Weight should be positive number and greater than 0.")
  private Integer weight;

  @NotNull(message = "Code is required.")
  @NotBlank(message = "Code cannot be empty.")
  @Pattern(
      regexp = "^[A-Z0-9_]*$",
      message = "Code should contain only upper case letters, underscore and numbers.")
  private String code;

  @NotNull(message = "Image URL is required.")
  @NotBlank(message = "Image URL cannot be empty.")
  private String imageURL;

  @NotNull(message = "Drone serial number is required.")
  @NotBlank(message = "Drone serial number cannot be empty.")
  private String droneSerialNumber;

  private State state;
}
