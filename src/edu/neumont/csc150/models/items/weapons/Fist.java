package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class Fist extends Weapon{

    public Fist() {
        super("Fists", Quality.MEDIUM, DamageType.BLUDGEONING, 2, 2);
        setDurability(100);
    }

}
