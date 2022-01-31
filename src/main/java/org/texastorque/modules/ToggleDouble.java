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

import org.texastorque.utils.LayoutUtils;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Module for inputting boolean data using two buttons
 * that disable eachother.
 * 
 * @author Justus Languell
 */
public class ToggleDouble extends Module {
    private boolean value = false;

    public ToggleDouble(String name) {
        this.name = name;
        init();
    }

    @Override
    public void init() {
        // panel.setStyle("-fx-background-color: black;");
        panel.setPrefSize(230, 60);

        Button on = new Button();
        Button off = new Button();
        Label label = new Label(name);

        on.setFont(LayoutUtils.getStandardFont(14));
        off.setFont(LayoutUtils.getStandardFont(14));
 
        label.setPrefSize(100, 50);
        label.setLayoutX(10);

        on.setText("✓");
        on.setPrefSize(50, 50);
        on.setLayoutX(120);
        on.setStyle("-fx-background-color: green;");

        off.setText("✗");
        off.setPrefSize(50, 50);
        off.setLayoutX(180);
        off.setStyle("-fx-background-color: red;");
        off.setOpacity(.5);
        off.setDisable(true);

        on.setOnAction((event) -> {
            value = true;
        });

        on.onMouseReleasedProperty().set(e -> {
            on.setOpacity(.5);
            on.setDisable(true);

            off.setOpacity(1);
            off.setDisable(false);
        });

        off.setOnAction((event) -> {
            value = false;
        });

        off.onMouseReleasedProperty().set(e -> {
            on.setOpacity(1);
            on.setDisable(false);

            off.setOpacity(.5);
            off.setDisable(true);
        });

        panel.getChildren().addAll(label, on, off);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    public boolean getValue() {
        return value;
    }
}
