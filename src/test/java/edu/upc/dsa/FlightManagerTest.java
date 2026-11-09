package edu.upc.dsa;

import edu.upc.dsa.domain.models.Drone;
import edu.upc.dsa.domain.models.FlightPlan;
import edu.upc.dsa.domain.models.Pilot;
import edu.upc.dsa.domain.models.Position;
import edu.upc.dsa.domain.repository.FlightManager;
import edu.upc.dsa.domain.repository.FlightManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlightManagerTest {
    FlightManager fm;

    @Before
    public void setUp()
    {
        fm = FlightManagerImpl.getInstance();

        // Add Pilots
        fm.addPilot("P1", "Chaval", "De la Peca", 1500.5f);
        fm.addPilot("P2", "Cara", "Anchoa", 800.0f);
        fm.addPilot("P3", "Toni", "Oller", 2200.0f);

        // Add Drones
        fm.addDrone("D1", "Falcon", "DJI", "Mavic 3", 120.5f);
        fm.addDrone("D2", "Eagle", "Autel", "EVO II", 250.0f);
        fm.addDrone("D3", "Hawk", "Parrot", "Anafi", 50.0f);
    }

    @After
    public void tearDown()
    {
        this.fm.clear();
        this.fm = null;
    }

    @Test
    public void addPilotTest()
    {
        Assert.assertEquals(3, fm.numPilots());
        fm.addPilot("P4", "Phoenix", "Trace", 600.0f);
        fm.addPilot("P5", "Bob", "Floyd", 450.0f);
        Assert.assertEquals(5, fm.numPilots());

        fm.addPilot("P1", "Chaval", "De la Peca copia", 100.0f);
        Assert.assertEquals(5, fm.numPilots());
    }

    @Test
    public void addDroneTest()
    {
        Assert.assertEquals(3, fm.numDrones());
        fm.addDrone("D4", "Vulture", "Skydio", "X2", 15.0f);
        fm.addDrone("D5", "Raven", "SenseFly", "eBee", 310.0f);
        Assert.assertEquals(5, fm.numDrones());

        fm.addDrone("D1", "Dron copia", "Aliexpress", "AAAA", 0.0f);
        Assert.assertEquals(5, fm.numDrones());
    }

    @Test
    public void getDronesByFlightHoursTest()
    {
        List<Drone> drones = fm.getDronesByFlightHours();

        Assert.assertEquals(3, drones.size());
        Assert.assertEquals("D2", drones.get(0).getId());
        Assert.assertEquals(250.0f, drones.get(0).getFlightHours(), 0.001);

        Assert.assertEquals("D1", drones.get(1).getId());
        Assert.assertEquals(120.5f, drones.get(1).getFlightHours(), 0.001);

        Assert.assertEquals("D3", drones.get(2).getId());
        Assert.assertEquals(50.0f, drones.get(2).getFlightHours(), 0.001);
    }

    @Test
    public void getPilotsByFlightHoursTest()
    {
        Map<String, Pilot> pilotsMap = fm.getPilotsByFlightHours();
        List<Pilot> sortedPilots = new ArrayList<>(pilotsMap.values());

        Assert.assertEquals(3, sortedPilots.size());
        Assert.assertEquals("P3", sortedPilots.get(0).getId());
        Assert.assertEquals(2200.0f, sortedPilots.get(0).getFlightHours(), 0.001);

        Assert.assertEquals("P1", sortedPilots.get(1).getId());
        Assert.assertEquals(1500.5f, sortedPilots.get(1).getFlightHours(), 0.001);

        Assert.assertEquals("P2", sortedPilots.get(2).getId());
        Assert.assertEquals(800.0f, sortedPilots.get(2).getFlightHours(), 0.001);
    }

    @Test
    public void warehouseMaintenanceTest()
    {
        fm.addDroneToWarehouse("D1");
        fm.addDroneToWarehouse("D2");
        Assert.assertEquals(2, fm.numDronesInWarehouse());

        fm.doMaintenance();
        Assert.assertEquals(1, fm.numDronesInWarehouse());
        fm.doMaintenance();
        Assert.assertEquals(0, fm.numDronesInWarehouse());
        fm.doMaintenance();
        Assert.assertEquals(0, fm.numDronesInWarehouse());
    }

    @Test
    public void createFlightPlanTest()
    {
        Assert.assertEquals(0, fm.numFlightPlans());

        List<Position> positions = new ArrayList<>();
        positions.add(new Position(41.3879, 2.1699));
        positions.add(new Position(41.3890, 2.1700));

        LocalDateTime start = LocalDateTime.of(2026, 11, 8, 10, 0);
        fm.createFlightPlan("P1", "D1", start, 2, positions);

        Assert.assertEquals(1, fm.numFlightPlans());

        // Overlapping flight for pilot P1
        fm.createFlightPlan("P1", "D2", start.plusHours(1), 2, positions);
        Assert.assertEquals(1, fm.numFlightPlans());

        // Overlapping flight for drone D1
        fm.createFlightPlan("P2", "D1", start.plusHours(1), 2, positions);
        Assert.assertEquals(1, fm.numFlightPlans());

        // Drone D3 is in warehouse
        fm.addDroneToWarehouse("D3");
        fm.createFlightPlan("P2", "D3", start.plusHours(5), 2, positions);
        Assert.assertEquals(1, fm.numFlightPlans());
    }
}