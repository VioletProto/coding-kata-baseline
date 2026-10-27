package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.controllers.WeaponForge;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Weapon;

public class FinalBoss extends Enemy{
    public FinalBoss() {
        super("Evil King", MAX_HEALTH, WeaponForge.createRandomArmor(Quality.LEGENDARY), WeaponForge.createRandomWeapon(Quality.LEGENDARY), 500, Status.STRONG, 100);
    }

}
