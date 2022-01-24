/**
 * Copyright (C) 2021-2022 Texas Torque - All Rights Reserved.
 * 
 * This file is part of TorqueScout which is proprietary software.
 * See file license.txt or write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.modules;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Module for inputting boolean data using a single button
 * that changes it's state.
 * 
 * @author Justus Languell
 */
public class ToggleSingle extends Module {
    private boolean value = false;

    public ToggleSingle(String name) {
        this.name = name;
        init();
    }

    private Button button = new Button();
    private Label label = new Label(name);

    @Override
    public void init() {
        final double topMargin = 5;

        panel.setPrefSize(320, 60);

        label.setPrefSize(130, 50);
        label.setLayoutX(10);
        label.setFont(new Font(18));
        label.setLayoutY(topMargin);

        button.setPrefSize(170, 50);
        button.setLayoutX(150);
        button.setText("✗");
        button.setStyle("-fx-background-color: red;");
        button.setLayoutY(topMargin);

        button.setOnAction((event) -> {
            value = !value;
            if (value) {
                button.setText("✓");
                button.setStyle("-fx-background-color: green;");
            } else {
                button.setText("✗");
                button.setStyle("-fx-background-color: red;");
            }
        });

        panel.getChildren().addAll(label, button);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    public Button getButton() {
        return button;
    }

    public boolean getValue() {
        return value;
    }
}
