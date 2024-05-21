package com.ghada.divingsimulation.Models;

public class Accident {

    String accPlace;
    String accTime;

    public Accident(String accPlace, String accTime) {
        this.accPlace = accPlace;
        this.accTime = accTime;
    }

    public String getAccPlace() {
        return accPlace;
    }

    public void setAccPlace(String accPlace) {
        this.accPlace = accPlace;
    }

    public String getAccTime() {
        return accTime;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }
}
