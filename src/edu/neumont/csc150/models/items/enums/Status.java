package edu.neumont.csc150.models.items.enums;

public enum Status {
    BEGGING("Begging"),
    HEALTHY("Healthy"),
    STRONG("Strong"),
    ANGRY("Angry"),
    TIRED("Tired");

    private String friendlyName;
    Status(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString() {
        return friendlyName;
    }
}
