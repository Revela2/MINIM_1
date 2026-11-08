package edu.upc.dsa.domain.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FlightPlan {

    private String pilotId;
    private String droneId;
    private LocalDateTime planTime;
    private int hours;
    private List<Map.Entry<Double, Double>> positions;

    public FlightPlan() {}

    public FlightPlan(String pilotId, String droneId, LocalDateTime planTime, int hours, List<Map.Entry<Double, Double>> positions) {
        this.pilotId = pilotId;
        this.droneId = droneId;
        this.planTime = planTime;
        this.hours = hours;
        this.positions = positions;
    }

    public String getPilotId() {
        return pilotId;
    }

    public void setPilotId(String pilotId) {
        this.pilotId = pilotId;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public LocalDateTime getStartTime() {
        return planTime;
    }

    public void setStartTime(LocalDateTime t) {
        this.planTime = t;
    }

    public LocalDateTime getEndTime() {
        return planTime.plusHours(hours);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int h) {
        this.hours = h;
    }

    public List<Map.Entry<Double, Double>> getPositions() {
        return positions;
    }

    public void setPositions(List<Map.Entry<Double, Double>> positions) {
        this.positions = positions;
    }
}