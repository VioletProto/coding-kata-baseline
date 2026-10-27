package edu.neumont.csc150.models.items.enums;

public enum DamageType {
    PEIRCE("Peirce"),
    SLASH("Slash"),
    BLUDGEONING("Bludgeoning");

    private final String friendlyName;

    DamageType(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public String toString() {
        return friendlyName;
    }
}
