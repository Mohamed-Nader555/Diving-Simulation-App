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
import java.time.LocalTime;

public class SurfaceIntervalCreditCell {
    private char startPressureGroup;
    private Duration from;
    private Duration to;
    private char endingPressureGroup;

    public SurfaceIntervalCreditCell(char startPressureGroup, String startTimeSpan, String endTimeSpan, char endingPressureGroup) {
        this.startPressureGroup = startPressureGroup;
        this.from = Duration.between(LocalTime.MIN, LocalTime.parse(startTimeSpan));
        this.to = Duration.between(LocalTime.MIN, LocalTime.parse(endTimeSpan));
        this.endingPressureGroup = endingPressureGroup;
    }

    public char getStartPressureGroup() {
        return startPressureGroup;
    }

    public Duration getFrom() {
        return from;
    }

    public Duration getTo() {
        return to;
    }

    public char getEndingPressureGroup() {
        return endingPressureGroup;
    }
}
