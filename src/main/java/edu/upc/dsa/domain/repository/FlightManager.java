package edu.upc.dsa.domain.repository;

import edu.upc.dsa.domain.models.Drone;
import edu.upc.dsa.domain.models.FlightPlan;
import edu.upc.dsa.domain.models.Pilot;
import edu.upc.dsa.domain.models.Position;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public interface FlightManager {

    void addPilot(String id, String name, String surname, float flightHours);
    void addDrone(String id, String name, String manufacturer, String model, float flightHours);

    List<Drone> getDrones();
    List<Pilot> getPilots();
    List<Drone> getDronesByFlightHours();
    HashMap<String, Pilot> getPilotsByFlightHours();
    Drone getDroneById(String id);
    Pilot getPilot(String id);

    void addDroneToWarehouse(String id);
    void doMaintenance();

    void createFlightPlan(String pilotId, String droneId, LocalDateTime start, int hours, List<Position> positions);
    boolean isPilotIntervalInFlightPlanList(String pilotId, LocalDateTime targetStart, LocalDateTime targetEnd);
    boolean isDroneIntervalInFlightPlanList(String droneId, LocalDateTime targetStart, LocalDateTime targetEnd);

    int numPilots();
    int numDrones();
    int numDronesInWarehouse();
    int numFlightPlans();

    void clear();
}