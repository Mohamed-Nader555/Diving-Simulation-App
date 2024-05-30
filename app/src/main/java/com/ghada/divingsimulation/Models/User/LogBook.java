package com.ghada.divingsimulation.Models.User;

import java.io.Serializable;

public class LogBook implements Serializable {
    String date;
    String diveSite;
    String buddy;
    String instructor;
    String startTime;
    String bottomTime;
    String startTank;
    String endTank;
    String seaCondition;
    String visibility;
    String notes;
    String diveType;
    String waterType;
    String gasMixture;
    String maxDepth;
    String location;


    public LogBook() {

        this.date = "";
        this.diveSite = "";
        this.buddy = "";
        this.instructor = "";
        this.startTime = "";
        this.bottomTime = "";
        this.startTank = "";
        this.endTank = "";
        this.seaCondition = "";
        this.visibility = "";
        this.notes = "";
        this.diveType = "";
        this.waterType = "";
        this.gasMixture = "";
        this.maxDepth = "";
        this.location = "";
    }

    public LogBook(String date, String diveSite, String buddy, String instructor, String startTime, String bottomTime, String startTank, String endTank, String seaCondition, String visibility, String notes, String diveType, String waterType, String gasMixture, String maxDepth, String location) {
        this.date = date;
        this.diveSite = diveSite;
        this.buddy = buddy;
        this.instructor = instructor;
        this.startTime = startTime;
        this.bottomTime = bottomTime;
        this.startTank = startTank;
        this.endTank = endTank;
        this.seaCondition = seaCondition;
        this.visibility = visibility;
        this.notes = notes;
        this.diveType = diveType;
        this.waterType = waterType;
        this.gasMixture = gasMixture;
        this.maxDepth = maxDepth;
        this.location = location;
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

    public String getBottomTime() {
        return bottomTime;
    }

    public void setBottomTime(String bottomTime) {
        this.bottomTime = bottomTime;
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

    public String getDiveType() {
        return diveType;
    }

    public void setDiveType(String diveType) {
        this.diveType = diveType;
    }

    public String getWaterType() {
        return waterType;
    }

    public void setWaterType(String waterType) {
        this.waterType = waterType;
    }

    public String getGasMixture() {
        return gasMixture;
    }

    public void setGasMixture(String gasMixture) {
        this.gasMixture = gasMixture;
    }


    public String getStartTank() {
        return startTank;
    }

    public void setStartTank(String startTank) {
        this.startTank = startTank;
    }

    public String getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(String maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
