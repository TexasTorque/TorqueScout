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

    public static class Padding {
        public double top, right, bottom, left;

        public Padding(double padding) {
            top = padding;
            right = padding;
            bottom = padding;
            left = padding;
        }

        public Padding(double top, double right, double bottom, double left) {
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.left = left;
        }
    }

    public static Pane insertPadding(Pane panel, Padding padding) {
        panel.setPadding(new javafx.geometry.Insets(padding.top,
                padding.right,
                padding.bottom,
                padding.left));
        return panel;
    }

    public static Pane bundleIntoVBox(int w, int h, Node... nodes) {
        Pane pane = new VBox();
        for (Node o : nodes)
            pane.getChildren().add(o);
        pane.setPrefWidth(w);
        pane.setPrefHeight(h);
        return pane;
    }

    public static Pane bundleIntoVBox(Node... nodes) {
        Pane pane = new VBox();
        for (Node o : nodes)
            pane.getChildren().add(o);
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

    public static Pane wrapInPane(Node... nodes) {
        Pane pane = new Pane();
        for (Node o : nodes) {
            pane.getChildren().add(o);
        }
        return pane;
    }
}
