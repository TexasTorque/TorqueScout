/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.utils;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Entry {
    public final static Integer[] climbScores = { 0, 4, 6, 10, 15 };
    public final static String[] climbNames = { "None", "Low", "Mid", "High", "Traversal" };

    public static final String header = "Team Number,Match Number,"
            + "Alliance Color,Taxi,Auto Lower,Auto Upper,Auto Missed,Auto Intaken,"
            + "Teleop Lower,Teleop Upper,Teleop Missed,Teleop Intaken,Climb Level,Climb Time,Name,Comment,";

    public static int valueOfClimb(String climb) {
        for (int i = 0; i < climbNames.length; i++)
            if (climbNames[i].equals(climb))
                return climbScores[i];
        return 0;
    }

    public final Integer teamNumber;
    public final Integer matchNumber;

    public final String allianceColor;

    public final boolean taxi;

    public final Integer autoLower;
    public final Integer autoUpper;
    public final Integer autoMissed;
    public final Integer autoIntaken;

    public final Integer teleopLower;
    public final Integer teleopUpper;
    public final Integer teleopMissed;
    public final Integer teleopIntaken;

    public final Integer climb;
    public final Integer climbTime;

    public final String name;
    public final String comment;

    public final Integer autoScore;
    public final Integer teleopScore;
    public final Integer totalScore;

    public final Long autoAccuracy;
    public final Long teleopAccuracy;
    public final Long totalAccuracy;

    public Entry(
            Integer teamNumber,
            Integer matchNumber,
            String allianceColor,
            boolean taxi,
            Integer autoLower,
            Integer autoUpper,
            Integer autoMissed,
            Integer autoIntaken,
            Integer teleopLower,
            Integer teleopUpper,
            Integer teleopMissed,
            Integer teleopIntaken,
            Integer climb,
            Integer climbTime,
            String name,
            String comment) {
        this.teamNumber = teamNumber;
        this.matchNumber = matchNumber;
        this.allianceColor = allianceColor;
        this.taxi = taxi;
        this.autoLower = autoLower;
        this.autoUpper = autoUpper;
        this.autoMissed = autoMissed;
        this.autoIntaken = 0;
        this.teleopLower = teleopLower;
        this.teleopUpper = teleopUpper;
        this.teleopMissed = teleopMissed;
        this.teleopIntaken = 0;
        this.climb = climb;
        this.climbTime = climbTime;
        this.name = name;
        this.comment = comment;

        this.autoScore = (taxi ? 2 : 0) + autoLower * 2 + autoUpper * 4;
        this.teleopScore = climbScores[climb] + teleopLower + teleopUpper * 2;
        this.totalScore = this.autoScore + this.teleopScore;

        this.autoAccuracy = Math.min(100, Math.max(0, Math.round(
                (this.autoLower + this.autoUpper + 0.)
                        / (this.autoLower + this.autoUpper + this.autoMissed)
                        * 100)));

        this.teleopAccuracy = Math.min(100, Math.max(0, Math.round(
                (this.teleopLower + this.teleopUpper + 0.)
                        / (this.teleopLower + this.teleopUpper + this.teleopMissed)
                        * 100)));

        this.totalAccuracy = (this.autoAccuracy + this.teleopAccuracy) / 2;
    }

    public static TableColumn<Entry, ?> createColumn(String property) {
        return createColumn(property, DataUtils.titleCase(
                DataUtils.splitCamelCase(property)));
    }

    public static TableColumn<Entry, ?> createColumn(String property, String name) {
        TableColumn<Entry, ?> column = new TableColumn<>(name);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        return column;
    }

    public String toCSV() {
        return String.format("%d,%d,%s,%b,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s,%s",
                teamNumber, matchNumber, allianceColor, taxi,
                autoLower, autoUpper, autoMissed, 0,
                teleopLower, teleopUpper, teleopMissed, 0,
                climb, climbTime, name, comment.replace(",", "，"));
    }

    public static Entry fromCSV(String s) {
        String[] parts = s.split(",");

        return new Entry(
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                parts[2],
                parts[3].equals("true"),
                Integer.parseInt(parts[4]),
                Integer.parseInt(parts[5]),
                Integer.parseInt(parts[6]),
                Integer.parseInt(parts[7]),
                Integer.parseInt(parts[8]),
                Integer.parseInt(parts[9]),
                Integer.parseInt(parts[10]),
                Integer.parseInt(parts[11]),
                Integer.parseInt(parts[12]),
                Integer.parseInt(parts[13]),
                parts[14],
                parts[15]);
    }

    private static final String NA = "N/A";

    public static Entry fromAveraged(ObservableList<Entry> entries) {
        boolean taxi = entries.stream().mapToInt(e -> e.getTaxiValue()).sum() / entries.size() == 1;

        int autoLower = entries.stream().mapToInt(e -> e.getAutoLower()).sum() / entries.size();
        int autoUpper = entries.stream().mapToInt(e -> e.getAutoUpper()).sum() / entries.size();
        int autoMissed = entries.stream().mapToInt(e -> e.getAutoMissed()).sum() / entries.size();
        int autoIntaken = entries.stream().mapToInt(e -> e.getAutoIntaken()).sum() / entries.size();

        int teleopLower = entries.stream().mapToInt(e -> e.getTeleopLower()).sum() / entries.size();
        int teleopUpper = entries.stream().mapToInt(e -> e.getTeleopUpper()).sum() / entries.size();
        int teleopMissed = entries.stream().mapToInt(e -> e.getTeleopMissed()).sum() / entries.size();
        int teleopIntaken = entries.stream().mapToInt(e -> e.getTeleopIntaken()).sum() / entries.size();

        double climb = entries.stream().mapToInt(e -> e.getClimbValue()).sum() / (entries.size() + 0.);
        int climbTime = entries.stream().mapToInt(e -> e.getClimbTime()).sum() / entries.size();

        Entry entry = new Entry(entries.get(0).getTeamNumber(), -1, NA, taxi,
                autoLower, autoUpper, autoMissed, autoIntaken,
                teleopLower, teleopUpper, teleopMissed, teleopIntaken,
                (int) Math.round(climb), climbTime, NA, NA);

        entry.setAvgClimbPoints(climb);
        return entry;
    }

    private Double avgClimbPoints = 0.;

    public void setAvgClimbPoints(double points) {
        this.avgClimbPoints = points;
    }

    public Double getAvgClimbPoints() {
        return DataUtils.round(avgClimbPoints, 2);
    }

    Button view = new Button("View");

    public Button getTeamButton() {
        return view;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public Integer getMatchNumber() {
        return matchNumber;
    }

    public String getAllianceColor() {
        return allianceColor;
    }

    public String getTaxi() {
        return taxi ? "Yes" : "No";
    }

    public Integer getTaxiValue() {
        return taxi ? 1 : 0;
    }

    public Integer getAutoLower() {
        return autoLower;
    }

    public Integer getAutoUpper() {
        return autoUpper;
    }

    public Integer getAutoMissed() {
        return autoMissed;
    }

    public Integer getAutoIntaken() {
        return autoIntaken;
    }

    public Integer getTeleopLower() {
        return teleopLower;
    }

    public Integer getTeleopUpper() {
        return teleopUpper;
    }

    public Integer getTeleopMissed() {
        return teleopMissed;
    }

    public Integer getTeleopIntaken() {
        return teleopIntaken;
    }

    public String getClimb() {
        return climbNames[climb];
    }

    public Integer getClimbValue() {
        return climb;
    }

    public Integer getClimbTime() {
        return climbTime;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public Integer getAutoScore() {
        return autoScore;
    }

    public Integer getTeleopScore() {
        return teleopScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public String getAutoAccuracy() {
        return autoAccuracy + "%";
    }

    public String getTeleopAccuracy() {
        return teleopAccuracy + "%";
    }

    public String getTotalAccuracy() {
        return totalAccuracy + "%";
    }
}