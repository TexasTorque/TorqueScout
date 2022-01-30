package org.texastorque.utils;

import java.net.Authenticator;
import java.time.LocalDateTime;

public class Entry {

    public int calcAutoScore(boolean taxi, int autoLower, int autoUpper) {
        int score = 0;
        score += (taxi == true) ? 2 : 0;
        score += autoLower * 2;
        score += autoUpper * 4;
        return score;
    }

    public int calcTeleopScore(int teleopLower, int teleopUpper, int climb) {
        int score = 0;
        score += teleopLower * 1;
        score += teleopUpper * 2;
        switch (climb) {
            case 0:
                score += 0;
                break;
            case 1:
                score += 4;
                break;
            case 2:
                score += 6;
                break;
            case 3:
                score += 10;
                break;
            case 4:
                score += 15;
                break;
        }
        return score;
    }

    public final int teamNumber;
    public final String matchName;
    public final int matchNumber;
    public final int climb;

    public final boolean taxi;

    public final int autoLower;
    public final int autoUpper;
    public final int autoMissed;
    public final int autoIntaken;

    public final int teleopLower;
    public final int teleopUpper;
    public final int teleopMissed;
    public final int teleopIntaken;

    public final String comment;

    public final LocalDateTime date;

    public final int autoScore;
    public final int teleopScore;
    public final int totalScore;
    public final double autoShootingAccuracy;
    public final double teleopShootingAccuracy;
    public final double totalShootingAccuracy;

    public Entry(int teamNumber,
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
            int climb,
            String comment,
            LocalDateTime date) {
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
        this.comment = comment;
        this.date = date;
        this.autoScore = calcAutoScore(taxi, autoLower, autoUpper);
        this.teleopScore = calcTeleopScore(teleopLower, teleopUpper, climb);
        this.totalScore = this.autoScore + this.teleopScore;
        this.autoShootingAccuracy = (this.autoLower + this.autoUpper + 0.)
                / (this.autoLower + this.autoUpper + this.autoMissed);
        this.teleopShootingAccuracy = (this.teleopLower + this.teleopUpper + 0.)
                / (this.teleopLower + this.teleopUpper + this.teleopMissed);
        this.totalShootingAccuracy = (this.autoShootingAccuracy + this.teleopShootingAccuracy) / 2.;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getMatchName() {
        return matchName;
    }

    public boolean getTaxi() {
        return taxi;
    }

    public int getAutoLower() {
        return autoLower;
    }

    public int getautoUpper() {
        return autoUpper;
    }

    public int getautoMissed() {
        return autoMissed;
    }

    public int getautoIntaken() {
        return autoIntaken;
    }

    public int getTeleopLower() {
        return teleopLower;
    }

    public int getTeleopUpper() {
        return teleopUpper;
    }

    public int getTeleopMissed() {
        return teleopMissed;
    }

    public int getTeleopIntaken() {
        return teleopIntaken;
    }

    public int getClimb() {
        return teamNumber;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getAutoScore() {
        return autoScore;
    }

    public int getTeleopScore() {
        return teleopScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public double getAutoShootingAccuracy() {
        return autoShootingAccuracy;
    }

    public double getTeleopShootingAccuracy() {
        return teleopShootingAccuracy;
    }

    public double getTotalShootingAccuracy() {
        return totalShootingAccuracy;
    }
}