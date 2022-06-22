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
import org.texastorque.utils.Entry;
import org.texastorque.utils.NoticeUtils;
import org.texastorque.utils.LayoutUtils;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Scoring extends Page {
    private boolean checked = false;

    protected Pane panel = new Pane();

    private ValueDisplay nameDisplay = new ValueDisplay("Name", "Name");
    private ValueDisplay teamNumberDisplay = new ValueDisplay("Team Number", 0000);
    private ValueDisplay matchNumberDisplay = new ValueDisplay("Match Number", 0);

    private ToggleSingle allianceColor = new ToggleSingle("Alliance Color", "Blue", "", "Red", "");

    private ToggleSingle taxi = new ToggleSingle("Auto Taxi");
    private Numeric autoLower = new Numeric("Auto lower");
    private Numeric autoUpper = new Numeric("Auto upper");
    private Numeric autoMissed = new Numeric("Auto missed");

    private Numeric teleopLower = new Numeric("Teleop lower");
    private Numeric teleopUpper = new Numeric("Teleop upper");
    private Numeric teleopMissed = new Numeric("Teleop missed");
    private DisjointToggles climb = new DisjointToggles("Climb Level", "Low", "Mid", "High", "Transversal");
    private Numeric climbTime = new Numeric("Climb Time", 5);

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
                                nameDisplay.getPanel(),
                                teamNumberDisplay.getPanel(),
                                matchNumberDisplay.getPanel(),
                                allianceColor.getPanel(),
                                taxi.getPanel(),
                                autoLower.getPanel(),
                                autoUpper.getPanel(),
                                autoMissed.getPanel(),
                                comments.getPanel()),
                        LayoutUtils.insertPadding(
                                LayoutUtils.bundleIntoVBox(
                                        teleopLower.getPanel(),
                                        teleopUpper.getPanel(),
                                        teleopMissed.getPanel(),
                                        climb.getPanel(),
                                        climbTime.getPanel(),
                                        LayoutUtils.insertPadding(
                                                LayoutUtils.bundleIntoVBox(back),
                                                new LayoutUtils.Padding(20, 0, 0, 0)),
                                        LayoutUtils.insertPadding(
                                                LayoutUtils.bundleIntoVBox(submit),
                                                new LayoutUtils.Padding(20, 0, 0, 0))),
                                new LayoutUtils.Padding(0, 0, 0, 40))));
        panel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public Entry generateEntry() {
        if (!checked) {
            NoticeUtils.displayInfo("Double Check Entries", "Submitting is permanent, "
                    + "please double check your entries at least once before you submit.");
            checked = true;
            return null;
        }

        int teamNumber = DataUtils.toInteger(teamNumberDisplay.getValue());
        int matchNumber = DataUtils.toInteger(matchNumberDisplay.getValue());

        if (teamNumber == -1 || matchNumber == -1) {
            NoticeUtils.displayError("Error Reading Entry", "Invalid team number or match number");
            return null;
        }

        return new Entry(
                teamNumber,
                matchNumber,
                (allianceColor.getValue() ? "Red" : "Blue"),
                taxi.getValue(),
                autoLower.getValue(),
                autoUpper.getValue(),
                autoMissed.getValue(),
                0,
                teleopLower.getValue(),
                teleopUpper.getValue(),
                teleopMissed.getValue(),
                0,
                climb.getValue(),
                climbTime.getValue(),
                nameDisplay.getValue(),
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
