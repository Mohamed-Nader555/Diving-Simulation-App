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
public class RepetitiveDiveCell {
    private char pressureGroup;
    private int depth;
    private int residualNitrogenTime;
    private Integer adjustedNoDecompressionLimit;

    public RepetitiveDiveCell(char pressureGroup, int depth, int residualNitrogenTime, Integer adjustedNoDecompressionLimit) {
        this.pressureGroup = pressureGroup;
        this.depth = depth;
        this.residualNitrogenTime = residualNitrogenTime;
        this.adjustedNoDecompressionLimit = adjustedNoDecompressionLimit;
    }

    public char getPressureGroup() {
        return pressureGroup;
    }

    public int getDepth() {
        return depth;
    }

    public int getResidualNitrogenTime() {
        return residualNitrogenTime;
    }

    public Integer getAdjustedNoDecompressionLimit() {
        return adjustedNoDecompressionLimit;
    }
}

