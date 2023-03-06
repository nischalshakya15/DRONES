package com.musula.drones.schedule;

import com.musula.drones.business.DroneBatteryPercentageBusiness;
import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

@EnableAsync
@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class ChargeDroneBattery {

  private final DroneRepository droneRepository;

  private final DroneBatteryPercentageBusiness droneBatteryPercentageBusiness;

  @Scheduled(fixedDelay = 10000)
  public void chargeDrones() {
    List<Drone> drones =
        droneRepository.findAllByStateAndBatteryPercentageLessThan(
            State.IDLE, DroneConstant.TOTAL_BATTERY_PERCENTAGE);

    List<Drone> updatedDrones =
        drones.stream()
            .peek(
                drone ->
                    droneBatteryPercentageBusiness.setBatteryPercentage(
                        drone,
                        DroneConstant.BATTERY_TO_RECHARGE_BY,
                        DroneConstant.TOTAL_BATTERY_PERCENTAGE))
            .collect(Collectors.toList());

    droneRepository.saveAll(updatedDrones);
  }
}
