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

    public boolean writeReport(Report report) {
        try {
            System.out.println("Writing report to " + path);
            Files.write(Paths.get(path), report.toCSV().getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            ErrorUtils.displayError("Data Writer Error", "Could not write entry to local database");
            return false;
        }
    }
    
}
