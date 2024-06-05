/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghada.divingsimulation.Models.eRDPML;

/**
 *
 * @author meshm
 */
public class TimeCell {
    private int depth;
    private Integer duration;
    private char endingPressureGroup;
    private boolean advanceToNextDuration;
    private boolean safetyStopRequired;
    private boolean noDecompressionLimit;

    // Constructor with all parameters
    public TimeCell(int depth, Integer duration, char endingPressureGroup, boolean safetyStopRequired , boolean noDecompressionLimit,  boolean advanceToNextDuration ) {
        this.depth = depth;
        this.duration = duration;
        this.endingPressureGroup = endingPressureGroup;
        this.advanceToNextDuration = advanceToNextDuration;
        this.safetyStopRequired = safetyStopRequired;
        this.noDecompressionLimit = noDecompressionLimit;
    }

    // Constructor with default values for advanceToNextDuration and safetyStopRequired
    public TimeCell(int depth, Integer duration, char endingPressureGroup) {
        this(depth, duration, endingPressureGroup, false, false, false);
    }
    
    // Constructor with default values for advanceToNextDuration and safetyStopRequired
    public TimeCell(int depth, Integer duration, char endingPressureGroup, boolean safetyStopRequired) {
        this(depth, duration, endingPressureGroup, safetyStopRequired,false, false);
    }

    public int getDepth() {
        return depth;
    }

    public Integer getDuration() {
        return duration;
    }

    public boolean isSafetyStopRequired() {
        return safetyStopRequired;
    }

    public boolean isNoDecompressionLimit() {
        return noDecompressionLimit;
    }

    public char getEndingPressureGroup() {
        return endingPressureGroup;
    }

    public boolean isAdvanceToNextDuration() {
        return advanceToNextDuration;
    }
}

