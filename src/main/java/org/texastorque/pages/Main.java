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

import java.util.ArrayList;

import org.texastorque.components.FadeButton;
import org.texastorque.modules.DisjointToggles;
import org.texastorque.modules.Numeric;
import org.texastorque.modules.ToggleSingle;
import org.texastorque.modules.TextBox;
import org.texastorque.utils.LayoutUtils;
import org.texastorque.utils.Report;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Page {
    protected Pane panel = new Pane();

    private Button newReport = new Button("New Report");
    private Button exportReports = new Button("Export Reports");
    private Button loadReports = new Button("Load Reports");
    private Button launchHub = new Button("Launch Hub");

    public Main() {
        Label title = new Label("Torque Scout");
        title.setFont(LayoutUtils.getStandardFont(36));
        title.setPrefSize(600, 50);
        title.setLayoutX(20);
        title.setLayoutY(20);

        Label description = new Label("Texas Torque FRC Scouting Utility");
        description.setFont(LayoutUtils.getStandardFont(14));
        description.setPrefSize(600, 30);
        description.setLayoutX(20);

        newReport.setPrefSize(300, 75);
        newReport.setLayoutX(20);
        newReport.setFont(LayoutUtils.getStandardFont(18));

        exportReports.setPrefSize(300, 75);
        exportReports.setLayoutX(20);
        exportReports.setFont(LayoutUtils.getStandardFont(18));

        loadReports.setPrefSize(300, 75);
        loadReports.setLayoutX(20);
        loadReports.setFont(LayoutUtils.getStandardFont(18));

        launchHub.setPrefSize(300, 75);
        launchHub.setLayoutX(20);
        launchHub.setFont(LayoutUtils.getStandardFont(18));

        //panel.setPrefSize(350, 1200);
        panel.getChildren().addAll(
                LayoutUtils.bundleIntoVBox(
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(title),
                                new LayoutUtils.Padding(20, 20, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(description),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(newReport),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(exportReports),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(loadReports),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(launchHub),
                                new LayoutUtils.Padding(20, 0, 0, 0))
                                
                ));
    }

    public Button getNewReport() {
        return newReport;
    }

    public Button getExportReports() {
        return exportReports;
    }

    public Button getLoadReports() {
        return loadReports;
    }

    public Button getLaunchHub() {
        return launchHub;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

}
