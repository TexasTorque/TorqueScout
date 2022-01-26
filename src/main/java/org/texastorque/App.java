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

import java.util.ArrayList;

import org.texastorque.pages.Main;
import org.texastorque.pages.Scoring;
import org.texastorque.utils.DataWriter;
import org.texastorque.utils.Report;

import javafx.application.Application;
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

    private void switchStageScene(Pane page) {
        if (stage.getScene() == null) {
            stage.setScene(new Scene(page, 1200, 1200));
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
        switchStageScene(window.getPanel());
    }

    private void switchToScoring() {
        Scoring window = new Scoring();
        window.getSubmit().setOnAction(e -> {
            Report report = window.generateReport();
            if (report == null) return;

            //report.toCSV();

            if (!dataWriter.writeReport(report)) return;

            switchToMain();
        });
        switchStageScene(window.getPanel());
    }

}
