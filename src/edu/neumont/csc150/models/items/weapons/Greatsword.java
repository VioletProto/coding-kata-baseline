package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Greatsword extends Weapon {

    public Greatsword(Quality quality) {
        this("Greatsword", quality);
    }

    public Greatsword(String itemName, Quality quality) {
        super(itemName, quality, DamageType.SLASH, 7, 5);
    }
}
