package edu.upc.dsa.domain.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class Pilot {

    private final String name;
    private final String surname;
    private final LinkedList<FlightPlan> FlightPlans;
    private final String id;
    private final float FlightHours;



    public Pilot(String name, String surname, String ID, float FlightHours) {
        this.name = name;
        this.surname = surname;
        this.FlightPlans = new LinkedList<>();
        this.id = ID;
        this.FlightHours = FlightHours;
    }

    public List<FlightPlan> showFlightPlans() {
        return this.FlightPlans;
    }

    public int numFlightPlans() {
        return FlightPlans.size();
    }

    public void addFLightPlan(FlightPlan flightPlan) {
        this.FlightPlans.add(flightPlan);
    }
    public String getId()
    {
        return id;
    }


    public float getFlightHours()
    {
        return FlightHours;
    }
}
