package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class Assassin extends Enemy{

    public Assassin(Armor armor, Weapon weapon) {
        super("Assassin", 90, armor, weapon, 80, Status.HEALTHY, rand.nextInt(80, 100));
    }
}
