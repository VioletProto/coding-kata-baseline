package edu.neumont.csc150.models.items.weapons;


import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Shortsword extends Weapon {

    public Shortsword(Quality quality) {
        this("Short sword", quality);
    }

    public Shortsword(String itemName, Quality quality) {
        super(itemName, quality, DamageType.PEIRCE, 5, 4);
    }

}
