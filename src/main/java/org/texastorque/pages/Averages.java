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
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import org.texastorque.components.FadeButton;
import org.texastorque.modules.DisjointToggles;
import org.texastorque.modules.Numeric;
import org.texastorque.modules.ToggleSingle;
import org.texastorque.modules.TextBox;
import org.texastorque.utils.DataWrapper;
import org.texastorque.utils.Entry;
import org.texastorque.utils.LayoutUtils;
import org.texastorque.utils.Report;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Averages extends Page {
    protected Pane panel = new Pane();

    @Override
    public Pane getPanel() {
        return panel;
    }

    private TableView<Entry> table = new TableView<Entry>();
    final Label label = new Label("Torque Scout");
    private Button back = new Button("Back to home");
    private Button hub = new Button("View all entries");

    public Averages(DataWrapper entries, Callback<Integer, Void> callback) {
        label.setTextFill(Color.WHITE);
        label.setFont(LayoutUtils.getStandardFont(44));
        table.setEditable(false);

        back.setFont(LayoutUtils.getStandardFont(24));
        hub.setFont(LayoutUtils.getStandardFont(24));
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-text-fill: black");
        hub.setTextFill(Color.WHITE);
        hub.setStyle("-fx-text-fill: black");

        ObservableList<Entry> averages = entries.getAverages();
        for (Entry average : averages)
            average.getTeamButton().setOnAction(e -> {
                callback.call(average.getTeamNumber());
            });

        table.getItems().addAll(entries.getAverages());

        TableColumn<Entry, String> taxiColumn = (TableColumn<Entry, String>) Entry.createColumn("taxi");
        taxiColumn.setComparator((String v1, String v2) -> {
            return v1.length() >= v2.length() ? 1 : -1;
        });

        TableColumn<Entry, String> autoAccuracyColumn = (TableColumn<Entry, String>) Entry.createColumn("autoAccuracy",
                "A Accuracy");

        autoAccuracyColumn.setComparator((String v1, String v2) -> {
            try {
                return Integer.parseInt(v1.replace("%", "")) >= Integer.parseInt(v2.replace("%", "")) ? 1 : -1;
            } catch (Exception e) {
                return 0;
            }
        });

        TableColumn<Entry, String> teleopAccuracyColumn = (TableColumn<Entry, String>) Entry
                .createColumn("teleopAccuracy", "T Accuracy");

        teleopAccuracyColumn.setComparator((String v1, String v2) -> {
            try {
                return Integer.parseInt(v1.replace("%", "")) >= Integer.parseInt(v2.replace("%", "")) ? 1 : -1;
            } catch (Exception e) {
                return 0;
            }
        });

        TableColumn<Entry, String> climbColumn = (TableColumn<Entry, String>) Entry.createColumn("climb");

        climbColumn.setComparator((String v1, String v2) -> {
            return Entry.valueOfClimb(v1) >= Entry.valueOfClimb(v2) ? 1 : -1;
        });

        TableColumn<Entry, Double> climbNumbersColumn = (TableColumn<Entry, Double>) Entry
                .createColumn("avgClimbPoints", "Climb #");

        TableColumn<Entry, Button> teamButtons = (TableColumn<Entry, Button>) Entry.createColumn("teamButton");

        TableColumn<Entry, Integer> score = (TableColumn<Entry, Integer>) Entry.createColumn("totalScore");

        table.getColumns().addAll(
                Entry.createColumn("teamNumber", "Team #"),
                // Entry.createColumn("matchNumber", "Match #"),
                taxiColumn,

                // Entry.createColumn("allianceColor", "Color"),

                Entry.createColumn("autoLower", "A Lower"),
                Entry.createColumn("autoUpper", "A Upper"),
                Entry.createColumn("autoMissed", "A Missed"),
                Entry.createColumn("autoIntaken", "A Intaken"),

                Entry.createColumn("autoScore", "A Score"),
                autoAccuracyColumn,

                Entry.createColumn("teleopLower", "T Lower"),
                Entry.createColumn("teleopUpper", "T Upper"),
                Entry.createColumn("teleopMissed", "T Missed"),
                Entry.createColumn("teleopIntaken", "T Intaken"),

                Entry.createColumn("teleopScore", "T Score"),
                teleopAccuracyColumn,

                // climbColumn,
                climbNumbersColumn,
                score,
                // Entry.createColumn("comment"),
                teamButtons);

        final VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table,
                LayoutUtils.bundleIntoHBox(back, hub));

        TableColumn<Entry, ?> scoreColumn = score;
        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().add(scoreColumn);

        panel.getChildren().add(vbox);
        panel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public Button getBackButton() {
        return back;
    }

    public Button getHubButton() {
        return hub;
    }

}
