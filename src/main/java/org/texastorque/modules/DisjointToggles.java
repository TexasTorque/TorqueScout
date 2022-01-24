/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.modules;

import java.util.ArrayList;
import java.util.Map;

import org.texastorque.components.FadeButton;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DisjointToggles extends Module {
    Pane panel = new VBox();

    private String[] names = null;
    int value = 0;

    public DisjointToggles(String name, String... names) {
        this.name = name;
        this.names = names;
        init();
    }

    ArrayList<ToggleSingle> toggles = new ArrayList<ToggleSingle>();

    @Override
    protected void init() {
        final double topMargin = 5;

        Label label = new Label(name);
        panel.setPrefSize(320, 1000);
        panel.getChildren().addAll(label);
        for (String local : names) {
            ToggleSingle toggle = new ToggleSingle(local);
            toggle.getButton().setOnAction((event) -> {
                for (int i = 0; i < toggles.size(); i++) {
                    if (toggles.get(i) != toggle) {
                        toggles.get(i).getButton().setText("✗");
                        toggles.get(i).getButton().setStyle("-fx-background-color: red;");
                    } else {
                        if (value - 1 == i) {
                            toggles.get(i).getButton().setText("✗");
                            toggles.get(i).getButton().setStyle("-fx-background-color: red;");
                            value = 0;
                        } else {
                            toggles.get(i).getButton().setText("✓");
                            toggles.get(i).getButton().setStyle("-fx-background-color: green;");
                            value = i + 1;
                        }
                    }
                }
                System.out.println(value);
            });
            toggles.add(toggle);
        }



        for (ToggleSingle toggle : toggles) {
            panel.getChildren().addAll(toggle.getPanel());
        }

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    public int getValue() {
        return value;
    }
}
