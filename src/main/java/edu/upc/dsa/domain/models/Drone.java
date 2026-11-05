package edu.upc.dsa.domain.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;

public class Drone {

    private  String id;
    private  String name;
    private String manufacturer;
    private String model;
    private LinkedList<FlightPlan> FlightPlans;
    private float FlightHours;


    public Drone(String id, String name,  String manufacturer, String model, float FlightHours) {
        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.model = model;
        this.FlightPlans = new LinkedList<>();
        this.FlightHours = FlightHours;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getFlightHours() { return FlightHours; }



}
