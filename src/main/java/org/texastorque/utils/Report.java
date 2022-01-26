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

    public static final String header = "dateTime,teamNumber,matchName,"
            + "taxi,autoLower,autoUpper,autoMissed,autoIntaken,"
            + "teleopLower,teleopUpper,teleopMissed,teleopIntaken,climb,";


    public static enum Climb {
        NONE,
        LOW,
        MEDIUM,
        HIGH,
        TRANSVERSAL
    }

    public final int teamNumber;
    public final String matchName;
    public final int matchNumber;

    public final boolean taxi;

    public final int autoLower;
    public final int autoUpper;
    public final int autoMissed;
    public final int autoIntaken;

    public final int teleopLower;
    public final int teleopUpper;
    public final int teleopMissed;
    public final int teleopIntaken;

    public final int climb;

    public final LocalDateTime date;

    public Report(
            int teamNumber,
            String matchName,
            int matchNumber,
            boolean taxi,
            int autoLower,
            int autoUpper,
            int autoMissed,
            int autoIntaken,
            int teleopLower,
            int teleopUpper,
            int teleopMissed,
            int teleopIntaken,
            int climb
    ) {
        this.teamNumber = teamNumber;
        this.matchName = matchName;
        this.matchNumber = matchNumber;
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
        this.date = LocalDateTime.now();
    }

    public String getDateTimeString() {
        return getDateTimeString("yyyy-MM-dd HH:mm:ss");
    }

    public String getDateTimeString(String formatString) {
        return date.format(DateTimeFormatter.ofPattern(formatString));
    }

    public String titleString() {
        return String.format("%s: %d in %s(%d)", 
                //new SimpleDateFormat("yyyy-MM-dd").format(date), 
                date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                teamNumber, matchName, matchNumber);
    }

    public String toCSV() {
        return String.format("%s,%d,%s,%d,%b,%d,%d,%d,%d,%d,%d,%d,%d,%d,",
                getDateTimeString(), teamNumber, matchName, matchNumber, taxi,
                autoLower, autoUpper, autoMissed, autoIntaken,
                teleopLower, teleopUpper, teleopMissed, teleopIntaken,
                climb);
    }
}
