package edu.neumont.csc150.factories;

import edu.neumont.csc150.controllers.WeaponForge;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.potions.Potion;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class HighEquipmentFactory implements EquipmentFactory{

    @Override
    public Weapon createWeapon() {
        return WeaponForge.createRandomWeapon(Quality.HIGH);
    }

    @Override
    public Armor createArmor() {
        return WeaponForge.createRandomArmor(Quality.HIGH);
    }

    @Override
    public Potion createPotion() {
        return WeaponForge.createRandomPotion(Quality.HIGH);
    }
}
