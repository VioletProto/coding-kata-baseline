package edu.neumont.csc150.models.items.armors;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class LeatherArmor extends Armor {

    public LeatherArmor(Quality quality) {
        this("Leather", quality);
    }

    public LeatherArmor(String itemName, Quality quality) {
        super(itemName, quality, 3, DamageType.PEIRCE);
    }
}
