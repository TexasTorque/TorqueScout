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

import org.texastorque.utils.DataWrapper;
import org.texastorque.utils.Entry;
import org.texastorque.utils.LayoutUtils;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class Team extends Page {
    protected Pane panel = new Pane();

    @Override
    public Pane getPanel() {
        return panel;
    }

    private TableView<Entry> table = new TableView<Entry>();
    final Label label = new Label("Torque Scout");
    private Button back = new Button("Back");
    private Button export = new Button("Export");

    public Team(DataWrapper entries, int team) {
        label.setTextFill(Color.WHITE);
        label.setFont(LayoutUtils.getStandardFont(44));
        table.setEditable(false);

        back.setFont(LayoutUtils.getStandardFont(24));
        back.setTextFill(Color.WHITE);
        back.setStyle("-fx-text-fill: black;");

        export.setFont(LayoutUtils.getStandardFont(24));
        export.setTextFill(Color.WHITE);
        export.setStyle("-fx-text-fill: black;");

        table.getItems().addAll(entries.getTeamEntries().get(team));

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

        TableColumn<Entry, Integer> scoreColumn = (TableColumn<Entry, Integer>) Entry.createColumn("totalScore");

        table.getColumns().addAll(
                Entry.createColumn("teamNumber", "Team #"),
                Entry.createColumn("matchNumber", "Match #"),
                taxiColumn,

                Entry.createColumn("allianceColor", "Color"),

                Entry.createColumn("autoLower", "A Lower"),
                Entry.createColumn("autoUpper", "A Upper"),
                Entry.createColumn("autoMissed", "A Missed"),

                Entry.createColumn("autoScore", "A Score"),
                autoAccuracyColumn,

                Entry.createColumn("teleopLower", "T Lower"),
                Entry.createColumn("teleopUpper", "T Upper"),
                Entry.createColumn("teleopMissed", "T Missed"),

                Entry.createColumn("teleopScore", "T Score"),
                teleopAccuracyColumn,

                climbColumn,
                Entry.createColumn("climbTime", "Climb Time"),
                scoreColumn,
                Entry.createColumn("name"),
                Entry.createColumn("comment"));

        table.setMinHeight(Screen.getPrimary().getBounds().getHeight() * .7);
        table.setMinWidth(Screen.getPrimary().getBounds().getWidth() * .6);

        final VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 0, 0, 10));

        vbox.getChildren().addAll(label, table, LayoutUtils.bundleIntoHBox(back, export));

        scoreColumn.setSortType(TableColumn.SortType.DESCENDING);
        table.getSortOrder().add(scoreColumn);

        panel.getChildren().add(vbox);
        panel.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    public Button getBackButton() {
        return back;
    }

    public Button getExportButton() {
        return export;
    }

}
