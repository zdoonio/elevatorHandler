package org.example.handler;

import org.example.model.ElevatorModel;
import org.example.model.ElevatorTrigger;

import java.util.*;

public class QueueHandler {
    private List<ElevatorModel> elevators = new ArrayList<>();
    private Queue<ElevatorTrigger> globalRequests = new PriorityQueue<>(
            Comparator.comparingLong(ElevatorTrigger::getTimestamp)
    );

    public QueueHandler(int numberOfElevators) {
        for (int i = 0; i < numberOfElevators; i++) {
            elevators.add(new ElevatorModel());
        }
    }

    // Metoda dodająca nowe żądanie
    public void addTrigger(ElevatorTrigger trigger) {
        globalRequests.add(trigger);
        assignToNearestElevator(trigger);
    }

    // Metoda przypisująca żądanie do najbliższej windy
    private void assignToNearestElevator(ElevatorTrigger trigger) {
        ElevatorModel nearest = elevators.stream()
                .min(Comparator.comparingInt(e ->
                        Math.abs(e.getCurrentFloor() - trigger.getTargetFloor())))
                .orElseThrow();

        nearest.addToQueue(trigger.getTargetFloor());
    }

    // Metoda sprawdzająca zmiany kierunku
    public boolean isAnyDirectionChanged() {
        return elevators.stream().anyMatch(ElevatorModel::isDirectionChanged);
    }

    // Metoda aktualizująca stan wszystkich wind
    public void updateElevators() {
        elevators.forEach(ElevatorModel::processNextFloor);
    }

    // Metoda diagnostyczna
    public void printStatus() {
        for (int i = 0; i < elevators.size(); i++) {
            ElevatorModel elevator = elevators.get(i);
            System.out.printf("Winda %d: %s | Piętro: %d | Kolejka: %s%n",
                    i,
                    elevator.getCurrentDirection(),
                    elevator.getCurrentFloor(),
                    elevator.getRequestQueue());
        }
    }
}
