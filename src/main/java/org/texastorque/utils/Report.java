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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {

    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd@HH:mm:ss");

    public static final String header = "dateTime,teamNumber,matchName,matchNumber,"
            + "allianceColor,taxi,autoLower,autoUpper,autoMissed,autoIntaken,"
            + "teleopLower,teleopUpper,teleopMissed,teleopIntaken,climb,comment";

    public static enum Climb {
        NONE,
        LOW,
        MEDIUM,
        HIGH,
        TRAVERSAL
    }

    private final int teamNumber;
    private final String matchName;
    private final int matchNumber;

    private String allianceColor;

    private final boolean taxi;

    private final int autoLower;
    private final int autoUpper;
    private final int autoMissed;
    private final int autoIntaken;

    private final int teleopLower;
    private final int teleopUpper;
    private final int teleopMissed;
    private final int teleopIntaken;

    private final int climb;

    private final String comment;

    private final LocalDateTime date;

    public Report(
            int teamNumber,
            String matchName,
            int matchNumber,
            String allainceColor,
            boolean taxi,
            int autoLower,
            int autoUpper,
            int autoMissed,
            int autoIntaken,
            int teleopLower,
            int teleopUpper,
            int teleopMissed,
            int teleopIntaken,
            int climb,
            String comment) {
        this.teamNumber = teamNumber;
        this.matchName = matchName;
        this.matchNumber = matchNumber;
        this.allianceColor = allainceColor;
        this.taxi = taxi;
        this.autoLower = autoLower;
        this.autoUpper = autoUpper;
        this.autoMissed = autoMissed;
        this.autoIntaken = autoIntaken;
        this.teleopLower = teleopLower;
        this.teleopUpper = teleopUpper;
        this.teleopMissed = teleopMissed;
        this.teleopIntaken = teleopIntaken;
        this.climb = climb;
        this.comment = comment;
        this.date = LocalDateTime.now();
    }

    public String getDateTimeString() {
        return date.format(dateTimeFormatter);
    }

    public String titleString() {
        return String.format("%s: %d in %s(%d)",
                // new SimpleDateFormat("yyyy-MM-dd").format(date),
                date.format(dateTimeFormatter),
                teamNumber, matchName, matchNumber);
    }

    public String toCSV() {
        return String.format("%s,%d,%s,%d,%s,%b,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s",
                getDateTimeString(), teamNumber, "unused", matchNumber, allianceColor, taxi,
                autoLower, autoUpper, autoMissed, autoIntaken,
                teleopLower, teleopUpper, teleopMissed, teleopIntaken,
                climb, comment.replace(",", "，"));
    }
}
