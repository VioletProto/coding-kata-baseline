package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class Mercenary extends Enemy{
    public Mercenary(Armor armor, Weapon weapon) {
        super("Mercenary", 70, armor, weapon, 60, Status.STRONG, rand.nextInt(80, 100));
    }
}
