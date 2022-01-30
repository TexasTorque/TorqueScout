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
import org.texastorque.utils.Entry;
import org.texastorque.utils.LayoutUtils;
import org.texastorque.utils.Report;

import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Hub extends Page {
    protected Pane panel = new Pane();

    @Override
    public Pane getPanel() {
        return panel;
    }

    private TableView<Entry> table = new TableView<Entry>();
    final Label label = new Label("TorqueScout Hub");

    public Hub(ObservableList<Entry> entries) {
        label.setFont(new Font("Arial", 50));
        table.setEditable(true);
        TableColumn teamNumber = new TableColumn("Team Number");
        teamNumber.setMinWidth(400);

        teamNumber.setCellValueFactory(
                new PropertyValueFactory<Entry, String>("teamNumber"));
        TableColumn matchName = new TableColumn("Match Name");
        matchName.setMinWidth(400);
        matchName.setCellValueFactory(
                new PropertyValueFactory<Entry, String>("matchName"));

        TableColumn score = new TableColumn("Score");
        score.setMinWidth(360);
        score.setCellValueFactory(
                new PropertyValueFactory<Entry, String>("score"));

        table.setItems(entries);
        table.getColumns().addAll(teamNumber, matchName, score);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        panel.getChildren().add(vbox);

    }

}
