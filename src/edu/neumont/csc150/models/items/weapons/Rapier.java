package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Rapier extends Weapon {

    public Rapier(Quality quality) {
        this("Rapier", quality);
    }

    public Rapier(String itemName, Quality quality) {
        super(itemName, quality, DamageType.PEIRCE, 6, 5);
    }
}
