package com.musula.drones.schedule;

import com.musula.drones.business.DroneBatteryPercentageBusiness;
import com.musula.drones.business.MedicationBusiness;
import com.musula.drones.common.constant.DroneConstant;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.entity.Drone;
import com.musula.drones.domain.drone.repository.DroneRepository;
import com.musula.drones.domain.dronemedication.entity.DroneMedication;
import com.musula.drones.domain.dronemedication.repository.DroneMedicationRepository;
import com.musula.drones.domain.medication.entity.Medication;
import com.musula.drones.domain.medication.repository.MedicationRepository;
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
public class UpdateMedicationStatus {

  private final DroneRepository droneRepository;

  private final MedicationRepository medicationRepository;

  private final DroneMedicationRepository droneMedicationRepository;

  private final DroneBatteryPercentageBusiness droneBatteryPercentageBusiness;

  private final MedicationBusiness medicationBusiness;

  /**
   * It updates the state of the medication and drone based on the current state of the medication
   * and drone
   */
  @Scheduled(fixedDelay = 5000)
  public void updateMedicationStatus() throws InterruptedException {
    List<DroneMedication> medications =
        droneMedicationRepository.findAllByMedicationStateNot(State.DELIVERED);

    for (DroneMedication droneMedication : medications) {
      Medication medication = droneMedication.getMedication();
      Drone drone = droneMedication.getDrone();

      medicationBusiness.setNextMedicationState(medication, DroneConstant.stateMap);
      medicationRepository.save(medication);

      drone.setState(medication.getState());
      droneRepository.save(drone);

      if (medication.getState().equals(State.DELIVERED)
          && drone.getState().equals(State.DELIVERED)) {
        drone.setState(State.RETURNING);
        droneRepository.save(drone);
      }

      if (medication.getState().equals(State.DELIVERED)
          && drone.getState().equals(State.RETURNING)) {
        drone.setState(State.IDLE);

        Double batteryConsumptionPercentage =
            droneBatteryPercentageBusiness.getBatteryConsumptionPercentage(
                drone.getModel(),
                DroneConstant.DISTANCE_COVERED,
                medication.getWeight(),
                DroneConstant.batteryConsumptionMap,
                DroneConstant.distanceCoverageMap);

        drone.setBatteryPercentage(Math.abs(drone.getBatteryPercentage() - batteryConsumptionPercentage));
        droneRepository.save(drone);
      }
    }

    Thread.sleep(1000);
  }
}
