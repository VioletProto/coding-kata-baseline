package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.items.armors.Armor;

import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class Thug extends Enemy{
    public Thug(Armor armor, Weapon weapon) {
        super("Street thug", 30, armor, weapon, 20, Status.TIRED, rand.nextInt(20, 70));
    }
}
