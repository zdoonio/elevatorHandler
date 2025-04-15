package org.example.model;

public class ElevatorTrigger {
    private final int targetFloor;
    private final long timestamp;

    public ElevatorTrigger(int floor) {
        this.targetFloor = floor;
        this.timestamp = System.currentTimeMillis();
    }

    public int getTargetFloor() { return targetFloor; }
    public long getTimestamp() { return timestamp; }
}
