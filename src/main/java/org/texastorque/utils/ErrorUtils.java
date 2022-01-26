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

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorUtils {
    
    private ErrorUtils() {}

    public static void displayError(String header, String content) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setHeaderText(header);
        errorAlert.setContentText(content);
        errorAlert.showAndWait();
    }
}
