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

public class Main extends Page {
    protected Pane panel = new Pane();

    private Button newReport = new Button("New Report");

    public Main() {
        newReport.setPrefSize(300, 75);

        panel.setPrefSize(1200, 1200);
        panel.getChildren().addAll(
            LayoutUtils.bundleIntoHBox(
                LayoutUtils.bundleIntoVBox(
                    newReport   
                )
            )
        );
    }

    public Button getNewReport() {
        return newReport;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }
    
}
