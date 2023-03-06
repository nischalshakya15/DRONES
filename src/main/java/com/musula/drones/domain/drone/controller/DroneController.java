package com.musula.drones.domain.drone.controller;

import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.dto.DroneDto;
import com.musula.drones.domain.drone.service.DroneService;
import com.musula.drones.domain.dronemedication.dto.DroneMedicationDto;
import com.musula.drones.domain.dronemedication.service.DroneMedicationService;
import com.musula.drones.domain.medication.dto.MedicationDto;
import com.musula.drones.domain.medication.service.MedicationService;
import jakarta.validation.Valid;
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
public class DroneController {

  private final DroneService droneService;

  private final MedicationService medicationService;

  private final DroneMedicationService droneMedicationService;

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
   * 01. Register a Drone.
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
   * 02. Load a drone with medication items.
   *
   * @param medicationDto This is the object that will be sent to the server.
   * @return A ResponseEntity with a status of 201 (created) and a body of the saved MedicationDto.
   */
  @PostMapping("/medications")
  public ResponseEntity<MedicationDto> save(@RequestBody @Valid MedicationDto medicationDto) {
    return ResponseEntity.created(URI.create("/api/v1/drones/medications"))
        .body(medicationService.save(medicationDto));
  }

  /**
   * 03. Check loaded medication items for a given drone.
   *
   * <p>Take drone serialNumber and state of the medication items.
   *
   * @param serialNumber The serial number of the drone.
   * @param medicationState The state of the medication.
   * @return A list of DroneMedicationDto objects.
   */
  @GetMapping("/medications")
  public ResponseEntity<DroneMedicationDto> findMedicationByDroneSerialNumberAndMedicationState(
      @RequestParam("serialNumber") String serialNumber,
      @RequestParam("medicationState") State medicationState) {
    return ResponseEntity.ok(
        droneMedicationService.findAllMedicationsByDroneSerialNumberAndMedicationState(
            serialNumber, medicationState));
  }

  /**
   * 04. Check available drones for loading.
   *
   * <p>Take State as the parameter to find the drones.
   *
   * @param state State
   * @return A list of DroneDto objects
   */
  @GetMapping("/states")
  public ResponseEntity<List<DroneDto>> findByState(@RequestParam("state") State state) {
    return ResponseEntity.ok(droneService.findAllByState(state));
  }

  /**
   * 05. Check drone battery level for a given drone.
   *
   * <p>Take the Drone serialNumber to find all the information of the Drone.
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
