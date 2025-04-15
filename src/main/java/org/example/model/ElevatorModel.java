package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ElevatorModel {
    private Queue<Integer> requestQueue = new LinkedList<>();
    private Direction currentDirection = Direction.NONE;
    private int currentFloor = 0;
    private boolean isDirectionChanged = false;
    private boolean isActive = false;

    // Metoda dodająca nowe piętro do kolejki
    public void addToQueue(int floor) {
        requestQueue.add(floor);
        updateDirection(floor);
    }

    // Metoda aktualizująca kierunek ruchu
    private void updateDirection(int newFloor) {
        Direction newDirection = currentDirection;

        if (requestQueue.size() == 1) {
            newDirection = (newFloor > currentFloor) ? Direction.UP :
                    (newFloor < currentFloor) ? Direction.DOWN : Direction.NONE;
        } else {
            int nextFloor = requestQueue.peek();
            newDirection = (nextFloor > currentFloor) ? Direction.UP :
                    (nextFloor < currentFloor) ? Direction.DOWN : Direction.NONE;
        }

        if (currentDirection != newDirection) {
            isDirectionChanged = true;
            currentDirection = newDirection;
        }
    }

    // Metoda przetwarzająca kolejne żądanie
    public void processNextFloor() {
        if (!requestQueue.isEmpty()) {
            int nextFloor = requestQueue.poll();
            currentFloor = nextFloor;
            isActive = !requestQueue.isEmpty();
        }
    }

    // Gettery i settery
    public boolean isDirectionChanged() { return isDirectionChanged; }
    public Direction getCurrentDirection() { return currentDirection; }
    public int getCurrentFloor() { return currentFloor; }
    public boolean isActive() { return isActive; }
    public Queue<Integer> getRequestQueue() { return requestQueue; }
}
