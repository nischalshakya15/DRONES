package com.musula.drones.controller;

import com.musula.drones.dto.DroneDto;
import com.musula.drones.service.DroneService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/drones")
public class DispatchController {

  private final DroneService droneService;

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
}
