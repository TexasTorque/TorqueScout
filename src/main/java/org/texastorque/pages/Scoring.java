/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.pages;

import org.texastorque.modules.DisjointToggles;
import org.texastorque.modules.Numeric;
import org.texastorque.modules.TextBox;
import org.texastorque.modules.ToggleSingle;
import org.texastorque.modules.ValueDisplay;
import org.texastorque.utils.DataUtils;
import org.texastorque.utils.NoticeUtils;
import org.texastorque.utils.LayoutUtils;
import org.texastorque.utils.Report;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;

public class Scoring extends Page {
    private boolean checked = false;

    protected Pane panel = new Pane();

    private ValueDisplay teamNumberDisplay = new ValueDisplay("Team Number", 0000);
    private ValueDisplay matchNameDisplay = new ValueDisplay("Match Name", "quals-0");
    private ValueDisplay matchNumberDisplay = new ValueDisplay("Match Number", 0);

    private ToggleSingle allianceColor = new ToggleSingle("Alliance Color", "blue", "", "red", "");

    private ToggleSingle taxi = new ToggleSingle("Auto Taxi");
    private Numeric autoLower = new Numeric("Auto lower");
    private Numeric autoUpper = new Numeric("Auto upper");
    private Numeric autoMissed = new Numeric("Auto missed");
    private Numeric autoIntaken = new Numeric("Auto intaken");

    private Numeric teleopLower = new Numeric("Teleop lower");
    private Numeric teleopUpper = new Numeric("Teleop upper");
    private Numeric teleopMissed = new Numeric("Teleop missed");
    private Numeric teleopIntaken = new Numeric("Teleop intaken");
    private DisjointToggles climb = new DisjointToggles("Climb Level", "Low", "Mid", "High", "Traversal");

    private TextBox comments = new TextBox("Comments", "");

    private Button submit = new Button("Submit");
    private Button back = new Button("Back");

    public Scoring() {
        submit.setPrefSize(200, 50);
        submit.setLayoutX(20);
        submit.setLayoutY(15);

        back.setPrefSize(200, 50);
        back.setLayoutX(20);
        back.setLayoutY(100);

        submit.setFont(LayoutUtils.getStandardFont(18));
        back.setFont(LayoutUtils.getStandardFont(18));
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-text-fill: black");
        submit.setTextFill(Color.WHITE);
        submit.setStyle("-fx-text-fill: black");

        panel.setPrefSize(1200, 1200);
        panel.getChildren().addAll(
                LayoutUtils.bundleIntoHBox(
                        LayoutUtils.bundleIntoVBox(
                                teamNumberDisplay.getPanel(),
                                matchNameDisplay.getPanel(),
                                matchNumberDisplay.getPanel(),
                                allianceColor.getPanel(),
                                taxi.getPanel(),
                                autoLower.getPanel(),
                                autoUpper.getPanel(),
                                autoMissed.getPanel(),
                                autoIntaken.getPanel(),
                                comments.getPanel()),
                        LayoutUtils.insertPadding(
                                LayoutUtils.bundleIntoVBox(
                                        teleopLower.getPanel(),
                                        teleopUpper.getPanel(),
                                        teleopMissed.getPanel(),
                                        teleopIntaken.getPanel(),
                                        climb.getPanel(),
                                        LayoutUtils.insertPadding(
                                                LayoutUtils.bundleIntoVBox(back),
                                                new LayoutUtils.Padding(20, 0, 0, 0)),
                                        LayoutUtils.insertPadding(
                                                LayoutUtils.bundleIntoVBox(submit),
                                                new LayoutUtils.Padding(20, 0, 0, 0))),
                                new LayoutUtils.Padding(0, 0, 0, 40))));
        panel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        panel.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.COMMA) {
                autoLower.increment();
            } else if (e.getCode() == KeyCode.PERIOD) {
                autoUpper.increment();
            } else if (e.getCode() == KeyCode.SLASH) {
                autoMissed.increment();
            } else if (e.getCode() == KeyCode.OPEN_BRACKET) {
                teleopLower.increment();
            } else if (e.getCode() == KeyCode.CLOSE_BRACKET) {
                teleopUpper.increment();
            } else if (e.getCode() == KeyCode.BACK_SLASH) {
                teleopMissed.increment();
            } else if (e.getCode() == KeyCode.SEMICOLON) {
                autoIntaken.increment();
            } else if (e.getCode() == KeyCode.QUOTE) {
                teleopIntaken.increment();
            }
        });
    }

    public Report generateReport() {
        if (!checked) {
            NoticeUtils.displayInfo("Double Check Entries", "Submitting is permanent, "
                    + "please double check your entries at least once before you submit.");
            checked = true;
            return null;
        }

        int teamNumber = DataUtils.toInteger(teamNumberDisplay.getValue());
        int matchNumber = DataUtils.toInteger(matchNumberDisplay.getValue());

        if (teamNumber < 0 || matchNumber < 0) {
            NoticeUtils.displayError("Error Reading Entry", "Invalid team number or match number");
            return null;
        }

        return new Report(
                teamNumber,
                matchNameDisplay.getValue(),
                matchNumber,
                allianceColor.getValue() ? "red" : "blue",
                taxi.getValue(),
                autoLower.getValue(),
                autoUpper.getValue(),
                autoMissed.getValue(),
                autoIntaken.getValue(),
                teleopLower.getValue(),
                teleopUpper.getValue(),
                teleopMissed.getValue(),
                teleopIntaken.getValue(),
                climb.getValue(),
                comments.getValue());
    }

    public boolean wantsToQuit() {
        return NoticeUtils.displayConfirmation("Are you sure you want to leave?", "You will lose all unsaved data.");
    }

    public Button getSubmit() {
        return submit;
    }

    public Button getBack() {
        return back;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

}
