package com.ghada.divingsimulation.Models.User;

public class Certificates {
    String certDate;
    String certOrg;
    String certLevel;
    String certNumber;
    String cetType;

    public Certificates() {
        certDate = "";
        certOrg = "";
        certLevel = "";
        certNumber = "";
        cetType = "";
    }

    public Certificates(String certDate, String certOrg, String certLevel, String certNumber, String cetType) {
        this.certDate = certDate;
        this.certOrg = certOrg;
        this.certLevel = certLevel;
        this.certNumber = certNumber;
        this.cetType = cetType;
    }

    public String getCertDate() {
        return certDate;
    }

    public void setCertDate(String certDate) {
        this.certDate = certDate;
    }

    public String getCertOrg() {
        return certOrg;
    }

    public void setCertOrg(String certOrg) {
        this.certOrg = certOrg;
    }

    public String getCertLevel() {
        return certLevel;
    }

    public void setCertLevel(String certLevel) {
        this.certLevel = certLevel;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    public String getCetType() {
        return cetType;
    }

    public void setCetType(String cetType) {
        this.cetType = cetType;
    }
}
