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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class DataWriter {
    String path;

    public DataWriter() {
        path = System.getProperty("user.home") + "/.scouting-database";
    }

    private void appendString(String s) throws IOException {
        Files.write(Paths.get(path), (s.replace("\n", "") + "\n").getBytes(), StandardOpenOption.APPEND); 
    }

    public boolean writeReport(Report report) {
        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createFile(Paths.get(path));
                appendString(Report.header); 
            }
            appendString(report.toCSV());
            return true;
        } catch (IOException e) {
            ErrorUtils.displayError("Data Writer Error", "Could not write entry to local database");
            return false;
        }
    }
    
}
