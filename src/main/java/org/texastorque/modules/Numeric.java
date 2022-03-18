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

import org.texastorque.components.FadeButton;
import org.texastorque.utils.LayoutUtils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Input module for numeric data.
 * 
 * @author Justus Languell
 */
public class Numeric extends Module {
    private int value = 0;
    private int increment = 1;
    private int min = 0;

    public Numeric(String name) {
        this.name = name;
        init();
    }

    public Numeric(String name, int increment) {
        this.name = name;
        this.increment = increment;
        init();
    }

    public Numeric(String name, int increment, int min) {
        this.name = name;
        this.increment = increment;
        this.min = min;
        init();
    }

    Label label = new Label(name);
    FadeButton add = new FadeButton();
    TextField count = new TextField("");
    FadeButton sub = new FadeButton();

    @Override
    public void init() {
        label.setTextFill(Color.WHITE);
        final double topMargin = 5;

        panel.setPrefSize(320, 60);

        label.setText(name);
        label.setPrefSize(130, 50);
        label.setLayoutX(10);
        label.setFont(LayoutUtils.getStandardFont(18));
        label.setLayoutY(topMargin);

        add.setText("+");
        add.setPrefSize(50, 50);
        add.setLayoutX(270);
        add.setStyle("-fx-background-color: green;");
        add.setLayoutY(topMargin);

        count.setPrefSize(50, 50);
        count.setLayoutX(210);
        count.setFont(LayoutUtils.getStandardFont(18));
        count.setEditable(false);
        count.setLayoutY(topMargin);

        sub.setText("–");
        sub.setPrefSize(50, 50);
        sub.setLayoutX(150);
        sub.setStyle("-fx-background-color: red;");
        sub.setLayoutY(topMargin);

        add.setOnAction((event) -> {
            increment();
        });

        sub.setOnAction((event) -> {
            decrement();
        });

        panel.getChildren().addAll(label, add, count, sub);
        count.setText(String.format("%d", value));
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

    public void increment() {
        value = Math.max(value + increment, min);
        count.setText(String.format("%d", value));
    }

    public void decrement() {
        value = Math.max(value - increment, min);
        count.setText(String.format("%d", value));
    }
}
