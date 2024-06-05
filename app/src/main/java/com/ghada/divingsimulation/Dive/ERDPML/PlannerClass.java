/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ghada.divingsimulation.Dive.ERDPML;

import com.ghada.divingsimulation.Models.eRDPML.Dive;
import com.ghada.divingsimulation.Models.eRDPML.RepetitiveDiveCell;
import com.ghada.divingsimulation.Models.eRDPML.SurfaceInterval;
import com.ghada.divingsimulation.Models.eRDPML.SurfaceIntervalCreditCell;
import com.ghada.divingsimulation.Models.eRDPML.TimeCell;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author meshm
 */
public class PlannerClass {

    private static final char MinPressureGroup = 'A';
    private static PlannerClass instance;
    private List<RepetitiveDiveCell> repetitiveDiveCells;
    private List<SurfaceIntervalCreditCell> surfaceIntervalCreditCells;
    private List<TimeCell> timeCells;

    private PlannerClass() {
        initializeDataStructures();
    }

    public static PlannerClass getInstance() {
        if (instance == null) {
            instance = new PlannerClass();
        }
        return instance;
    }

    private void initializeDataStructures() {
        // Initialize TimeCells
        timeCells = new ArrayList<>();
        // 10 meters
        // 10 meters

        timeCells.add(new TimeCell(10, 10, 'A'));
        timeCells.add(new TimeCell(10, 20, 'B'));
        timeCells.add(new TimeCell(10, 26, 'C'));
        timeCells.add(new TimeCell(10, 30, 'D'));
        timeCells.add(new TimeCell(10, 34, 'E'));
        timeCells.add(new TimeCell(10, 37, 'F'));
        timeCells.add(new TimeCell(10, 41, 'G'));
        timeCells.add(new TimeCell(10, 45, 'H'));
        timeCells.add(new TimeCell(10, 50, 'I'));
        timeCells.add(new TimeCell(10, 54, 'J'));
        timeCells.add(new TimeCell(10, 59, 'K'));
        timeCells.add(new TimeCell(10, 64, 'L'));
        timeCells.add(new TimeCell(10, 70, 'M'));
        timeCells.add(new TimeCell(10, 75, 'N'));
        timeCells.add(new TimeCell(10, 82, 'O'));
        timeCells.add(new TimeCell(10, 88, 'P'));
        timeCells.add(new TimeCell(10, 95, 'Q'));
        timeCells.add(new TimeCell(10, 104, 'R'));
        timeCells.add(new TimeCell(10, 112, 'S'));
        timeCells.add(new TimeCell(10, 122, 'T'));
        timeCells.add(new TimeCell(10, 133, 'U'));
        timeCells.add(new TimeCell(10, 145, 'V'));
        timeCells.add(new TimeCell(10, 160, 'W', true));
        timeCells.add(new TimeCell(10, 178, 'X', true));
        timeCells.add(new TimeCell(10, 199, 'Y', true));
        timeCells.add(new TimeCell(10, 219, 'Z', true, true, false));

        // 12 metres
        timeCells.add(new TimeCell(12, 9, 'A'));
        timeCells.add(new TimeCell(12, 17, 'B'));
        timeCells.add(new TimeCell(12, 23, 'C'));
        timeCells.add(new TimeCell(12, 26, 'D'));
        timeCells.add(new TimeCell(12, 29, 'E'));
        timeCells.add(new TimeCell(12, 32, 'F'));
        timeCells.add(new TimeCell(12, 35, 'G'));
        timeCells.add(new TimeCell(12, 38, 'H'));
        timeCells.add(new TimeCell(12, 42, 'I'));
        timeCells.add(new TimeCell(12, 45, 'J'));
        timeCells.add(new TimeCell(12, 49, 'K'));
        timeCells.add(new TimeCell(12, 53, 'L'));
        timeCells.add(new TimeCell(12, 57, 'M'));
        timeCells.add(new TimeCell(12, 62, 'N'));
        timeCells.add(new TimeCell(12, 66, 'O'));
        timeCells.add(new TimeCell(12, 71, 'P'));
        timeCells.add(new TimeCell(12, 76, 'Q'));
        timeCells.add(new TimeCell(12, 82, 'R'));
        timeCells.add(new TimeCell(12, 88, 'S'));
        timeCells.add(new TimeCell(12, 94, 'T'));
        timeCells.add(new TimeCell(12, 101, 'U'));
        timeCells.add(new TimeCell(12, 108, 'V'));
        timeCells.add(new TimeCell(12, 116, 'W', true));
        timeCells.add(new TimeCell(12, 125, 'X', true));
        timeCells.add(new TimeCell(12, 134, 'Y', true));
        timeCells.add(new TimeCell(12, 147, 'Z', true, true, false));

        // 14 metres
        timeCells.add(new TimeCell(14, 8, 'A'));
        timeCells.add(new TimeCell(14, 15, 'B'));
        timeCells.add(new TimeCell(14, 19, 'C'));
        timeCells.add(new TimeCell(14, 22, 'D'));
        timeCells.add(new TimeCell(14, 24, 'E'));
        timeCells.add(new TimeCell(14, 27, 'F'));
        timeCells.add(new TimeCell(14, 29, 'G'));
        timeCells.add(new TimeCell(14, 32, 'H'));
        timeCells.add(new TimeCell(14, 35, 'I'));
        timeCells.add(new TimeCell(14, 37, 'J'));
        timeCells.add(new TimeCell(14, 40, 'K'));
        timeCells.add(new TimeCell(14, 43, 'L'));
        timeCells.add(new TimeCell(14, 47, 'M'));
        timeCells.add(new TimeCell(14, 50, 'N'));
        timeCells.add(new TimeCell(14, 53, 'O'));
        timeCells.add(new TimeCell(14, 57, 'P'));
        timeCells.add(new TimeCell(14, 61, 'Q'));
        timeCells.add(new TimeCell(14, 64, 'R'));
        timeCells.add(new TimeCell(14, 68, 'S'));
        timeCells.add(new TimeCell(14, 73, 'T'));
        timeCells.add(new TimeCell(14, 77, 'U'));
        timeCells.add(new TimeCell(14, 82, 'V', true));
        timeCells.add(new TimeCell(14, 87, 'W', true));
        timeCells.add(new TimeCell(14, 92, 'X', true));
        timeCells.add(new TimeCell(14, 98, 'Y', true, true, false));

        // 16 metres
        timeCells.add(new TimeCell(16, 7, 'A'));
        timeCells.add(new TimeCell(16, 13, 'B'));
        timeCells.add(new TimeCell(16, 17, 'C'));
        timeCells.add(new TimeCell(16, 19, 'D'));
        timeCells.add(new TimeCell(16, 21, 'E'));
        timeCells.add(new TimeCell(16, 23, 'F'));
        timeCells.add(new TimeCell(16, 25, 'G'));
        timeCells.add(new TimeCell(16, 27, 'H'));
        timeCells.add(new TimeCell(16, 29, 'I'));
        timeCells.add(new TimeCell(16, 32, 'J'));
        timeCells.add(new TimeCell(16, 34, 'K'));
        timeCells.add(new TimeCell(16, 37, 'L'));
        timeCells.add(new TimeCell(16, 39, 'M'));
        timeCells.add(new TimeCell(16, 42, 'N'));
        timeCells.add(new TimeCell(16, 45, 'O'));
        timeCells.add(new TimeCell(16, 48, 'P'));
        timeCells.add(new TimeCell(16, 50, 'Q'));
        timeCells.add(new TimeCell(16, 53, 'R'));
        timeCells.add(new TimeCell(16, 56, 'S'));
        timeCells.add(new TimeCell(16, 60, 'T'));
        timeCells.add(new TimeCell(16, 63, 'U', true));
        timeCells.add(new TimeCell(16, 67, 'V', true));
        timeCells.add(new TimeCell(16, 70, 'W', true));
        timeCells.add(new TimeCell(16, 72, 'X', true, true, false));

        // 18 metres
        timeCells.add(new TimeCell(18, 6, 'A'));
        timeCells.add(new TimeCell(18, 11, 'B'));
        timeCells.add(new TimeCell(18, 15, 'C'));
        timeCells.add(new TimeCell(18, 16, 'D'));
        timeCells.add(new TimeCell(18, 18, 'E'));
        timeCells.add(new TimeCell(18, 20, 'F'));
        timeCells.add(new TimeCell(18, 22, 'G'));
        timeCells.add(new TimeCell(18, 24, 'H'));
        timeCells.add(new TimeCell(18, 26, 'I'));
        timeCells.add(new TimeCell(18, 28, 'J'));
        timeCells.add(new TimeCell(18, 30, 'K'));
        timeCells.add(new TimeCell(18, 32, 'L'));
        timeCells.add(new TimeCell(18, 34, 'M'));
        timeCells.add(new TimeCell(18, 36, 'N'));
        timeCells.add(new TimeCell(18, 39, 'O'));
        timeCells.add(new TimeCell(18, 41, 'P'));
        timeCells.add(new TimeCell(18, 43, 'Q'));
        timeCells.add(new TimeCell(18, 46, 'R'));
        timeCells.add(new TimeCell(18, 48, 'S'));
        timeCells.add(new TimeCell(18, 51, 'T', true));
        timeCells.add(new TimeCell(18, 53, 'U', true));
        timeCells.add(new TimeCell(18, 55, 'V', true));
        timeCells.add(new TimeCell(18, 56, 'W', true, true, false));

        // 20 metres
        timeCells.add(new TimeCell(20, 6, 'A'));
        timeCells.add(new TimeCell(20, 10, 'B'));
        timeCells.add(new TimeCell(20, 13, 'C'));
        timeCells.add(new TimeCell(20, 15, 'D'));
        timeCells.add(new TimeCell(20, 16, 'E'));
        timeCells.add(new TimeCell(20, 18, 'F'));
        timeCells.add(new TimeCell(20, 20, 'G'));
        timeCells.add(new TimeCell(20, 21, 'H'));
        timeCells.add(new TimeCell(20, 23, 'I'));
        timeCells.add(new TimeCell(20, 25, 'J'));
        timeCells.add(new TimeCell(20, 26, 'K'));
        timeCells.add(new TimeCell(20, 28, 'L'));
        timeCells.add(new TimeCell(20, 30, 'M'));
        timeCells.add(new TimeCell(20, 32, 'N'));
        timeCells.add(new TimeCell(20, 34, 'O'));
        timeCells.add(new TimeCell(20, 36, 'P'));
        timeCells.add(new TimeCell(20, 38, 'Q'));
        timeCells.add(new TimeCell(20, 40, 'R', true));
        timeCells.add(new TimeCell(20, 42, 'S', true));
        timeCells.add(new TimeCell(20, 44, 'T', true));
        timeCells.add(new TimeCell(20, 45, 'U', true, true, false));

        // 22 metres
        timeCells.add(new TimeCell(22, 5, 'A'));
        timeCells.add(new TimeCell(22, 9, 'B'));
        timeCells.add(new TimeCell(22, 12, 'C'));
        timeCells.add(new TimeCell(22, 13, 'D'));
        timeCells.add(new TimeCell(22, 15, 'E'));
        timeCells.add(new TimeCell(22, 16, 'F'));
        timeCells.add(new TimeCell(22, 18, 'G'));
        timeCells.add(new TimeCell(22, 19, 'H'));
        timeCells.add(new TimeCell(22, 21, 'I'));
        timeCells.add(new TimeCell(22, 22, 'J'));
        timeCells.add(new TimeCell(22, 24, 'K'));
        timeCells.add(new TimeCell(22, 25, 'L'));
        timeCells.add(new TimeCell(22, 27, 'M'));
        timeCells.add(new TimeCell(22, 29, 'N'));
        timeCells.add(new TimeCell(22, 30, 'O'));
        timeCells.add(new TimeCell(22, 32, 'P', true));
        timeCells.add(new TimeCell(22, 34, 'Q', true));
        timeCells.add(new TimeCell(22, 36, 'R', true));
        timeCells.add(new TimeCell(22, 37, 'S', true, true, false));

        // 25 metres
        timeCells.add(new TimeCell(25, 4, 'A'));
        timeCells.add(new TimeCell(25, 8, 'B'));
        timeCells.add(new TimeCell(25, 10, 'C'));
        timeCells.add(new TimeCell(25, 11, 'D'));
        timeCells.add(new TimeCell(25, 13, 'E'));
        timeCells.add(new TimeCell(25, 14, 'F'));
        timeCells.add(new TimeCell(25, 15, 'G'));
        timeCells.add(new TimeCell(25, 17, 'H'));
        timeCells.add(new TimeCell(25, 18, 'I'));
        timeCells.add(new TimeCell(25, 19, 'J'));
        timeCells.add(new TimeCell(25, 21, 'K'));
        timeCells.add(new TimeCell(25, 22, 'L'));
        timeCells.add(new TimeCell(25, 23, 'M'));
        timeCells.add(new TimeCell(25, 25, 'N', true));
        timeCells.add(new TimeCell(25, 26, 'O', true));
        timeCells.add(new TimeCell(25, 28, 'P', true));
        timeCells.add(new TimeCell(25, 29, 'Q', true, true, false));

        // 30 metres
        timeCells.add(new TimeCell(30, 3, 'A', true));
        timeCells.add(new TimeCell(30, 6, 'B', true));
        timeCells.add(new TimeCell(30, 8, 'C', true));
        timeCells.add(new TimeCell(30, 9, 'D', true));
        timeCells.add(new TimeCell(30, 10, 'E', true));
        timeCells.add(new TimeCell(30, 11, 'F', true));
        timeCells.add(new TimeCell(30, 12, 'G', true));
        timeCells.add(new TimeCell(30, 13, 'H', true));
        timeCells.add(new TimeCell(30, 14, 'I', true));
        timeCells.add(new TimeCell(30, 15, 'J', true));
        timeCells.add(new TimeCell(30, 16, 'K', true));
        timeCells.add(new TimeCell(30, 17, 'L', true));
        timeCells.add(new TimeCell(30, 19, 'M', true));
        timeCells.add(new TimeCell(30, 20, 'N', true, true, false));

        // 35 metres
        timeCells.add(new TimeCell(35, 3, 'A', true));
        timeCells.add(new TimeCell(35, 5, 'B', true));
        timeCells.add(new TimeCell(35, 7, 'C', true));
        timeCells.add(new TimeCell(35, 8, 'D', true));
        timeCells.add(new TimeCell(35, -1, 'E', true, false, true));
        timeCells.add(new TimeCell(35, 9, 'F', true));
        timeCells.add(new TimeCell(35, 10, 'G', true));
        timeCells.add(new TimeCell(35, 11, 'H', true));
        timeCells.add(new TimeCell(35, 12, 'I', true));
        timeCells.add(new TimeCell(35, 13, 'J', true));
        timeCells.add(new TimeCell(35, 14, 'K', true, true, false));

        // 40 metres
        timeCells.add(new TimeCell(40, -1, 'A', true, false, true));
        timeCells.add(new TimeCell(40, 5, 'B', true));
        timeCells.add(new TimeCell(40, 6, 'C', true));
        timeCells.add(new TimeCell(40, -1, 'D', true, false, true));
        timeCells.add(new TimeCell(40, 7, 'E', true));
        timeCells.add(new TimeCell(40, 8, 'F', true));
        timeCells.add(new TimeCell(40, 9, 'G', true, true, false));

        // 42 metres
        timeCells.add(new TimeCell(42, -1, 'A', true, false, true));
        timeCells.add(new TimeCell(42, 4, 'B', true));
        timeCells.add(new TimeCell(42, -1, 'C', true, false, true));
        timeCells.add(new TimeCell(42, 6, 'D', true));
        timeCells.add(new TimeCell(42, 7, 'E', true));
        timeCells.add(new TimeCell(42, 8, 'F', true, true, false));

        // Initialize SurfaceIntervalCreditCells
        surfaceIntervalCreditCells = new ArrayList<>();

        // A
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('A', "00:00", "00:03", 'A'));

        // B
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('B', "00:00", "00:47", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('B', "00:48", "03:48", 'A'));

        // C
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('C', "00:00", "00:21", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('C', "00:22", "01:09", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('C', "01:10", "04:10", 'A'));

        // D
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('D', "00:00", "00:08", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('D', "00:09", "00:30", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('D', "00:31", "01:18", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('D', "01:19", "04:19", 'A'));

        // E
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('E', "00:00", "00:07", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('E', "00:08", "00:16", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('E', "00:17", "00:38", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('E', "00:39", "01:27", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('E', "01:28", "04:28", 'A'));

        // F
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('F', "00:00", "00:07", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('F', "00:08", "00:15", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('F', "00:16", "00:24", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('F', "00:25", "00:46", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('F', "00:47", "01:34", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('F', "01:35", "04:35", 'A'));

        // G
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "00:00", "00:06", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "00:07", "00:13", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "00:14", "00:22", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "00:23", "00:31", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "00:32", "00:53", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "00:54", "01:41", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('G', "01:42", "04:42", 'A'));

        // H
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "00:00", "00:05", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "00:06", "00:12", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "00:13", "00:20", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "00:21", "00:28", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "00:29", "00:37", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "00:38", "00:59", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "01:00", "01:47", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('H', "01:48", "04:48", 'A'));

        // I
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:00", "00:05", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:06", "00:11", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:12", "00:18", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:19", "00:26", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:27", "00:34", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:35", "00:43", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "00:44", "01:05", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "01:06", "01:53", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('I', "01:54", "04:54", 'A'));

        // J
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:00", "00:05", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:06", "00:11", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:12", "00:17", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:18", "00:24", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:25", "00:31", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:32", "00:40", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:41", "00:49", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "00:50", "01:11", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "01:12", "01:59", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('J', "02:00", "05:00", 'A'));

        // K
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:00", "00:04", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:05", "00:10", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:11", "00:16", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:17", "00:22", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:23", "00:29", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:30", "00:37", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:38", "00:45", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:46", "00:54", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "00:55", "01:16", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "01:17", "02:04", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('K', "02:05", "05:05", 'A'));

        // L
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:00", "00:04", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:05", "00:09", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:10", "00:15", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:16", "00:21", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:22", "00:27", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:28", "00:34", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:35", "00:42", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:43", "00:50", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "00:51", "00:59", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "01:00", "01:21", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "01:22", "02:09", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('L', "02:10", "05:10", 'A'));

        // M
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:00", "00:04", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:05", "00:09", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:10", "00:14", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:15", "00:19", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:20", "00:25", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:26", "00:32", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:33", "00:39", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:40", "00:46", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:47", "00:55", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "00:56", "01:04", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "01:05", "01:25", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "01:26", "02:14", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('M', "02:15", "05:15", 'A'));

        // N
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:00", "00:03", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:04", "00:08", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:09", "00:13", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:14", "00:18", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:19", "00:24", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:25", "00:30", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:31", "00:36", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:37", "00:43", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:44", "00:51", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "00:52", "00:59", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "01:00", "01:08", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "01:09", "01:30", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "01:31", "02:18", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('N', "02:19", "05:19", 'A'));

        // O
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:00", "00:03", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:04", "00:08", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:09", "00:12", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:13", "00:17", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:18", "00:23", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:24", "00:28", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:29", "00:34", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:35", "00:41", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:42", "00:47", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:48", "00:55", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "00:56", "01:03", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "01:04", "01:12", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "01:13", "01:34", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "01:35", "02:23", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('O', "02:24", "05:24", 'A'));

        // P
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:00", "00:03", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:04", "00:07", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:08", "00:12", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:13", "00:16", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:17", "00:21", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:22", "00:27", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:28", "00:32", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:33", "00:38", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:39", "00:45", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:46", "00:51", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "00:52", "00:59", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "01:00", "01:07", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "01:08", "01:16", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "01:17", "01:38", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "01:39", "02:27", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('P', "02:28", "05:28", 'A'));

        // Q
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:00", "00:03", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:04", "00:07", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:08", "00:11", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:12", "00:16", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:17", "00:20", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:21", "00:25", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:26", "00:30", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:31", "00:36", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:37", "00:42", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:43", "00:48", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:49", "00:55", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "00:56", "01:03", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "01:04", "01:11", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "01:12", "01:20", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "01:21", "01:42", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "01:43", "02:30", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Q', "02:31", "05:31", 'A'));

        // R
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:00", "00:03", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:04", "00:07", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:08", "00:11", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:12", "00:15", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:16", "00:19", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:20", "00:24", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:25", "00:29", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:30", "00:34", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:35", "00:40", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:41", "00:46", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:47", "00:52", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "00:53", "00:59", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "01:00", "01:07", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "01:08", "01:15", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "01:16", "01:24", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "01:25", "01:46", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "01:47", "02:34", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('R', "02:35", "05:35", 'A'));

        // S
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:00", "00:03", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:04", "00:06", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:07", "00:10", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:11", "00:14", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:15", "00:18", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:19", "00:23", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:24", "00:27", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:28", "00:32", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:33", "00:38", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:39", "00:43", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:44", "00:49", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:50", "00:56", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "00:57", "01:03", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "01:04", "01:10", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "01:11", "01:18", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "01:19", "01:27", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "01:28", "01:49", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "01:50", "02:38", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('S', "02:39", "05:39", 'A'));

        // T
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:00", "00:02", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:03", "00:06", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:07", "00:10", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:11", "00:13", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:14", "00:17", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:18", "00:22", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:23", "00:26", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:27", "00:31", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:32", "00:36", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:37", "00:41", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:42", "00:47", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:48", "00:53", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "00:54", "00:59", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "01:00", "01:06", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "01:07", "01:13", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "01:14", "01:22", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "01:23", "01:31", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "01:32", "01:53", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "01:54", "02:41", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('T', "02:42", "05:42", 'A'));

        // U
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:00", "00:02", 'U'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:03", "00:06", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:07", "00:09", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:10", "00:13", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:14", "00:17", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:18", "00:21", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:22", "00:25", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:26", "00:29", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:30", "00:34", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:35", "00:39", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:40", "00:44", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:45", "00:50", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:51", "00:56", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "00:57", "01:02", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "01:03", "01:09", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "01:10", "01:17", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "01:18", "01:25", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "01:26", "01:34", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "01:35", "01:56", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "01:57", "02:44", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('U', "02:45", "05:45", 'A'));

        // V
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:00", "00:02", 'V'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:03", "00:05", 'U'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:06", "00:09", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:10", "00:12", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:13", "00:16", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:17", "00:20", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:21", "00:24", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:25", "00:28", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:29", "00:33", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:34", "00:37", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:38", "00:42", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:43", "00:47", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:48", "00:54", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "00:54", "00:59", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "01:00", "01:05", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "01:06", "01:12", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "01:13", "01:20", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "01:21", "01:28", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "01:29", "01:37", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "01:38", "01:59", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "02:00", "02:47", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('V', "02:48", "05:48", 'A'));

        // W
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:00", "00:02", 'W'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:03", "00:05", 'V'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:06", "00:08", 'U'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:09", "00:12", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:13", "00:15", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:16", "00:19", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:20", "00:23", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:24", "00:27", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:28", "00:31", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:32", "00:36", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:37", "00:40", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:41", "00:45", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:46", "00:50", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:51", "00:56", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "00:57", "01:02", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "01:03", "01:08", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "01:09", "01:15", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "01:16", "01:23", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "01:24", "01:31", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "01:32", "01:40", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "01:41", "02:02", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "02:03", "02:50", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('W', "02:51", "05:51", 'A'));

        // X
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:00", "00:02", 'X'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:03", "00:05", 'W'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:06", "00:08", 'V'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:09", "00:11", 'U'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:12", "00:15", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:16", "00:18", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:19", "00:22", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:23", "00:26", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:27", "00:30", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:31", "00:34", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:35", "00:39", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:40", "00:43", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:44", "00:48", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:49", "00:53", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "00:54", "00:59", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:00", "01:05", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:06", "01:11", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:12", "01:18", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:19", "01:26", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:27", "01:34", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:35", "01:43", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "01:44", "02:05", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "02:06", "02:53", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('X', "02:54", "05:54", 'A'));

        // Y
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:00", "00:02", 'Y'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:03", "00:05", 'X'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:06", "00:08", 'W'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:09", "00:11", 'V'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:12", "00:14", 'U'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:15", "00:18", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:19", "00:21", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:22", "00:25", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:26", "00:29", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:30", "00:33", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:34", "00:37", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:38", "00:41", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:42", "00:46", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:47", "00:51", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:52", "00:56", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "00:57", "01:02", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:03", "01:08", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:09", "01:14", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:15", "01:21", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:22", "01:29", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:30", "01:37", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:38", "01:46", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "01:47", "02:08", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "02:09", "02:56", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Y', "02:57", "05:57", 'A'));

        // Z
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:00", "00:02", 'Z'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:03", "00:05", 'Y'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:06", "00:08", 'X'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:09", "00:11", 'W'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:12", "00:14", 'V'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:15", "00:17", 'U'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:18", "00:20", 'T'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:21", "00:24", 'S'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:25", "00:28", 'R'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:29", "00:31", 'Q'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:32", "00:35", 'P'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:36", "00:40", 'O'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:41", "00:44", 'N'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:45", "00:49", 'M'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:50", "00:54", 'L'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "00:55", "00:59", 'K'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:00", "01:05", 'J'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:06", "01:11", 'I'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:12", "01:17", 'H'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:18", "01:24", 'G'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:25", "01:31", 'F'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:32", "01:40", 'E'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:41", "01:49", 'D'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "01:50", "02:11", 'C'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "02:12", "02:59", 'B'));
        surfaceIntervalCreditCells.add(new SurfaceIntervalCreditCell('Z', "03:00", "06:00", 'A'));

        // Initialize RepetitiveDiveCells
        repetitiveDiveCells = new ArrayList<>();

        // 10 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('Z', 10, 219, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Y', 10, 199, 20));
        repetitiveDiveCells.add(new RepetitiveDiveCell('X', 10, 178, 41));
        repetitiveDiveCells.add(new RepetitiveDiveCell('W', 10, 160, 59));
        repetitiveDiveCells.add(new RepetitiveDiveCell('V', 10, 145, 74));
        repetitiveDiveCells.add(new RepetitiveDiveCell('U', 10, 133, 86));
        repetitiveDiveCells.add(new RepetitiveDiveCell('T', 10, 122, 97));
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 10, 112, 107));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 10, 104, 115));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 10, 95, 124));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 10, 88, 131));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 10, 82, 137));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 10, 75, 144));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 10, 70, 149));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 10, 64, 155));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 10, 59, 160));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 10, 54, 165));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 10, 50, 169));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 10, 45, 174));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 10, 41, 178));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 10, 37, 182));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 10, 34, 185));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 10, 30, 189));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 10, 26, 193));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 10, 20, 199));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 10, 10, 209));

        // 12 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('Z', 12, 147, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Y', 12, 134, 13));
        repetitiveDiveCells.add(new RepetitiveDiveCell('X', 12, 125, 22));
        repetitiveDiveCells.add(new RepetitiveDiveCell('W', 12, 116, 31));
        repetitiveDiveCells.add(new RepetitiveDiveCell('V', 12, 108, 39));
        repetitiveDiveCells.add(new RepetitiveDiveCell('U', 12, 101, 46));
        repetitiveDiveCells.add(new RepetitiveDiveCell('T', 12, 94, 53));
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 12, 88, 59));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 12, 82, 65));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 12, 76, 71));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 12, 71, 76));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 12, 66, 81));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 12, 62, 85));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 12, 57, 90));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 12, 53, 94));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 12, 49, 98));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 12, 45, 102));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 12, 42, 105));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 12, 38, 109));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 12, 35, 112));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 12, 32, 115));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 12, 29, 118));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 12, 26, 121));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 12, 23, 124));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 12, 17, 130));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 12, 9, 138));

        // 14 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('Y', 14, 98, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('X', 14, 92, 6));
        repetitiveDiveCells.add(new RepetitiveDiveCell('W', 14, 87, 11));
        repetitiveDiveCells.add(new RepetitiveDiveCell('V', 14, 82, 16));
        repetitiveDiveCells.add(new RepetitiveDiveCell('U', 14, 77, 21));
        repetitiveDiveCells.add(new RepetitiveDiveCell('T', 14, 73, 25));
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 14, 68, 30));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 14, 64, 34));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 14, 61, 37));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 14, 57, 41));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 14, 53, 45));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 14, 50, 48));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 14, 47, 51));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 14, 43, 55));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 14, 40, 58));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 14, 37, 61));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 14, 35, 63));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 14, 32, 66));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 14, 29, 69));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 14, 27, 71));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 14, 24, 74));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 14, 22, 76));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 14, 19, 79));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 14, 15, 83));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 14, 8, 90));

        // 16 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('X', 16, 72, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('W', 16, 70, 2));
        repetitiveDiveCells.add(new RepetitiveDiveCell('V', 16, 67, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('U', 16, 63, 9));
        repetitiveDiveCells.add(new RepetitiveDiveCell('T', 16, 60, 12));
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 16, 56, 16));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 16, 53, 19));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 16, 50, 22));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 16, 48, 24));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 16, 45, 27));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 16, 42, 30));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 16, 39, 33));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 16, 37, 35));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 16, 34, 38));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 16, 32, 40));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 16, 29, 43));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 16, 27, 45));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 16, 25, 47));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 16, 23, 49));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 16, 21, 51));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 16, 19, 53));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 16, 17, 55));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 16, 13, 59));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 16, 7, 65));

        // 18 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('W', 18, 56, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('V', 18, 55, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('U', 18, 53, 3));
        repetitiveDiveCells.add(new RepetitiveDiveCell('T', 18, 51, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 18, 48, 8));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 18, 46, 10));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 18, 43, 13));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 18, 41, 15));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 18, 39, 17));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 18, 36, 20));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 18, 34, 22));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 18, 32, 24));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 18, 30, 26));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 18, 28, 28));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 18, 26, 30));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 18, 24, 32));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 18, 22, 34));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 18, 20, 36));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 18, 18, 38));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 18, 16, 40));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 18, 15, 41));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 18, 11, 45));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 18, 6, 50));

        // 20 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('U', 20, 45, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('T', 20, 44, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 20, 42, 3));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 20, 40, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 20, 38, 7));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 20, 36, 9));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 20, 34, 11));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 20, 32, 13));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 20, 30, 15));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 20, 28, 17));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 20, 26, 19));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 20, 25, 20));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 20, 23, 22));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 20, 21, 24));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 20, 20, 25));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 20, 18, 27));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 20, 16, 29));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 20, 15, 30));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 20, 13, 32));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 20, 10, 35));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 20, 6, 39));

        // 22 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('S', 22, 37, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('R', 22, 36, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 22, 34, 3));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 22, 32, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 22, 30, 7));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 22, 29, 8));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 22, 27, 10));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 22, 25, 12));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 22, 24, 13));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 22, 22, 15));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 22, 21, 16));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 22, 19, 18));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 22, 18, 19));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 22, 16, 21));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 22, 15, 22));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 22, 13, 24));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 22, 12, 25));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 22, 9, 28));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 22, 5, 32));

        // 25 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('Q', 25, 29, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('P', 25, 28, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('O', 25, 26, 3));
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 25, 25, 4));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 25, 23, 6));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 25, 22, 7));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 25, 21, 8));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 25, 19, 10));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 25, 18, 11));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 25, 17, 12));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 25, 15, 14));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 25, 14, 15));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 25, 13, 16));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 25, 11, 18));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 25, 10, 19));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 25, 8, 21));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 25, 4, 25));

        // 30 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('N', 30, 20, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('M', 30, 19, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('L', 30, 17, 3));
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 30, 16, 4));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 30, 15, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 30, 14, 6));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 30, 13, 7));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 30, 12, 8));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 30, 11, 9));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 30, 10, 10));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 30, 9, 11));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 30, 8, 12));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 30, 6, 14));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 30, 3, 17));

        // 35 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('K', 35, 14, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('J', 35, 13, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('I', 35, 12, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('H', 35, 11, 3));
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 35, 10, 4));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 35, 9, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 35, 9, 5));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 35, 8, 6));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 35, 7, 7));
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 35, 5, 9));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 35, 3, 11));

        // 40 m
        repetitiveDiveCells.add(new RepetitiveDiveCell('G', 40, 9, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('F', 40, 8, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('E', 40, 7, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('D', 40, 7, -1));
        repetitiveDiveCells.add(new RepetitiveDiveCell('C', 40, 6, 3)); // this is NULL on the tables but 3 in the eRDPMl
        repetitiveDiveCells.add(new RepetitiveDiveCell('B', 40, 5, 4));
        repetitiveDiveCells.add(new RepetitiveDiveCell('A', 40, 2, 7));

    }

    public Character getPressureGroup(int depth, int duration) {
        TimeCell timeCell = getTimeCell(depth, duration);
        return timeCell != null ? timeCell.getEndingPressureGroup() : '-';
    }

    public char getPressureGroupAfterSurfaceInterval(char startPressureGroup, Duration duration) {
//        System.out.println("from getPressureGroupAfterSurfaceInterval() start: " + startPressureGroup
//                + "  And the duration is : " + duration);
        for (SurfaceIntervalCreditCell cell : surfaceIntervalCreditCells) {
            if (cell.getStartPressureGroup() == startPressureGroup) {
                // Check if the duration is within the range of this cell
                if (isDurationAfter(cell.getFrom(), duration) && isDurationBefore(cell.getTo(), duration)) {
                    return cell.getEndingPressureGroup();
                }
            }
        }
        return MinPressureGroup;
    }

    // Helper method to check if duration is after another duration
    private boolean isDurationAfter(Duration base, Duration other) {
        return base.compareTo(other) < 0;
    }

    // Helper method to check if duration is before another duration
    private boolean isDurationBefore(Duration base, Duration other) {
        return base.compareTo(other) > 0;
    }

//    public TimeCell getMaxForDepth(int depth) {
//        Integer tableDepth = getTableDepth(depth);
//        return tableDepth != null ? timeCells.stream()
//                .filter(x -> x.getDepth() == tableDepth)
//                .max(Comparator.comparing(TimeCell::getDuration))
//                .orElse(null) : null;
//    }
    public Integer getTableDepth(int depth) {
        return timeCells.stream()
                .filter(x -> x.getDepth() >= depth)
                .map(TimeCell::getDepth)
                .findFirst()
                .orElse(null);
    }

    public TimeCell getTimeCell(int depth, int duration) {

        for (TimeCell timeCell : timeCells) {
            if (timeCell.getDepth() >= depth && timeCell.getDuration() >= duration && timeCell.getDuration() != -1) {
                return timeCell;
            }

//        return timeCells.stream()
//                .filter(x -> x.getDepth() >= depth && x.getDuration() >= duration)
//                .findFirst()
//                .orElse(null);
        }
        return null;
    }

    public Integer getNoDecompressionLimitForDepth(int depth) {
        Integer tableDepth = getTableDepth(depth);
        return tableDepth != null ? timeCells.stream()
                .filter(x -> x.getDepth() == tableDepth)
                .max(Comparator.comparing(TimeCell::getDuration))
                .map(TimeCell::getDuration)
                .orElse(null) : null;
    }

    public RepetitiveDiveCell getRepetitiveDiveCell(char pressureGroup, int depth) {
        return repetitiveDiveCells.stream()
                .filter(x -> x.getPressureGroup() == pressureGroup && x.getDepth() >= depth)
                .findFirst()
                .orElse(null);
    }

    public List<Dive> calculateDivePlan(List<Dive> dives) {
        if (dives.isEmpty()) {
            return null;
        }

        if (dives.get(0).getPressureGroupBeforeDive() == null) {
            dives.get(0).setPressureGroupBeforeDive(MinPressureGroup);
        }

        List<Dive> divePlan = new ArrayList<>();

        for (int i = 0; i < dives.size(); i++) {
            Dive dive = dives.get(i);

            dive.setTotalBottomTime(dive.getPlannedBottomTime() + dive.getResidualNitrogenTimeBroughtForward());

            if (dive.getAdjustedNoDecompressionLimitBroughtForward() == 0 || dive.getPlannedBottomTime() <= dive.getAdjustedNoDecompressionLimitBroughtForward()) {
                TimeCell timeCell = getTimeCell(dive.getPlannedDepth(), dive.getTotalBottomTime());

                if (timeCell != null) {
                    dive.setPressureGroupAfterDive(timeCell.getEndingPressureGroup());
                    dive.setSafetyStop(timeCell.isSafetyStopRequired());

                    if (i + 1 < dives.size()) {
                        SurfaceInterval si = getMinimumSurfaceInterval(
                                dive.getPressureGroupAfterDive(),
                                dives.get(i + 1).getPlannedDepth(),
                                dives.get(i + 1).getPlannedBottomTime(),
                                dive.getSurfaceInterval()
                        );

                        if (si != null) {
                            dive.setResidualNitrogenTimeToCarryForward(si.getResidualNitrogenTime());
                            dive.setAdjustedNoDecompressionLimitToCarryForward(si.getAdjustedNoDecompressionLimit());
                            dive.setSurfaceInterval(si.getMinimumSurfaceInterval());
                            dive.setPressureGroupAfterSurfaceInterval(si.getPressureGroupAfterSurfaceInterval());

                            dives.get(i + 1).setResidualNitrogenTimeBroughtForward(si.getResidualNitrogenTime());
                            dives.get(i + 1).setAdjustedNoDecompressionLimitBroughtForward(si.getAdjustedNoDecompressionLimit());
                            dives.get(i + 1).setPressureGroupBeforeDive(si.getPressureGroupAfterSurfaceInterval());
                        }
                    }

                    divePlan.add(dive);
                }
            }
        }

        return divePlan;
    }

    private long getDurationMillis(Duration duration) {
        return duration.toMillis();
    }

    public SurfaceInterval getMinimumSurfaceInterval(char pressureGroupBeforeSurfaceInterval, int depth, int duration, Duration requestedMinimumSurfaceInterval) {
        Duration minimumSurfaceInterval = adjustMinimumSurfaceIntervalForRuleWxyz(pressureGroupBeforeSurfaceInterval);

        char originalPressureGroupBeforeSurfaceInterval = pressureGroupBeforeSurfaceInterval;

        if (requestedMinimumSurfaceInterval != null && requestedMinimumSurfaceInterval.compareTo(minimumSurfaceInterval) > 0) {
            minimumSurfaceInterval = requestedMinimumSurfaceInterval;
        }

        if (minimumSurfaceInterval.toMinutes() > 0) {
            SurfaceIntervalCreditCell surfaceIntervalCreditCell = null;
            for (SurfaceIntervalCreditCell cell : surfaceIntervalCreditCells) {
                // Compare total milliseconds instead of using isBefore
                if (cell.getStartPressureGroup() == pressureGroupBeforeSurfaceInterval && getDurationMillis(cell.getTo()) >= getDurationMillis(minimumSurfaceInterval)) {
                    surfaceIntervalCreditCell = cell;
                    break;
                }
            }
            if (surfaceIntervalCreditCell != null) {
                pressureGroupBeforeSurfaceInterval = surfaceIntervalCreditCell.getEndingPressureGroup();
            } else {
                pressureGroupBeforeSurfaceInterval = MinPressureGroup;
            }
        }

        RepetitiveDiveCell repetitiveDiveCell = null;
        for (RepetitiveDiveCell cell : repetitiveDiveCells) {
            if (cell.getDepth() >= depth && cell.getPressureGroup() <= pressureGroupBeforeSurfaceInterval && cell.getAdjustedNoDecompressionLimit() >= duration) {
                repetitiveDiveCell = cell;
                break;
            }
        }

        if (repetitiveDiveCell != null && repetitiveDiveCell.getAdjustedNoDecompressionLimit() != null) {
            SurfaceIntervalCreditCell surfaceIntervalCreditCell = null;
            for (SurfaceIntervalCreditCell cell : surfaceIntervalCreditCells) {
                if (cell.getStartPressureGroup() == originalPressureGroupBeforeSurfaceInterval && cell.getEndingPressureGroup() <= repetitiveDiveCell.getPressureGroup()) {
                    surfaceIntervalCreditCell = cell;
                    break;
                }
            }
            if (surfaceIntervalCreditCell != null) {
                return new SurfaceInterval(
                        originalPressureGroupBeforeSurfaceInterval,
                        surfaceIntervalCreditCell.getFrom(),
                        surfaceIntervalCreditCell.getTo(),
                        surfaceIntervalCreditCell.getEndingPressureGroup(),
                        repetitiveDiveCell.getResidualNitrogenTime(),
                        repetitiveDiveCell.getAdjustedNoDecompressionLimit()
                );
            }
        }

        // can't do the dive
        return null;
    }

    public static Duration adjustMinimumSurfaceIntervalForRuleWxyz(char pressureGroupBeforeSurfaceInterval) {
        switch (pressureGroupBeforeSurfaceInterval) {
            case 'W':
            case 'X':
                return Duration.ofHours(1);
            case 'Y':
            case 'Z':
                return Duration.ofHours(3);
            default:
                return Duration.ZERO;
        }
    }

    public char getPressureGroupAfterSurfaceInterval(char startPressureGroup, int depth, int duration, Duration requestedMinimumSurfaceInterval) {
        Duration minimumSurfaceInterval = adjustMinimumSurfaceIntervalForRuleWxyz(startPressureGroup);

        if (requestedMinimumSurfaceInterval != null && requestedMinimumSurfaceInterval.compareTo(minimumSurfaceInterval) > 0) {
            minimumSurfaceInterval = requestedMinimumSurfaceInterval;
        }

        char pressureGroupBeforeSurfaceInterval = startPressureGroup;

        if (minimumSurfaceInterval.toMinutes() > 0) {
            SurfaceIntervalCreditCell surfaceIntervalCreditCell = null;
            for (SurfaceIntervalCreditCell cell : surfaceIntervalCreditCells) {
                if (cell.getStartPressureGroup() == startPressureGroup && getDurationMillis(cell.getTo()) >= getDurationMillis(minimumSurfaceInterval)) {
                    surfaceIntervalCreditCell = cell;
                    break;
                }
            }
            if (surfaceIntervalCreditCell != null) {
                pressureGroupBeforeSurfaceInterval = surfaceIntervalCreditCell.getEndingPressureGroup();
            } else {
                pressureGroupBeforeSurfaceInterval = MinPressureGroup;
            }
        }

        RepetitiveDiveCell repetitiveDiveCell = null;
        for (RepetitiveDiveCell cell : repetitiveDiveCells) {
            if (cell.getDepth() >= depth && cell.getPressureGroup() <= pressureGroupBeforeSurfaceInterval && cell.getAdjustedNoDecompressionLimit() >= duration) {
                repetitiveDiveCell = cell;
                break;
            }
        }

        if (repetitiveDiveCell != null && repetitiveDiveCell.getAdjustedNoDecompressionLimit() != null) {
            SurfaceIntervalCreditCell surfaceIntervalCreditCell = null;
            for (SurfaceIntervalCreditCell cell : surfaceIntervalCreditCells) {
                if (cell.getStartPressureGroup() == startPressureGroup && cell.getEndingPressureGroup() <= repetitiveDiveCell.getPressureGroup()) {
                    surfaceIntervalCreditCell = cell;
                    break;
                }
            }
            if (surfaceIntervalCreditCell != null) {
                return surfaceIntervalCreditCell.getEndingPressureGroup();
            }
        }

        // If unable to calculate, return minimum pressure group
        return MinPressureGroup;
    }

    public int calculateResidualNitrogenTime(char pressureGroup, int depth) {
        return repetitiveDiveCells.stream()
                .filter(rdc -> rdc.getPressureGroup() == pressureGroup && rdc.getDepth() == depth)
                .map(RepetitiveDiveCell::getResidualNitrogenTime)
                .findFirst()
                .orElse(0);
    }

    public int getTheDecompressionLimit(char pressureGroup, int depth) {
        return repetitiveDiveCells.stream()
                .filter(rdc -> rdc.getPressureGroup() == pressureGroup && rdc.getDepth() == depth)
                .map(RepetitiveDiveCell::getAdjustedNoDecompressionLimit)
                .findFirst()
                .orElse(0);
    }

    public int calculateAdjustedNoDecompressionLimit(int depth, int residualNitrogenTime) {
        return timeCells.stream()
                .filter(tc -> tc.getDepth() == depth && tc.getEndingPressureGroup() == 'A')
                .map(TimeCell::getDuration)
                .filter(java.util.Objects::nonNull)
                .mapToInt(Integer::intValue)
                .findFirst()
                .orElse(0) - residualNitrogenTime;
    }

}
