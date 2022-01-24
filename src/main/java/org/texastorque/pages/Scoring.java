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

import org.texastorque.components.FadeButton;
import org.texastorque.modules.DisjointToggles;
import org.texastorque.modules.Numeric;
import org.texastorque.modules.ToggleSingle;
import org.texastorque.modules.ValueDisplay;
import org.texastorque.utils.LayoutUtils;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class Scoring extends Page {
    protected Pane panel = new Pane();

    private ValueDisplay teamNumberDisplay = new ValueDisplay("Team Number", 0000);
    private ValueDisplay matchNameDisplay = new ValueDisplay("Match Name", "quals-0");
    private ValueDisplay matchNumberDisplay = new ValueDisplay("Match Number", 0);

 
    private ToggleSingle taxi = new ToggleSingle("Auto Taxi");
    private Numeric autoLower = new Numeric("Auto lower shots");
    private Numeric autoUpper = new Numeric("Auto upper shots");
    private Numeric autoMissed = new Numeric("Auto missed shots");
    private Numeric autoIntaken = new Numeric("Auto intaken balls");

    private Numeric teleopLower = new Numeric("Teleop lower shots");
    private Numeric teleopUpper = new Numeric("Teleop upper shots");
    private Numeric teleopMissed = new Numeric("Teleop missed shots");
    private Numeric teleopIntaken = new Numeric("Teleop intaken balls");
    private DisjointToggles climb = new DisjointToggles("Climb level", "Low", "Mid", "High", "Transversal");

    private Button submit = new Button("Submit");

    public Scoring() {
        submit.setPrefSize(300, 75);

        panel.setPrefSize(1200, 1200);
        panel.getChildren().addAll(
            LayoutUtils.bundleIntoHBox(
                LayoutUtils.bundleIntoVBox(
                    teamNumberDisplay.getPanel(),
                    matchNameDisplay.getPanel(),
                    matchNumberDisplay.getPanel(),
                    taxi.getPanel(),
                    autoLower.getPanel(),
                    autoUpper.getPanel(),
                    autoMissed.getPanel(),
                    autoIntaken.getPanel()
                ),
                LayoutUtils.bundleIntoVBox(
                    teleopLower.getPanel(),
                    teleopUpper.getPanel(),
                    teleopMissed.getPanel(),
                    teleopIntaken.getPanel(),
                    climb.getPanel(),
                    submit
                )
            )
        );
    }

    public Button getSubmit() {
        return submit;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }
    
}
