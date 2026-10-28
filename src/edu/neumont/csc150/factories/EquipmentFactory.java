package edu.neumont.csc150.factories;

import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.potions.Potion;
import edu.neumont.csc150.models.items.weapons.Weapon;

public interface EquipmentFactory {
    Weapon createWeapon();
    Armor createArmor();
    Potion createPotion();

}
