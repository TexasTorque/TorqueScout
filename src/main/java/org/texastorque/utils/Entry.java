package org.texastorque.utils;

import java.net.Authenticator;
import java.time.LocalDateTime;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class Entry {
    public final static Integer[] climbScores = {0, 4, 6, 10, 15};

    public final Integer teamNumber;
    public final String matchName;
    public final Integer matchNumber;
    public final Integer climb;

    public final boolean taxi;

    public final Integer autoLower;
    public final Integer autoUpper;
    public final Integer autoMissed;
    public final Integer autoIntaken;

    public final Integer teleopLower;
    public final Integer teleopUpper;
    public final Integer teleopMissed;
    public final Integer teleopIntaken;

    public final String comment;

    public final LocalDateTime date;

    public final Integer autoScore;
    public final Integer teleopScore;
    public final Integer totalScore;

    public final Integer autoShootingAccuracy;
    public final Integer teleopShootingAccuracy;
    public final Integer totalShootingAccuracy;

    public Entry(Integer teamNumber,
            String matchName,
            Integer matchNumber,
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

        this.autoScore = (taxi ? 2 : 0) + autoLower * 2 + autoUpper * 4; 
        this.teleopScore = climbScores[climb] + teleopLower + teleopUpper * 2;
        this.totalScore = this.autoScore + this.teleopScore;

        this.autoShootingAccuracy = Math.min(100, Math.max(0, 
                (this.autoLower + this.autoUpper)
                / (this.autoLower + this.autoUpper + this.autoMissed) 
                * 100));
                
        this.teleopShootingAccuracy = Math.min(100, Math.max(0, 
                (this.teleopLower + this.teleopUpper)
                / (this.teleopLower + this.teleopUpper + this.teleopMissed) 
                * 100));

        this.totalShootingAccuracy = (this.autoShootingAccuracy + this.teleopShootingAccuracy) / 2;
    }

    public Integer getTeamNumber() {
        return teamNumber;
    }

    public String getMatchName() {
        return matchName;
    }

    public Integer getMatchNumber() {
        return matchNumber;
    }

    public String getTaxi() {
        return taxi ? "Yes" : "No";
    }

    public Integer getAutoLower() {
        return autoLower;
    }

    public Integer getautoUpper() {
        return autoUpper;
    }

    public Integer getautoMissed() {
        return autoMissed;
    }

    public Integer getautoIntaken() {
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

    public Integer getClimb() {
        return teamNumber;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getDate() {
        return date;
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

    public Integer getAutoShootingAccuracy() {
        return autoShootingAccuracy;
    }

    public Integer getTeleopShootingAccuracy() {
        return teleopShootingAccuracy;
    }

    public Integer getTotalShootingAccuracy() {
        return totalShootingAccuracy;
    }

    public static TableColumn<Entry, Integer> createTeamNumberColumn() {
        TableColumn<Entry, Integer> teamNumberCol = new TableColumn<>("Team Number");
        teamNumberCol.setCellValueFactory(new PropertyValueFactory<>("teamNumber"));
        return teamNumberCol;
    }

    public static TableColumn<Entry, Integer> createMatchNumberColumn() {
        TableColumn<Entry, Integer> teamNumberCol = new TableColumn<>("Match Number");
        teamNumberCol.setCellValueFactory(new PropertyValueFactory<>("matchNumber"));
        return teamNumberCol;
    }
}