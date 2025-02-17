package org.example.touristguide.model;

public enum Tags {

    RESTAURANT("Restaurant"), FORLYSTELSESPARK("Forlystelsespark"), NATUROPLEVELSE("Naturoplevelse"), KUNST("Kunst");

    private String displayName;

    Tags(String displayName){
        this.displayName=displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
