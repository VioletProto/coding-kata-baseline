package edu.neumont.csc150.models.Characers;


import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class CorruptGuard extends Enemy {

    public CorruptGuard(Armor armor, Weapon weapon) {
        super("Corrupt guard", 50, armor, weapon, 40, Status.STRONG, rand.nextInt(60, 100));
    }
}
