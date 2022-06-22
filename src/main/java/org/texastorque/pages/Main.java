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

import org.texastorque.utils.LayoutUtils;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Main extends Page {
    protected Pane panel = new Pane();

    private Button newEntry = new Button("New Entry");
    private Button exportEntries = new Button("Export Entries");
    private Button loadEntries = new Button("Load Entries");
    private Button launchHub = new Button("Launch Hub");
    private Button clearDatabase = new Button("Clear Data");

    public Main() {
        Label title = new Label("Torque Scout");
        title.setFont(LayoutUtils.getStandardFont(36));
        title.setPrefSize(600, 50);
        title.setLayoutX(20);
        title.setLayoutY(20);
        title.setTextFill(Color.WHITE);

        Label description = new Label("Texas Torque FRC Scouting Utility");
        description.setFont(LayoutUtils.getStandardFont(14));
        description.setPrefSize(600, 30);
        description.setLayoutX(20);
        description.setTextFill(Color.WHITE);

        newEntry.setPrefSize(300, 75);
        newEntry.setLayoutX(20);
        newEntry.setFont(LayoutUtils.getStandardFont(18));

        exportEntries.setPrefSize(300, 75);
        exportEntries.setLayoutX(20);
        exportEntries.setFont(LayoutUtils.getStandardFont(18));

        loadEntries.setPrefSize(300, 75);
        loadEntries.setLayoutX(20);
        loadEntries.setFont(LayoutUtils.getStandardFont(18));

        launchHub.setPrefSize(300, 75);
        launchHub.setLayoutX(20);
        launchHub.setFont(LayoutUtils.getStandardFont(18));

        clearDatabase.setPrefSize(300, 75);
        clearDatabase.setLayoutX(20);
        clearDatabase.setFont(LayoutUtils.getStandardFont(18));

        // panel.setPrefSize(350, 1200);
        panel.getChildren().addAll(
                LayoutUtils.bundleIntoVBox(
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(title),
                                new LayoutUtils.Padding(20, 20, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(description),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(newEntry),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(exportEntries),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(loadEntries),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(launchHub),
                                new LayoutUtils.Padding(20, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(clearDatabase),
                                new LayoutUtils.Padding(20, 0, 0, 0))));

        panel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        newEntry.setTextFill(Color.WHITE);
        newEntry.setStyle("-fx-text-fill: black");

        exportEntries.setTextFill(Color.WHITE);
        exportEntries.setStyle("-fx-text-fill: black");

        loadEntries.setTextFill(Color.WHITE);
        loadEntries.setStyle("-fx-text-fill: black");

        launchHub.setTextFill(Color.WHITE);
        launchHub.setStyle("-fx-text-fill: black");

        clearDatabase.setTextFill(Color.WHITE);
        clearDatabase.setStyle("-fx-text-fill: black");
    }

    public Button getNewEntry() {
        return newEntry;
    }

    public Button getExportEntries() {
        return exportEntries;
    }

    public Button getLoadEntries() {
        return loadEntries;
    }

    public Button getLaunchHub() {
        return launchHub;
    }

    public Button getClearDatabase() {
        return clearDatabase;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

}
