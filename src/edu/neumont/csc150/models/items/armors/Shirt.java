package edu.neumont.csc150.models.items.armors;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Shirt extends Armor {
    public Shirt() {
        super("Shirt", Quality.MEDIUM, 1, DamageType.BLUDGEONING);
    }
}
