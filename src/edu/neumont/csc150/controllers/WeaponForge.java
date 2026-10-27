package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.armors.ChainMailArmor;
import edu.neumont.csc150.models.items.armors.LeatherArmor;
import edu.neumont.csc150.models.items.armors.PlateArmor;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.potions.Potion;
import edu.neumont.csc150.models.items.weapons.*;

public class WeaponForge {

    public static Weapon createRandomWeapon(Quality quality) {
        Weapon weapon;
        int selection = BattleController.rand.nextInt((5) + 1);
        switch (selection) {
            case 1 -> {
                weapon = new Shortsword(quality);
            }
            case 2 -> {
                weapon = new Longsword(quality);
            }
            case 3 -> {
                weapon = new Greatsword(quality);
            }
            case 4 -> {
                weapon = new Rapier(quality);
            }
            default -> {
                weapon = new Mace(quality);
            }
        }
        return weapon;
    }

    public static Armor createRandomArmor(Quality quality) {
        Armor armor;
        int selection = BattleController.rand.nextInt((3) + 1);
        switch (selection) {
            case 1 -> {
                armor = new LeatherArmor(quality);
            }
            case 2 -> {
                armor = new ChainMailArmor(quality);
            }
            default -> {
                armor = new PlateArmor(quality);
            }
        }
        return armor;
    }

    public static Potion createRandomPotion(Quality quality) {
        return new Potion(quality);
    }
}
