/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.texastorque.pages.Averages;
import org.texastorque.pages.Hub;
import org.texastorque.pages.Main;
import org.texastorque.pages.Scoring;
import org.texastorque.pages.Team;
import org.texastorque.utils.DataReader;
import org.texastorque.utils.DataWriter;
import org.texastorque.utils.Entry;
import org.texastorque.utils.NoticeUtils;
import org.texastorque.utils.Report;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class App extends Application {
    Stage stage;

    DataWriter dataWriter = new DataWriter();
    DataReader dataReader = new DataReader();

    public static void postPaneOnStage(Stage stage, Pane pane) {
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public static void postPaneOnStage(Stage stage, Pane pane, int w, int h) {
        stage.setScene(new Scene(pane));
        stage.show();
        stage.setWidth(w);
        stage.setHeight(h);
    }

    private void switchStageScene(Pane page) {
        if (stage.getScene() == null)
            stage.setScene(new Scene(page, 0, 0));
        else
            stage.getScene().setRoot(page);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TorqueScout");
        stage.setScene(null);
        this.stage = stage;
        stage.show();
        switchToMain();
    }

    public static void startApp(String[] args) {
        launch(args);
    }

    private void switchToMain() {
        Main window = new Main();
        window.getNewReport().setOnAction(e -> {
            switchToScoring();
        });
        window.getExportReports().setOnAction(e -> {
            dataWriter.export(stage);
        });
        window.getLoadReports().setOnAction(e -> {
            dataReader.loadEntries(stage);
        });
        window.getLaunchHub().setOnAction(e -> {
            switchToAverages();
        });
        switchStageScene(window.getPanel());
        setStageSize(350, 600);
    }

    private void switchToScoring() {
        Scoring window = new Scoring();
        window.getSubmit().setOnAction(e -> {
            Report report = window.generateReport();
            if (report == null)
                return;

            if (!dataWriter.writeReport(report))
                return;

            NoticeUtils.displayInfo("Report Success", "Report successfully logged.");

            switchToMain();
        });
        window.getBack().setOnAction(e -> {
            if (window.wantsToQuit())
                switchToMain();
        });
        switchStageScene(window.getPanel());
        setStageSize(800, 600);
    }

    private void switchToHub() {
        if (dataReader.getEntries() == null)
            dataReader.loadEntries(stage);

        Hub window = new Hub(dataReader.getDataWrapper());

        window.getBackButton().setOnAction(e -> {
            switchToMain();
        });
        window.getAveragesButton().setOnAction(e -> {
            switchToAverages();
        });

        switchStageScene(window.getPanel());
        // setStageSize(1200, 1200);
        stage.setMaximized(true);
    }

    private void switchToAverages() {
        if (dataReader.getEntries() == null)
            dataReader.loadEntries(stage);

        Averages window = new Averages(dataReader.getDataWrapper(), new Callback<Integer, Void>() {
            @Override
            public Void call(Integer team) {
                switchToTeam(team);
                return null;
            }
        });

        window.getBackButton().setOnAction(e -> {
            switchToMain();
        });
        window.getHubButton().setOnAction(e -> {
            switchToHub();
        });

        switchStageScene(window.getPanel());
        // setStageSize(1200, 1200);
        stage.setMaximized(true);
    }

    private void switchToTeam(Integer team) {
        Team window = new Team(dataReader.getDataWrapper(), team);

        window.getBackButton().setOnAction(e -> {
            switchToAverages();
        });

        switchStageScene(window.getPanel());
        // setStageSize(1200, 1200);
        stage.setMaximized(true);
    }

    private void setStageSize(int w, int h) {
        stage.setWidth(w);
        stage.setHeight(h);
    }

}
