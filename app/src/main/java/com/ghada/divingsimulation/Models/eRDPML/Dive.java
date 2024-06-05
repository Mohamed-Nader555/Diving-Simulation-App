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
import java.time.Duration;

public class Dive {
    private int plannedDepth;
    private int plannedBottomTime;
    private Duration surfaceInterval;
    private int residualNitrogenTimeBroughtForward;
    private int adjustedNoDecompressionLimitBroughtForward;
    private int residualNitrogenTimeToCarryForward;
    private int adjustedNoDecompressionLimitToCarryForward;
    private Character pressureGroupBeforeDive;
    private Character pressureGroupAfterDive;
    private Boolean safetyStop;
    private Character pressureGroupAfterSurfaceInterval;
    private Integer totalBottomTime;

    public Dive(int plannedDepth, int plannedBottomTime, Duration minimumSurfaceInterval) {
        this.plannedDepth = plannedDepth;
        this.plannedBottomTime = plannedBottomTime;
        this.surfaceInterval = minimumSurfaceInterval;
    }

    public int getResidualNitrogenTimeBroughtForward() {
        return residualNitrogenTimeBroughtForward;
    }

    public void setResidualNitrogenTimeBroughtForward(int residualNitrogenTimeBroughtForward) {
        this.residualNitrogenTimeBroughtForward = residualNitrogenTimeBroughtForward;
    }

    public int getAdjustedNoDecompressionLimitBroughtForward() {
        return adjustedNoDecompressionLimitBroughtForward;
    }

    public void setAdjustedNoDecompressionLimitBroughtForward(int adjustedNoDecompressionLimitBroughtForward) {
        this.adjustedNoDecompressionLimitBroughtForward = adjustedNoDecompressionLimitBroughtForward;
    }

    public int getResidualNitrogenTimeToCarryForward() {
        return residualNitrogenTimeToCarryForward;
    }

    public void setResidualNitrogenTimeToCarryForward(int residualNitrogenTimeToCarryForward) {
        this.residualNitrogenTimeToCarryForward = residualNitrogenTimeToCarryForward;
    }

    public int getAdjustedNoDecompressionLimitToCarryForward() {
        return adjustedNoDecompressionLimitToCarryForward;
    }

    public void setAdjustedNoDecompressionLimitToCarryForward(int adjustedNoDecompressionLimitToCarryForward) {
        this.adjustedNoDecompressionLimitToCarryForward = adjustedNoDecompressionLimitToCarryForward;
    }

    public Character getPressureGroupBeforeDive() {
        return pressureGroupBeforeDive;
    }

    public void setPressureGroupBeforeDive(Character pressureGroupBeforeDive) {
        this.pressureGroupBeforeDive = pressureGroupBeforeDive;
    }

    public Character getPressureGroupAfterDive() {
        return pressureGroupAfterDive;
    }

    public void setPressureGroupAfterDive(Character pressureGroupAfterDive) {
        this.pressureGroupAfterDive = pressureGroupAfterDive;
    }

    public int getPlannedDepth() {
        return plannedDepth;
    }

    public int getPlannedBottomTime() {
        return plannedBottomTime;
    }

    public Boolean getSafetyStop() {
        return safetyStop;
    }

    public void setSafetyStop(Boolean safetyStop) {
        this.safetyStop = safetyStop;
    }

    public Duration getSurfaceInterval() {
        return surfaceInterval;
    }

    public void setSurfaceInterval(Duration surfaceInterval) {
        this.surfaceInterval = surfaceInterval;
    }

    public Character getPressureGroupAfterSurfaceInterval() {
        return pressureGroupAfterSurfaceInterval;
    }

    public void setPressureGroupAfterSurfaceInterval(Character pressureGroupAfterSurfaceInterval) {
        this.pressureGroupAfterSurfaceInterval = pressureGroupAfterSurfaceInterval;
    }

    public Integer getTotalBottomTime() {
        return totalBottomTime;
    }

    public void setTotalBottomTime(Integer totalBottomTime) {
        this.totalBottomTime = totalBottomTime;
    }
}

