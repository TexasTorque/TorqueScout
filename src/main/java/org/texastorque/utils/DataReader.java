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

public class DataReader {
    String path;

    DirectoryChooser directoryChooser = new DirectoryChooser();

    public DataReader() {
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public boolean getDataSet(Stage s) {
        try {
            File selectedDirectory = directoryChooser.showDialog(s);

            String content = "";

            String[] fileNames = (new File(selectedDirectory.getAbsolutePath())).list();
            for (String fileName : fileNames) {
                if (fileName.contains("scouting-data-")) {
                    path = selectedDirectory.getAbsolutePath() + "/" + fileName;
                    content += Files.readString(Path.of(path)).replace(Report.header, "");
                }
            }

            System.out.println(content);
            
            return true;
        } catch (IOException e) {
            NoticeUtils.displayError("Data Reader Error", "Could not read data directory");
            return false;
        }
    }

}