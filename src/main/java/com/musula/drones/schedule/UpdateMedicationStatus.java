package com.musula.drones.schedule;

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
import java.util.Map;

@EnableAsync
@Configuration
@EnableScheduling
@AllArgsConstructor
@Slf4j
public class UpdateMedicationStatus {

  private final DroneRepository droneRepository;

  private final MedicationRepository medicationRepository;

  private final DroneMedicationRepository droneMedicationRepository;

  private static final Map<State, State> stateMap =
      Map.of(
          State.LOADING,
          State.LOADED,
          State.LOADED,
          State.DELIVERED,
          State.DELIVERING,
          State.DELIVERED);

  @Scheduled(fixedDelay = 5000)
  public void updateMedicationStatus() throws InterruptedException {
    List<DroneMedication> medications =
        droneMedicationRepository.findAllByMedicationStateNot(State.DELIVERED);

    for (DroneMedication droneMedication : medications) {
      Medication medication = droneMedication.getMedication();
      Drone drone = droneMedication.getDrone();

      State currentState = medication.getState();
      if (stateMap.containsKey(currentState)) {
        State nextState = stateMap.get(currentState);

        medication.setState(nextState);
        medicationRepository.save(medication);

        drone.setState(nextState);
        droneRepository.save(drone);
      }

      if (medication.getState().equals(State.DELIVERED)
          && drone.getState().equals(State.DELIVERED)) {
        drone.setState(State.RETURNING);
        droneRepository.save(drone);
      }

      if (medication.getState().equals(State.DELIVERED)
          && drone.getState().equals(State.RETURNING)) {
        drone.setState(State.IDLE);
        droneRepository.save(drone);
      }
    }

    Thread.sleep(10000);
  }
}
