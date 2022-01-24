/**
 * Copyright (C) 2021-2022 TexasTorque - All Rights Reserved.
 *
 * This file is part of TorqueScout which is proprietary software.
 * TorqueScout is not available for modification or distribution without express consent from TexasTorque.
 * See file ./license.txt or go write <jus@gtsbr.org> for full license details.
 * 
 * @author Justus Languell
 */
package org.texastorque.utils;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LayoutUtils {
    private LayoutUtils() {
    }

    public static Pane bundleIntoVBox(Node... nodes) {
        Pane pane = new VBox();
        for (Node o : nodes) {
            pane.getChildren().add(o);
        }
        return pane;
    }

    public static Pane bundleIntoHBox(Node... nodes) {
        Pane pane = new HBox();
        for (Node o : nodes) {
            pane.getChildren().add(o);
        }
        return pane;
    }

    public static Label generateLabel(String text, int fontSize) {
        Label label = new Label(text);
        label.setFont(new Font(fontSize));
        return label;
    }

}
