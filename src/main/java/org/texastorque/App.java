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

import org.texastorque.pages.Averages;
import org.texastorque.pages.Hub;
import org.texastorque.pages.Main;
import org.texastorque.pages.Scoring;
import org.texastorque.pages.Team;
import org.texastorque.utils.DataReader;
import org.texastorque.utils.DataUtils;
import org.texastorque.utils.DataWriter;
import org.texastorque.utils.Entry;
import org.texastorque.utils.NoticeUtils;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class App extends Application {
    public static String ADMIN_PASSWORD_HASH = "be363cd40ae7a86b2615cf6e1ac3641f9c2d25d357feb44846fba86c68a02b4c";

    Stage stage;

    DataWriter dataWriter = new DataWriter();
    DataReader dataReader = new DataReader();

    public static void postPaneOnStage(Stage stage, Pane pane) {
        stage.setScene(new Scene(pane));
        stage.show();
    }

    private void switchStageScene(Pane page) {
        if (stage.getScene() == null)
            stage.setScene(new Scene(page, 0, 0));
        else
            stage.getScene().setRoot(page);
        stage.setMaximized(true);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Torque Scout");
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
        window.getNewEntry().setOnAction(e -> {
            switchToScoring();
        });
        window.getExportEntries().setOnAction(e -> {
            dataWriter.export(stage);
        });
        window.getLoadEntries().setOnAction(e -> {
            dataReader.loadEntries(stage);
        });
        window.getLaunchHub().setOnAction(e -> {
            switchToAverages();
        });
        window.getClearDatabase().setOnAction(e -> {
            String password = NoticeUtils.promptPassword("Admin Password", "Please enter the admin password to clear the database.");
            if (DataUtils.sha256String(password).equals(ADMIN_PASSWORD_HASH)) {
                dataWriter.clearDatabase();
                NoticeUtils.displayInfo("Successfully cleared the database", "The database has been successfully cleared");
            } else
                NoticeUtils.displayInfo("Authentication failure", "Authentication failed. Please try again.");
        });
        
        switchStageScene(window.getPanel());
    }

    private void switchToScoring() {
        Scoring window = new Scoring();
        window.getSubmit().setOnAction(e -> {
            Entry entry = window.generateEntry();
            if (entry == null)
                return;

            if (!dataWriter.writeEntry(entry))
                return;

            NoticeUtils.displayInfo("Entry Success", "Entry successfully logged.");

            switchToMain();
        });
        window.getBack().setOnAction(e -> {
            if (window.wantsToQuit())
                switchToMain();
        });
        switchStageScene(window.getPanel());
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
    }

    private void switchToTeam(Integer team) {
        Team window = new Team(dataReader.getDataWrapper(), team);

        window.getBackButton().setOnAction(e -> {
            switchToAverages();
        });

        switchStageScene(window.getPanel());
    }

    private void setStageSize(int w, int h) {
        stage.setWidth(w);
        stage.setHeight(h);
    }

}
