package edu.neumont.csc150.models.items.armors;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class ChainMailArmor extends Armor {

    public ChainMailArmor(Quality quality) {
        this("Chain-mail", quality);
    }

    public ChainMailArmor(String itemName, Quality quality) {
        super(itemName, quality, 4, DamageType.SLASH);
    }
}
