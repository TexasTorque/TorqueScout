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

public class ValueDisplay extends Module {
    private String value = "";

    public ValueDisplay(String name, String value) {
        this.name = name;
        this.value = value;
        init();
    }

    public ValueDisplay(String name, int value) {
        this.name = name;
        this.value = String.format("%d", value);
        init();
    }

    public ValueDisplay(String name, double value) {
        this.name = name;
        this.value = String.format("%f", value);
        init();
    }

    Label label = new Label(name);
    TextField display = new TextField("");

    @Override
    protected void init() {
        label.setTextFill(Color.WHITE);
        final double topMargin = 5;

        panel.setPrefSize(320, 60);

        label.setText(name);
        label.setPrefSize(130, 50);
        label.setLayoutX(10);
        label.setFont(LayoutUtils.getStandardFont(18));
        label.setLayoutY(topMargin);

        display.setPrefSize(180, 50);
        display.setLayoutX(140);
        display.setFont(LayoutUtils.getStandardFont(18));
        display.setLayoutY(topMargin);

        panel.getChildren().addAll(label, display);
        display.setText(value);
    }

    public void setEditable(boolean editable) {
        display.setEditable(editable);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pane getPanel() {
        return panel;
    }

    public String getValue() {
        if (display.isEditable())
            value = display.getText();
        return value;
    }
}
