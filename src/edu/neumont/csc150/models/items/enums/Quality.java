package edu.neumont.csc150.models.items.enums;

public enum Quality {
    BROKEN("Broken"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),
    LEGENDARY("Legendary");

    private final String friendlyName;
    Quality(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString() {
        return friendlyName;
    }
}
