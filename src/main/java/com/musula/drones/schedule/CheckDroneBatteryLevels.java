package com.musula.drones.schedule;

import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@EnableAsync
@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class CheckDroneBatteryLevels {

  private final DroneRepository droneRepository;

  /**
   * It will run every 5 seconds and print the serial number and battery percentage of all the
   * drones in the database
   */
  @Scheduled(fixedRate = 5000)
  public void scheduleBatteryLevelCheck() {
    List<Drone> drones = droneRepository.findAll();
    for (Drone drone : drones) {
      log.info(
          "Model: {}, Serial Number: {}, Battery Level: {}, and State: {}",
          drone.getModel(),
          drone.getSerialNumber(),
          drone.getBatteryPercentage(),
          drone.getState());
    }
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      log.error("Interrupted exception with {}.", e.getMessage());
    }
  }
}
