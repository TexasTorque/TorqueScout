package org.texastorque.utils;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataWrapper {

    private ObservableList<Entry> entries;
    private ObservableList<Entry> averages = FXCollections.observableArrayList();
    private HashMap<Integer, ObservableList<Entry>> teamEntries
            = new HashMap<Integer, ObservableList<Entry>>();
    
    public DataWrapper(ObservableList<Entry> entries) {
        this.entries = entries;

        for (Entry entry : entries) {
            if (teamEntries.containsKey(entry.getTeamNumber()))
                teamEntries.get(entry.getTeamNumber()).add(entry);
            else {
                ObservableList<Entry> newList = FXCollections.observableArrayList();
                newList.add(entry);
                teamEntries.put(entry.getTeamNumber(), newList);
            }
        }

        for (Map.Entry<Integer, ObservableList<Entry>> _entries : teamEntries.entrySet())
            averages.add(Entry.fromAveraged(_entries.getValue()));
    }

    public ObservableList<Entry> getEntries() {
        return entries;
    }

    public ObservableList<Entry> getAverages() {
        return averages;
    }

    public HashMap<Integer, ObservableList<Entry>> getTeamEntries() {
        return teamEntries;
    }
}
