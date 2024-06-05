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

public class SurfaceInterval {
    private char pressureGroupBeforeSurfaceInterval;
    private Duration minimumSurfaceInterval;
    private Duration maximumSurfaceInterval;
    private char pressureGroupAfterSurfaceInterval;
    private int residualNitrogenTime;
    private int adjustedNoDecompressionLimit;

    public SurfaceInterval(char pressureGroupBeforeSurfaceInterval, Duration minimumSurfaceInterval, Duration maximumSurfaceInterval, char pressureGroupAfterSurfaceInterval, int residualNitrogenTime, int adjustedNoDecompressionLimit) {
        this.pressureGroupBeforeSurfaceInterval = pressureGroupBeforeSurfaceInterval;
        this.minimumSurfaceInterval = minimumSurfaceInterval;
        this.maximumSurfaceInterval = maximumSurfaceInterval;
        this.pressureGroupAfterSurfaceInterval = pressureGroupAfterSurfaceInterval;
        this.residualNitrogenTime = residualNitrogenTime;
        this.adjustedNoDecompressionLimit = adjustedNoDecompressionLimit;
    }

    public char getPressureGroupBeforeSurfaceInterval() {
        return pressureGroupBeforeSurfaceInterval;
    }

    public Duration getMinimumSurfaceInterval() {
        return minimumSurfaceInterval;
    }

    public Duration getMaximumSurfaceInterval() {
        return maximumSurfaceInterval;
    }

    public char getPressureGroupAfterSurfaceInterval() {
        return pressureGroupAfterSurfaceInterval;
    }

    public int getResidualNitrogenTime() {
        return residualNitrogenTime;
    }

    public int getAdjustedNoDecompressionLimit() {
        return adjustedNoDecompressionLimit;
    }
}
