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
import javafx.scene.paint.Color;
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

    private String trueColor = "green";
    private String trueText = "✓";
    private String falseColor = "red";
    private String falseText = "✗";

    public ToggleSingle(String name) {
        this.name = name;
        init();
    }

    public ToggleSingle(String name, String trueColor, String trueText, String falseColor, String falseText) {
        setTrueColor(trueColor);
        setTrueText(trueText);
        setFalseColor(falseColor);
        setFalseText(falseText);
        this.name = name;
        init();
    }

    private Button button = new Button();
    private Label label = new Label(name);

    @Override
    public void init() {
        label.setTextFill(Color.WHITE);
        final double topMargin = 5;

        button.setFont(LayoutUtils.getStandardFont(14));

        panel.setPrefSize(320, 60);

        label.setText(name);
        label.setPrefSize(130, 50);
        label.setLayoutX(10);
        label.setFont(LayoutUtils.getStandardFont(18));
        label.setLayoutY(topMargin);

        button.setPrefSize(170, 50);
        button.setLayoutX(150);
        button.setText(falseText);
        button.setStyle(colorStyle(falseColor));
        button.setLayoutY(topMargin);

        button.setOnAction((event) -> {
            value = !value;
            if (value) {
                button.setText(trueText);
                button.setStyle(colorStyle(trueColor));
            } else {
                button.setText(falseText);
                button.setStyle(colorStyle(falseColor));
            }
        });

        panel.getChildren().addAll(label, button);
    }

    public void setTrueColor(String trueColor) {
        this.trueColor = trueColor;
    }

    public void setTrueText(String trueText) {
        this.trueText = trueText;
    }

    public void setFalseColor(String falseColor) {
        this.falseColor = falseColor;
    }

    public void setFalseText(String falseText) {
        this.falseText = falseText;
    }

    private String colorStyle(String color) {
        return String.format("-fx-background-color: %s;", color);
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
