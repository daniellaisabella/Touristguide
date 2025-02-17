package org.example.touristguide.model;

public enum City {
    KØBENHAVN("København"), MØN("Møn");

    private String displayName;

    City(String displayName){
        this.displayName=displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
