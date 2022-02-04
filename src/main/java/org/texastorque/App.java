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

public class App extends Application {
    Stage stage;

    ArrayList<Report> reports = new ArrayList<Report>();
    DataWriter dataWriter = new DataWriter();
    DataReader dataReader = new DataReader();

    private void switchStageScene(Pane page) {
        if (stage.getScene() == null) {
            stage.setScene(new Scene(page, 0, 0));
        } else {
            stage.getScene().setRoot(page);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("TorqueScout");
        stage.setScene(null);
        this.stage = stage;
        stage.show();
        switchToMain();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void switchToMain() {
        Main window = new Main(reports);
        window.getNewReport().setOnAction(e -> {
            switchToScoring();
        });
        window.getExportReports().setOnAction(e -> {
            dataWriter.export(stage);
        });
        window.getLaunchHub().setOnAction(e -> {
            //switchToHub();
            switchToAverages();
        });
        switchStageScene(window.getPanel());
        setStageSize(350, 700);
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
        setStageSize(800, 750);
    }

    private void switchToHub() {
        if (!dataReader.loadEntries(stage)) return;

        Hub window = new Hub(dataReader.getEntries());
        window.getBackButton().setOnAction(e -> {
            switchToMain();
        });

        switchStageScene(window.getPanel());
        //setStageSize(1200, 1200);
        stage.setMaximized(true);
    }

    private void switchToAverages() {
        if (!dataReader.loadEntries(stage)) return;

        Averages window = new Averages(dataReader.getDataWrapper());
        window.getBackButton().setOnAction(e -> {
            switchToMain();
        });

        switchStageScene(window.getPanel());
        //setStageSize(1200, 1200);
        stage.setMaximized(true);
    }

    private void setStageSize(int w, int h) {
        stage.setWidth(w);
        stage.setHeight(h);
    }

}
