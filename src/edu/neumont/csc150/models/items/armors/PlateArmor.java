package edu.neumont.csc150.models.items.armors;

import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public class PlateArmor extends Armor {

    public PlateArmor(Quality quality) {
        this("Plate armor", quality);
    }

    public PlateArmor(String itemName, Quality quality) {
        super(itemName, quality, 5, DamageType.SLASH);
    }
}
