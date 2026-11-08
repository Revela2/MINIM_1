package edu.upc.dsa.domain.models;

import java.util.LinkedList;

public class Pilot {

    private String name;
    private String surname;
    private LinkedList<FlightPlan> FlightPlans;
    private String id;
    private float FlightHours;


    public Pilot() {
    }

    public Pilot(String name, String surname, String ID, float FlightHours) {
        this.name = name;
        this.surname = surname;
        this.FlightPlans = new LinkedList<>();
        this.id = ID;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public float getFlightHours() {
        return FlightHours;
    }

    public void setFlightHours(float flightHours) {
        this.FlightHours = flightHours;
    }
}
