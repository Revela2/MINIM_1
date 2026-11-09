package edu.upc.dsa.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class FlightPlan {

    private String pilotId;
    private String droneId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][X]")
    private LocalDateTime startTime;
    private int hours;
    private List<Position> positions;

    public FlightPlan() {}

    public FlightPlan(String pilotId, String droneId, LocalDateTime startTime, int hours, List<Position> positions) {
        this.pilotId = pilotId;
        this.droneId = droneId;
        this.startTime = startTime;
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
        return startTime;
    }

    public void setStartTime(LocalDateTime t) {
        this.startTime = t;
    }

    public LocalDateTime endTime() {
        return startTime.plusHours(hours);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int h) {
        this.hours = h;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}