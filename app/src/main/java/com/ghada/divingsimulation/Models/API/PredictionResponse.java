package com.ghada.divingsimulation.Models.API;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PredictionResponse {


    @SerializedName("output")
    @Expose
    private String output;


    public PredictionResponse() {
    }

    public PredictionResponse(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }


}
