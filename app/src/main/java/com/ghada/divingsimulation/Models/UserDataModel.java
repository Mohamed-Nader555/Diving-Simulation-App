package com.ghada.divingsimulation.Models;

import java.util.ArrayList;

public class UserDataModel {

    String UserID;
    String fullName;
    String email;
    String phoneNumber;
    String password;
    String profileImage;
    String totalDives;
    String totalDivingTime;
    String maxDepth;
    String avgDepth;
    ArrayList<Certificates> certificates;
    Medical medical;
    ArrayList<LogBook> logBook;
    ArrayList<Accident> accidents;

    public UserDataModel() {
    }

    public UserDataModel(String userID, String fullName, String email, String phoneNumber, String password, String profileImage, String totalDives, String totalDivingTime, String maxDepth, String avgDepth, ArrayList<Certificates> certificates, Medical medical, ArrayList<LogBook> logBook, ArrayList<Accident> accidents) {
        UserID = userID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.profileImage = profileImage;
        this.totalDives = totalDives;
        this.totalDivingTime = totalDivingTime;
        this.maxDepth = maxDepth;
        this.avgDepth = avgDepth;
        this.certificates = certificates;
        this.medical = medical;
        this.logBook = logBook;
        this.accidents = accidents;
    }


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getTotalDives() {
        return totalDives;
    }

    public void setTotalDives(String totalDives) {
        this.totalDives = totalDives;
    }

    public String getTotalDivingTime() {
        return totalDivingTime;
    }

    public void setTotalDivingTime(String totalDivingTime) {
        this.totalDivingTime = totalDivingTime;
    }

    public String getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(String maxDepth) {
        this.maxDepth = maxDepth;
    }

    public String getAvgDepth() {
        return avgDepth;
    }

    public void setAvgDepth(String avgDepth) {
        this.avgDepth = avgDepth;
    }

    public ArrayList<Certificates> getCertificates() {
        return certificates;
    }

    public void setCertificates(ArrayList<Certificates> certificates) {
        this.certificates = certificates;
    }

    public Medical getMedical() {
        return medical;
    }

    public void setMedical(Medical medical) {
        this.medical = medical;
    }

    public ArrayList<LogBook> getLogBook() {
        return logBook;
    }

    public void setLogBook(ArrayList<LogBook> logBook) {
        this.logBook = logBook;
    }

    public ArrayList<Accident> getAccidents() {
        return accidents;
    }

    public void setAccidents(ArrayList<Accident> accidents) {
        this.accidents = accidents;
    }
}
