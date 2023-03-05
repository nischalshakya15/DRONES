package com.musula.drones.schedule;

import com.musula.drones.common.enums.State;
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

  private final MedicationRepository medicationRepository;

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
    List<Medication> medications = medicationRepository.findAllByStateNot(State.DELIVERED);
    for (Medication medication : medications) {
      State currentState = medication.getState();
      if (stateMap.containsKey(currentState)) {
        State nextState = stateMap.get(currentState);
        medication.setState(nextState);
        medicationRepository.save(medication);
      }
    }
    Thread.sleep(10000);
  }
}
