package edu.upc.dsa.domain.repository;


import edu.upc.dsa.domain.models.FlightPlan;
import edu.upc.dsa.domain.models.Drone;
import edu.upc.dsa.domain.models.Pilot;
import edu.upc.dsa.util.RandomUtils;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightManagerImpl implements FlightManager {
    private final List<Drone> DroneList;
    private final Queue<String> WarehouseQueue;
    private final List<FlightPlan>  FlightPlanList;
    private final HashMap<String, Pilot> PilotList;
    private static FlightManagerImpl instance;
    static final Logger logger = Logger.getLogger(FlightManagerImpl.class);



    private FlightManagerImpl() {
        DroneList = new ArrayList<>();
        WarehouseQueue = new LinkedList<>();
        PilotList = new HashMap<>();
        FlightPlanList = new LinkedList<>();
    }

    public static FlightManagerImpl getInstance() {
        if (instance == null) instance = new FlightManagerImpl();
        return instance;
    }

    public void clear()
    {
        DroneList.clear();
        WarehouseQueue.clear();
        PilotList.clear();
        FlightPlanList.clear();

    }

    public void addPilot(String id, String name, String surname, float FlightHours) {
        String newId = id.isEmpty() ? RandomUtils.getId() : id;
        if (PilotList.containsKey(newId)) {
            logger.error("Duplicate Pilot ID: " + newId);
            return;
        }
        PilotList.put(newId, new Pilot(name, surname, id, FlightHours));
        logger.info("Pilot " + id + " has been added");
    }

    public void addDrone(String id, String name, String manufacturer, String model, float FlightHours) {
        String newId = id.isEmpty() ? RandomUtils.getId() : id;
        for (Drone d : DroneList)
        {
            if (Objects.equals(d.getId(), newId))
            {
                logger.error("Duplicate Drone ID: " + newId);
                return;
            }
        }

        DroneList.add(new Drone(newId, name, manufacturer, model, FlightHours));
        logger.info("Drone" + id + " has been added");
    }

    public List<Drone> getDronesByFlightHours() {
        List<Drone> res = new LinkedList<Drone> (DroneList);
        res.sort( (d1, d2) -> Double.compare(d2.getFlightHours(), d1.getFlightHours()));
        logger.info("Drones sorted by FlightHours");
        return res;
    }

    public HashMap<String, Pilot> getPilotsByFlightHours() {
        HashMap<String, Pilot> res = new HashMap<>(PilotList);
        logger.info("Pilots sorted by FlightHours");
        return res.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(
                        Comparator.comparingDouble(Pilot::getFlightHours).reversed()
                ))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new
                ));
    }

    public void addDroneToWarehouse(String id)
    {
        for (Drone d : DroneList)
        {
            if (Objects.equals(d.getId(), id))
            {
                WarehouseQueue.add(id);
                logger.info("Drone " + id + " has been added to the warehouse");
                return;
            }
        }
        logger.warn("Drone " + id + " not found, nothing added");
    }

    public void doMaintenance()
    {
        String id = WarehouseQueue.poll();
        if (id == null)
        {
            logger.warn("No drones left in the warehouse");
        }
        else
        {
            logger.info("Drone " + id + " has been removed from the warehouse");
        }
    }

    public void createFlightPlan(String PilotId, String DroneId, LocalDateTime Start, int hours, List<Map.Entry<Double, Double>> positions)
    {
        Pilot p =  PilotList.get(PilotId);
        if (p == null)
        {
            logger.error("Pilot " + PilotId + " not found");
            return;
        }
        Drone d = null;
        for (Drone drone : DroneList)
        {
            if (Objects.equals(drone.getId(), DroneId))
            {
                d = drone;
            }
        }
        if (d == null)
        {
            logger.error("Drone " + DroneId + " not found");
            return;
        }
        for (String id : WarehouseQueue)
        {
            if (Objects.equals(d.getId(), id))
            {
                logger.error("Drone " + DroneId + " is in the warehouse");
            }
        }

        //TODO Check intervals and add Flightplan

    }



    public void addFlightPlan(FlightPlan flightPlan) {
        FlightPlanList.add(flightPlan);
    }

    public int numPilots() {
        return PilotList.size();
    }

    public int numDrones() {
        return DroneList.size();
    }

    public int numFlightPlans() {
        return FlightPlanList.size();
    }


    public Pilot getPilot(String id) {
        return PilotList.get(id);
    }

}
