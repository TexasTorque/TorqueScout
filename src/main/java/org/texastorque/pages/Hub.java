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
        table.setEditable(false);
        

        //table.setItems(entries);
        table.getItems().addAll(entries);
        table.getColumns().addAll(
                Entry.createColumn("teamNumber", "Team #"),
                Entry.createColumn("matchNumber", "Match #"),
                Entry.createColumn("taxi"),

                Entry.createColumn("autoLower", "A Lower"),
                Entry.createColumn("autoUpper", "A Upper"),
                Entry.createColumn("autoMissed", "A Missed"),
                Entry.createColumn("autoIntaken", "A Intaken"),

                Entry.createColumn("autoScore", "A Score"),
                Entry.createColumn("autoAccuracy", "A Accuracy"),

                Entry.createColumn("teleopLower", "T Lower"),
                Entry.createColumn("teleopUpper", "T Upper"),
                Entry.createColumn("teleopMissed", "T Missed"),
                Entry.createColumn("teleopIntaken", "T Intaken"),

                Entry.createColumn("teleopScore", "T Score"),
                Entry.createColumn("teleopAccuracy", "T Accuracy"),

                Entry.createColumn("climb")
        );

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        panel.getChildren().add(vbox);

    }

}
