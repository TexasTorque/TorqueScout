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

import java.util.Optional;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;

/**
 * A collection of useful alerts and dialog boxes.
 * 
 * @author Justus
 */
public class NoticeUtils {

    private NoticeUtils() {}

    /**
     * Displays an alert that notifies the user of an error.
     * 
     * @param header Header text.
     * @param content Content text.
     */
    public static void displayError(String header, String content) {
        Alert notice = new Alert(AlertType.ERROR);
        notice.setHeaderText(header);
        notice.setContentText(content);
        notice.showAndWait();
    }

    /**
     * Displays an alert that presents information to the user.
     * 
     * @param header Header text.
     * @param content Content text.
     */
    public static void displayInfo(String header, String content) {
        Alert notice = new Alert(AlertType.INFORMATION);
        notice.setHeaderText(header);
        notice.setContentText(content);
        notice.showAndWait();
    }

    /**
     * Displays an alert that presents information to the user,
     * and then waits for the user to confirm or deny.
     * 
     * @param header Header text.
     * @param content Content text.
     * @return true if the user confirmed, false if the user denied.
     */
    public static boolean displayConfirmation(String header, String content) {
        Alert notice = new Alert(AlertType.CONFIRMATION);
        notice.setHeaderText(header);
        notice.setContentText(content);
        Optional<ButtonType> result = notice.showAndWait();
        return result.get().getText().equals("OK");
    }

    /**
     * Displays a dialog box that prompts the user for a string.
     * 
     * @param header Header text.
     * @param content Content text.
     * @return The string the user entered.
     */
    public static String promptString(String header, String content) {
        TextInputDialog prompt = new TextInputDialog();
        prompt.setTitle(header);
        prompt.setHeaderText(content);
        prompt.setContentText("Enter a value:");
        Optional<String> result = prompt.showAndWait();
        return result.get();
    }

     /**
     * Displays a custom dialog box that prompts the user for a password.
     * 
     * @param header Header text.
     * @param content Content text.
     * @return The string the user entered.
     */
    public static String promptPassword(String header, String content) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle(header);
        dialog.setHeaderText(content);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        PasswordField pwd = new PasswordField();
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER_LEFT);
        box.setSpacing(10);
        box.getChildren().addAll(new Label("Password: "), pwd);
        dialog.getDialogPane().setContent(box);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK)
                return pwd.getText();
            return "";
        });

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
            return result.get();
        return "";
    }
}
