package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.armors.LeatherArmor;
import edu.neumont.csc150.models.items.armors.Shirt;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.weapons.Fist;
import edu.neumont.csc150.models.items.weapons.Shortsword;
import edu.neumont.csc150.models.items.weapons.Weapon;

import java.util.ArrayList;

public class Player extends Character {
    private final Item[] backpack = new Item[10];
    private int karma;

    public Player(String name) {
        super(name, 30, new LeatherArmor(Quality.MEDIUM), new Shortsword(Quality.MEDIUM), 50, BASE_DEFENCE);
        setKarma(100);
    }

    //region Getters/Setters

    public int getKarma() {
        return karma;
    }

    private void setKarma(int karma) {
        if (karma < 0) {
            karma = 0;
        } else if (karma > 100) {
            karma = 100;
        }
        this.karma = karma;
    }

    //endregion

    @Override
    public void equipWeapon(Weapon weapon) {
        if (getWeapon() instanceof Fist) {
            setWeapon(weapon);
        } else {
            addToBackpack(removeWeapon());
            equipWeapon(weapon);
        }
    }

    @Override
    public Weapon removeWeapon() {
        if (getWeapon() instanceof Fist) {
            throw new IllegalArgumentException("You cannot unequip fists");
        }
        Weapon tempWeapon = getWeapon();
        setWeapon(new Fist());
        return tempWeapon;
    }

    @Override
    public void equipArmor(Armor armor) {
        if (getArmor() instanceof Shirt) {
            setArmor(armor);
        } else {
            addToBackpack(removeArmor());
            equipArmor(armor);
        }
    }

    @Override
    public Armor removeArmor() {
        if (getArmor() instanceof Shirt) {
            throw new IllegalArgumentException("You cannot unequip shirt");
        }
        Armor tempArmor = getArmor();
        setArmor(new Shirt());
        return tempArmor;
    }

    public void removeKarma(int amount) {
        setKarma(getKarma() - amount);
    }

    public void addToBackpack(Item item) {
        boolean itemAdded = false;
        for (int i = 0; i < backpack.length; i++) {
            if (backpack[i] == null) {
                backpack[i] = item;
                itemAdded = true;
                break;
            }
        }
        if (!itemAdded) {
            throw new IllegalArgumentException("Backpack is full");
        }
    }

    public void removeItemFromBackpack(Item item) {
        for (int i = 0; i < backpack.length; i++) {
            if (backpack[i] == item) {
                for (int j = i; j < backpack.length - 1; j++) {
                    backpack[j] = backpack[j + 1];
                }
                backpack[backpack.length - 1] = null;
                return;
            }
        }
    }

    public Item[] getBackpack() {
        return backpack.clone();
    }

}
