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

    public Drone(){}


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

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getFlightHours() {
        return FlightHours;
    }

    public void setFlightHours(float flightHours) {
        this.FlightHours = flightHours;
    }



}
