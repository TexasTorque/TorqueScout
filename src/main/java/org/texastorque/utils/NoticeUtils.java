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

public class NoticeUtils {
    
    private NoticeUtils() {}

    public static void displayError(String header, String content) {
        Alert notice = new Alert(AlertType.ERROR);
        notice.setHeaderText(header);
        notice.setContentText(content);
        notice.showAndWait();
    }

    public static void displayInfo(String header, String content) {
        Alert notice = new Alert(AlertType.INFORMATION);
        notice.setHeaderText(header);
        notice.setContentText(content);
        notice.showAndWait();
    }
}
