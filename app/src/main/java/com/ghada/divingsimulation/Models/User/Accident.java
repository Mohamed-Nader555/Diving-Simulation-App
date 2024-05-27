package com.ghada.divingsimulation.Models.User;

public class Accident {

    String rescuerName;
    String fetal;
    String training;
    String date;
    String time;
    String extentOfInjury;
    String vicName;
    String vicNationality;
    String vicAge;
    String vicGender;
    String vicCertLevel;
    String foundOnSurface;
    String depthAtAccident;
    String city;
    String currentDiveSite;
    String currentUnderWater;
    String currentSurface;
    String equipmentRented;
    String drySuit;
    String EANx;


    public Accident() {
        this.rescuerName = "";
        this.fetal = "";
        this.training = "";
        this.date = "";
        this.time = "";
        this.extentOfInjury = "";
        this.vicName = "";
        this.vicNationality = "";
        this.vicAge = "";
        this.vicGender = "";
        this.vicCertLevel = "";
        this.foundOnSurface = "";
        this.depthAtAccident = "";
        this.city = "";
        this.currentDiveSite = "";
        this.currentUnderWater = "";
        this.currentSurface = "";
        this.equipmentRented = "";
        this.drySuit = "";
        this.EANx = "";

    }


    public Accident(String rescuerName, String fetal, String training, String date, String time, String extentOfInjury, String vicName, String vicNationality, String vicAge, String vicGender, String vicCertLevel, String foundOnSurface,String depthAtAccident, String city, String currentDiveSite, String currentUnderWater, String currentSurface, String equipmentRented, String drySuit, String EANx) {
        this.rescuerName = rescuerName;
        this.fetal = fetal;
        this.training = training;
        this.date = date;
        this.time = time;
        this.extentOfInjury = extentOfInjury;
        this.vicName = vicName;
        this.vicNationality = vicNationality;
        this.vicAge = vicAge;
        this.vicGender = vicGender;
        this.vicCertLevel = vicCertLevel;
        this.foundOnSurface = foundOnSurface;
        this.depthAtAccident = depthAtAccident;
        this.city = city;
        this.currentDiveSite = currentDiveSite;
        this.currentUnderWater = currentUnderWater;
        this.currentSurface = currentSurface;
        this.equipmentRented = equipmentRented;
        this.drySuit = drySuit;
        this.EANx = EANx;
    }


    public String getRescuerName() {
        return rescuerName;
    }

    public void setRescuerName(String rescuerName) {
        this.rescuerName = rescuerName;
    }

    public String getFetal() {
        return fetal;
    }

    public void setFetal(String fetal) {
        this.fetal = fetal;
    }

    public String getTraining() {
        return training;
    }

    public void setTraining(String training) {
        this.training = training;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getExtentOfInjury() {
        return extentOfInjury;
    }

    public void setExtentOfInjury(String extentOfInjury) {
        this.extentOfInjury = extentOfInjury;
    }

    public String getVicName() {
        return vicName;
    }

    public void setVicName(String vicName) {
        this.vicName = vicName;
    }

    public String getVicNationality() {
        return vicNationality;
    }

    public void setVicNationality(String vicNationality) {
        this.vicNationality = vicNationality;
    }

    public String getVicAge() {
        return vicAge;
    }

    public void setVicAge(String vicAge) {
        this.vicAge = vicAge;
    }

    public String getVicGender() {
        return vicGender;
    }

    public void setVicGender(String vicGender) {
        this.vicGender = vicGender;
    }

    public String getVicCertLevel() {
        return vicCertLevel;
    }

    public void setVicCertLevel(String vicCertLevel) {
        this.vicCertLevel = vicCertLevel;
    }

    public String getFoundOnSurface() {
        return foundOnSurface;
    }

    public void setFoundOnSurface(String foundOnSurface) {
        this.foundOnSurface = foundOnSurface;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrentDiveSite() {
        return currentDiveSite;
    }

    public void setCurrentDiveSite(String currentDiveSite) {
        this.currentDiveSite = currentDiveSite;
    }

    public String getCurrentUnderWater() {
        return currentUnderWater;
    }

    public void setCurrentUnderWater(String currentUnderWater) {
        this.currentUnderWater = currentUnderWater;
    }

    public String getCurrentSurface() {
        return currentSurface;
    }

    public void setCurrentSurface(String currentSurface) {
        this.currentSurface = currentSurface;
    }

    public String getEquipmentRented() {
        return equipmentRented;
    }

    public void setEquipmentRented(String equipmentRented) {
        this.equipmentRented = equipmentRented;
    }

    public String getDrySuit() {
        return drySuit;
    }

    public void setDrySuit(String drySuit) {
        this.drySuit = drySuit;
    }

    public String getEANx() {
        return EANx;
    }

    public void setEANx(String EANx) {
        this.EANx = EANx;
    }

    public String getDepthAtAccident() {
        return depthAtAccident;
    }

    public void setDepthAtAccident(String depthAtAccident) {
        this.depthAtAccident = depthAtAccident;
    }

    @Override
    public String toString() {
        return "Accident{" +
                "rescuerName='" + rescuerName + '\'' +
                ", fetal='" + fetal + '\'' +
                ", training='" + training + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", extentOfInjury='" + extentOfInjury + '\'' +
                ", vicName='" + vicName + '\'' +
                ", vicNationality='" + vicNationality + '\'' +
                ", vicAge='" + vicAge + '\'' +
                ", vicGender='" + vicGender + '\'' +
                ", vicCertLevel='" + vicCertLevel + '\'' +
                ", foundOnSurface='" + foundOnSurface + '\'' +
                ", depthAtAccident='" + depthAtAccident + '\'' +
                ", city='" + city + '\'' +
                ", currentDiveSite='" + currentDiveSite + '\'' +
                ", currentUnderWater='" + currentUnderWater + '\'' +
                ", currentSurface='" + currentSurface + '\'' +
                ", equipmentRented='" + equipmentRented + '\'' +
                ", drySuit='" + drySuit + '\'' +
                ", EANx='" + EANx + '\'' +
                '}';
    }
}
