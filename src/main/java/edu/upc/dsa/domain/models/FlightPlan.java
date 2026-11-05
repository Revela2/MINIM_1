package edu.upc.dsa.domain.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FlightPlan {

    private Pilot pilot;
    private Drone drone;
    private LocalDateTime planTime;
    private int hours;
    private List<Map.Entry<Double, Double>> positions;

    public FlightPlan(Pilot p, Drone d, LocalDateTime planTime, int hours,  List<Map.Entry<Double, Double>> positions) {
        this.pilot = p;
        this.drone = d;
        this.planTime = planTime;
        this.hours = hours;
        this.positions = positions;
    }

    public Pilot getPilot()
    {
        return pilot;
    }

    public Drone getDrone()
    {
        return drone;
    }
    public LocalDateTime getStartTime()
    {
        return planTime;
    }

    public LocalDateTime getEndTime()
    {
        return planTime.plusHours(hours);
    }
}
