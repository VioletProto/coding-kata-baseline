package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.Interfaces.Attackable;
import edu.neumont.csc150.models.flavorText.AttackResult;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.armors.Shirt;
import edu.neumont.csc150.models.items.enums.DamageType;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.weapons.Fist;
import edu.neumont.csc150.models.items.weapons.Weapon;

import java.util.Random;

public abstract class Enemy extends Character {
    public final int MIN_ENERGY = 0;
    public final int MAX_ENERGY = 100;
    private int startingHealth;
    private int Energy;
    private Status status;
    protected static final Random rand = new Random();

    public Enemy(String name, int health, Armor armor, Weapon weapon, int money, Status status, int energy) {
        super(name, health, armor, weapon, money, BASE_DEFENCE);
        setDefence(getDefence() + getArmor().getBlockStat());
        setEnergy(energy);
        setStatus(status);
        setStartingHealth(health);

    }

    //region Getters/Setters

    public Status getStatus() {
        return status;
    }

    protected void setStatus(Status status) {
        if (status == null)
            throw new IllegalArgumentException("Status was null for some reason");
        this.status = status;
    }

    public int getEnergy() {
        return Energy;
    }

    private void setEnergy(int energy) {
        if (energy < MIN_ENERGY) {
            energy = MIN_ENERGY;
        } else if (energy > MAX_ENERGY) {
            energy = MAX_ENERGY;
        }
        Energy = energy;
    }

    public int getStartingHealth() {
        return startingHealth;
    }

    private void setStartingHealth(int startingHealth) {
        this.startingHealth = startingHealth;
    }

    //endregion

    public void lowEnergy(int amount){
        setEnergy(getEnergy() - amount);
    }

    @Override
    public void equipWeapon(Weapon weapon) {
        setWeapon(weapon);
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
        setArmor(armor);
    }

    @Override
    public Armor removeArmor() {
        if (getArmor() instanceof Shirt) {
            throw new IllegalArgumentException("You cannot unequip shirts");
        }
        Armor tempArmor = getArmor();
        setArmor(new Shirt());
        return tempArmor;
    }

    @Override
    public AttackResult attack(Attackable target) {
        int attackResult = Attackable.rollDice(1, 20);
        int combinedResult = attackResult + getWeapon().getHitBonus();

        switch (getStatus()){
            case BEGGING -> {
                attackResult = 0;
                combinedResult = 0;
            }
            case ANGRY -> combinedResult = (int) (combinedResult * 1.25);
            case STRONG -> combinedResult = (int) (combinedResult * 1.5);
            case TIRED -> combinedResult = (int) (combinedResult * .75);
        }

        if (attackResult == 20) {
            lowEnergy(10);
            return target.takeDamage(((Attackable.rollDice(1, 20) +
                            getWeapon().getDamageBonus()) * 2)
                    , getWeapon().getDamageType(), true, this.getName()
                    , ((Character) target).getName());
        } else if (combinedResult >= ((Character) target).getDefence()) {
            lowEnergy(10);
            return target.takeDamage(Attackable.rollDice(1, 20) +
                            getWeapon().getDamageBonus()
                    , getWeapon().getDamageType(), true, this.getName()
                    , ((Character) target).getName());
        } else {
            return target.takeDamage(0, getWeapon().getDamageType(), false
                    , this.getName(), ((Character) target).getName());
        }
    }

    @Override
    public AttackResult takeDamage(int amount, DamageType damageType, boolean hitOrMiss, String attacker, String defender) {
        if (amount >= (getStartingHealth() / 2)) {
            setStatus(Status.BEGGING);
        } else if (getEnergy() <= MAX_ENERGY / 8) {
            setStatus(Status.BEGGING);
        } else {
            if(hitOrMiss || amount != 0) {
                int probablility = Attackable.rollDice(1, 20);
                if (probablility > 12) {
                    setStatus(Status.ANGRY);
                } else if (probablility == 1) {
                    setStatus(Status.BEGGING);
                }
            }
        }

        switch (getStatus()) {
            case STRONG -> amount = (int) (amount * 0.75f);
            case ANGRY -> amount = (int) (amount * 1.15f);
            case TIRED -> amount = (int) (amount * 1.25);
            case BEGGING -> amount = (int) (amount * 0.50f);
        }

        return super.takeDamage(amount, damageType, hitOrMiss, attacker, defender);
    }
}
