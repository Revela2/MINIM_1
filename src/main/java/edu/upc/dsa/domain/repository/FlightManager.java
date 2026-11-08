package edu.upc.dsa.domain.repository;


import edu.upc.dsa.domain.models.FlightPlan;
import edu.upc.dsa.domain.models.Drone;
import edu.upc.dsa.domain.models.Pilot;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FlightManager {

    void addPilot(String nif, String name, String surname, float FlightHours);
    void addFlightPlan(FlightPlan flightPlan);
    void addDrone(String id, String name, String manufacturer, String model, float FlightHours);
    void addDroneToWarehouse(String id);
    void doMaintenance();
    void createFlightPlan(String PilotId, String DroneId, LocalDateTime Start, int hours, List<Map.Entry<Double, Double>> positions);

    void clear();


    List<Drone> getDronesByFlightHours();
    HashMap<String, Pilot> getPilotsByFlightHours();
    Pilot getPilot(String id);
    Drone getDroneById(String id);
    List<Drone> getDrones();
    List<Pilot> getPilots();


    int numPilots();
    int numDrones();
    int numFlightPlans();
    int numDronesInWarehouse();

    boolean isPilotIntervalInFlightPlanList(String pilotId, LocalDateTime targetStart, LocalDateTime targetEnd);
    boolean isDroneIntervalInFlightPlanList(String droneId, LocalDateTime targetStart, LocalDateTime targetEnd);
}
