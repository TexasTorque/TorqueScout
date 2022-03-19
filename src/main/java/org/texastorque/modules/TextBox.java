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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class TextBox extends Module {
    private String value = "";

    public TextBox(String name, String value) {
        this.name = name;
        this.value = value;
        init();
    }

    Label label = new Label(name);
    TextArea textArea = new TextArea("");

    @Override
    protected void init() {
        panel.setPrefSize(300, 320);

        textArea.setPrefSize(320, 180);
        textArea.setFont(LayoutUtils.getStandardFont(14));
        textArea.setLayoutX(10);
        textArea.setLayoutY(10);
        textArea.wrapTextProperty().set(true);

        panel.getChildren().addAll(label, textArea);
    }

    public void setEditable(boolean editable) {
        textArea.setEditable(editable);
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
        if (textArea.isEditable())
            value = textArea.getText();
        return value;
    }
}
