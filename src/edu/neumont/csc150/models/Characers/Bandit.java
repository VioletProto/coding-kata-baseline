package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class Bandit extends Enemy {
    public Bandit(Armor armor, Weapon weapon) {
        super("Bandit", 50, armor, weapon, 40, Status.HEALTHY, rand.nextInt(50, 80));
    }
}
