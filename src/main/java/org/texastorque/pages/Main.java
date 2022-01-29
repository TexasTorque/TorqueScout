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
    private Button launchHub = new Button("Launch Hub");

    ArrayList<Report> reports = null;

    // private TableView table = new TableView();

    // public Pane makeLabelsPane() {
    // Pane p = new VBox();
    // p.setPrefSize(600, 600);
    // if (reports == null) { System.out.println("reports is null");
    // return p;}
    // for (Report report : reports) {
    // Label l = new Label(report.titleString());
    // l.setFont(new Font(16));
    // p.getChildren().add(l);
    // }
    // return p;
    // }

    // public Pane makeTablesPane() {
    // table.setEditable(true);

    // TableColumn dateTimeCol = new TableColumn("Date&Time");
    // TableColumn teamNumberCol = new TableColumn("Team Number");
    // TableColumn matchNameCol = new TableColumn("Match Name");
    // TableColumn matchNumberCol = new TableColumn("Match Number");

    // table.getColumns().addAll(dateTimeCol, teamNumberCol, matchNameCol,
    // matchNumberCol);

    // final VBox vbox = new VBox();
    // vbox.setSpacing(5);
    // vbox.setPrefSize(600, 600);
    // vbox.getChildren().addAll(table);
    // return vbox;
    // }

    public Main(ArrayList<Report> reports) {
        Label title = new Label("TorqueScout Client");
        title.setFont(new Font(36));
        title.setPrefSize(600, 50);
        title.setLayoutX(20);
        title.setLayoutY(20);

        Label description = new Label(
                "TorqueScout Client is a program to collect match\n" +
                        "information for scouting analysis. The client\n" +
                        "provides a dashboard to collect match data, and\n" +
                        "exports a single CSV file, designed to be used in\n" +
                        "TorqueScout Hub, which is used to perform\n" +
                        "data analysis and inform scouting decisions.");
        description.setFont(new Font(14));
        description.setPrefSize(600, 150);
        description.setLayoutX(20);

        newReport.setPrefSize(300, 75);
        newReport.setLayoutX(20);
        newReport.setLayoutY(40);

        exportReports.setPrefSize(300, 75);
        exportReports.setLayoutX(20);
        exportReports.setLayoutY(60);

        launchHub.setPrefSize(300, 75);
        launchHub.setLayoutX(20);
        launchHub.setLayoutY(60);

        this.reports = reports;

        //panel.setPrefSize(350, 1200);
        panel.getChildren().addAll(
                LayoutUtils.bundleIntoVBox(
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(title),
                                new LayoutUtils.Padding(0, 0, 0, 40)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(description),
                                new LayoutUtils.Padding(0, 0, 0, 40)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(newReport),
                                new LayoutUtils.Padding(0, 0, 0, 0)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(exportReports),
                                new LayoutUtils.Padding(20, 20, 00, 20)),
                        LayoutUtils.insertPadding(
                                LayoutUtils.wrapInPane(launchHub),
                                new LayoutUtils.Padding(00, 20, 00, 20))
                                
                ));
    }

    public Button getNewReport() {
        return newReport;
    }

    public Button getExportReports() {
        return exportReports;
    }

    public Button getLaunchHub() {
        return launchHub;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

}
