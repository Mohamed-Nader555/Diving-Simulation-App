package com.ghada.divingsimulation.Models;

public class LogBook {
    String date;
    String diveSite;
    String buddy;
    String instructor;
    String startTime;
    String divingTime;
    String startTank;
    String endTank;
    String seaCondition;
    String visibility;
    String notes;


    public LogBook(String date, String diveSite, String buddy, String instructor, String startTime, String divingTime, String startTank, String endTank, String seaCondition, String visibility, String notes) {
        this.date = date;
        this.diveSite = diveSite;
        this.buddy = buddy;
        this.instructor = instructor;
        this.startTime = startTime;
        this.divingTime = divingTime;
        this.startTank = startTank;
        this.endTank = endTank;
        this.seaCondition = seaCondition;
        this.visibility = visibility;
        this.notes = notes;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiveSite() {
        return diveSite;
    }

    public void setDiveSite(String diveSite) {
        this.diveSite = diveSite;
    }

    public String getBuddy() {
        return buddy;
    }

    public void setBuddy(String buddy) {
        this.buddy = buddy;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDivingTime() {
        return divingTime;
    }

    public void setDivingTime(String divingTime) {
        this.divingTime = divingTime;
    }

    public String getStartTank() {
        return startTank;
    }

    public void setStartTank(String startTank) {
        this.startTank = startTank;
    }

    public String getEndTank() {
        return endTank;
    }

    public void setEndTank(String endTank) {
        this.endTank = endTank;
    }

    public String getSeaCondition() {
        return seaCondition;
    }

    public void setSeaCondition(String seaCondition) {
        this.seaCondition = seaCondition;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    @Override
    public String toString() {
        return "LogBook{" +
                "date='" + date + '\'' +
                ", diveSite='" + diveSite + '\'' +
                ", buddy='" + buddy + '\'' +
                ", instructor='" + instructor + '\'' +
                ", startTime='" + startTime + '\'' +
                ", divingTime='" + divingTime + '\'' +
                ", startTank='" + startTank + '\'' +
                ", endTank='" + endTank + '\'' +
                ", seaCondition='" + seaCondition + '\'' +
                ", visibility='" + visibility + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
