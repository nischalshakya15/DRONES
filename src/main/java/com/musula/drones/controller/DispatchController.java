package com.musula.drones.controller;

import com.musula.drones.dto.DroneDto;
import com.musula.drones.dto.MedicationDto;
import com.musula.drones.enums.DroneState;
import com.musula.drones.service.DroneService;
import com.musula.drones.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/drones")
public class DispatchController {

  private final DroneService droneService;

  private final MedicationService medicationService;

  /**
   * This function returns a list of all drones in the database
   *
   * @return A list of DroneDto objects
   */
  @GetMapping
  public ResponseEntity<List<DroneDto>> findAll() {
    return ResponseEntity.ok(droneService.findAll());
  }

  /**
   * It takes a DroneDto object as a parameter, saves it, and returns a ResponseEntity with a status
   * code of 201 (created) and the saved DroneDto object
   *
   * @param droneDto The object that will be sent to the server.
   * @return A ResponseEntity with a status of 201 (created) and a body of the DroneDto that was
   *     saved.
   */
  @PostMapping
  public ResponseEntity<DroneDto> save(@RequestBody DroneDto droneDto) {
    return ResponseEntity.created(URI.create("/api/v1/drones")).body(droneService.save(droneDto));
  }

  /**
   * The function is a POST request to the /medications endpoint. It takes a MedicationDto object as
   * a parameter and returns a MedicationDto object
   *
   * @param medicationDto This is the object that will be sent to the server.
   * @return A ResponseEntity with a status of 201 (created) and a body of the saved MedicationDto.
   */
  @PostMapping("/medications")
  public ResponseEntity<MedicationDto> save(@RequestBody MedicationDto medicationDto) {
    return ResponseEntity.created(URI.create("/api/v1/drones/medications"))
        .body(medicationService.save(medicationDto));
  }

  /**
   * "Find all drones with the given state and return them as a list of DroneDto objects."
   *
   * @param state DroneState
   * @return A list of DroneDto objects
   */
  @GetMapping("/states")
  public ResponseEntity<List<DroneDto>> findByState(@RequestParam("state") DroneState state) {
    return ResponseEntity.ok(droneService.findAllByState(state));
  }

  /**
   * "Find a drone by its serial number and return it as a DroneDto."
   *
   * @param serialNumber The serial number of the drone you want to find.
   * @return A DroneDto object
   */
  @GetMapping("/serial-numbers")
  public ResponseEntity<DroneDto> findDroneBySerialNumber(
      @RequestParam("serialNumber") String serialNumber) {
    return ResponseEntity.ok(droneService.findDroneBySerialNumber(serialNumber));
  }
}
