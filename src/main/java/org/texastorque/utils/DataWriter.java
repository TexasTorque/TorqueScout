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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class DataWriter {
    String path;

    DirectoryChooser directoryChooser = new DirectoryChooser();

    public DataWriter() {
        path = System.getProperty("user.home") + "/.scouting-database";
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    private void appendString(String s) throws IOException {
        Files.write(Path.of(path), (s.replace("\n", " ") + "\n").getBytes(), StandardOpenOption.APPEND);
    }

    public boolean writeReport(Report report) {
        try {
            if (!Files.exists(Path.of(path))) {
                Files.createFile(Path.of(path));
                appendString(Report.header);
            }
            appendString(report.toCSV());
            return true;
        } catch (IOException e) {
            NoticeUtils.displayError("Data Writer Error", "Could not write entry to local database");
            return false;
        }
    }

    public boolean export(Stage s) {
        try {
            String content = Files.readString(Path.of(path));
            File selectedDirectory = directoryChooser.showDialog(s);
            Path exportPath = Path.of(selectedDirectory.getAbsolutePath() + "/" + "scouting-data.csv");
            Files.writeString(exportPath, content);
            NoticeUtils.displayInfo("Data Exported Success", "Successfully exported scouting report");
            return true;
        } catch (IOException e) {
            NoticeUtils.displayError("Data Exporter Error", "Could not export data");
            return false;
        }
    }

}
