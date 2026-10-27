package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Mace extends Weapon {

    public Mace(Quality quality) {
        this("Mace", quality);
    }

    public Mace(String itemName, Quality quality) {
        super(itemName, quality, DamageType.BLUDGEONING, 7, 4);
    }
}
