package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Longsword extends Weapon {

    public Longsword(Quality quality) {
        this("Longsword", quality);
    }

    public Longsword(String itemName, Quality quality) {
        super(itemName, quality, DamageType.SLASH, 7, 6);
    }
}
