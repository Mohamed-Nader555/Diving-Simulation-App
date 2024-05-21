package com.ghada.divingsimulation.Models;

public class Medical {
    String bloodPressure;
    String bloodType;
    String age;
    String pastInj;
    String chronicDiseases;
    String earDiseases;
    String heartDiseases;


    public Medical() {

        bloodPressure = "";
        bloodType = "";
        age = "";
        pastInj = "";
        chronicDiseases = "";
        earDiseases = "";
        heartDiseases = "";

    }


    public Medical(String bloodPressure, String bloodType, String age, String pastInj, String chronicDiseases, String earDiseases, String heartDiseases) {
        this.bloodPressure = bloodPressure;
        this.bloodType = bloodType;
        this.age = age;
        this.pastInj = pastInj;
        this.chronicDiseases = chronicDiseases;
        this.earDiseases = earDiseases;
        this.heartDiseases = heartDiseases;
    }


    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPastInj() {
        return pastInj;
    }

    public void setPastInj(String pastInj) {
        this.pastInj = pastInj;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getEarDiseases() {
        return earDiseases;
    }

    public void setEarDiseases(String earDiseases) {
        this.earDiseases = earDiseases;
    }

    public String getHeartDiseases() {
        return heartDiseases;
    }

    public void setHeartDiseases(String heartDiseases) {
        this.heartDiseases = heartDiseases;
    }
}


