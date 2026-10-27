package edu.neumont.csc150.models.Characers;

import edu.neumont.csc150.models.Interfaces.Attackable;
import edu.neumont.csc150.models.flavorText.AttackResult;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.DamageType;
import edu.neumont.csc150.models.items.weapons.Weapon;

public abstract class Character implements Attackable {
    public final static int BASE_DEFENCE = 10;
    public final static int MAX_HEALTH = 250;
    public final static int MIN_HEALTH = 0;
    private String name;
    private int health;
    private Armor armor;
    private Weapon weapon;
    private int Money;
    private int defence;
    private boolean isAlive;



    public Character(String name, int health, Armor armor, Weapon weapon,int money, int defence) {
        setName(name);
        setHealth(health);
        setArmor(armor);
        setWeapon(weapon);
        setMoney(money);
        setDefence(defence);
        setAlive(true);
    }

    //region Getters/Setters

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Name cannot be null or empty");
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int health) {
        if (health < MIN_HEALTH) {
            health = MIN_HEALTH;
        } else if (health > MAX_HEALTH) {
            health = MAX_HEALTH;
        }

        this.health = health;
    }

    public int getMoney() {
        return Money;
    }

    protected void setMoney(int money) {
        if (money < 0) {
            money = 0;
        }
        Money = money;
    }

    public boolean canAffordItem (int itemCost){
        return itemCost <= getMoney();
    }

    public Armor getArmor() {
        return armor;
    }

    protected void setArmor(Armor armor) {
        if (armor == null) {
            return;
        }
        setDefence(BASE_DEFENCE);
        this.armor = armor;
        setDefence(getDefence() + armor.getBlockStat());
    }

    public Weapon getWeapon() {
        return weapon;
    }

    protected void setWeapon(Weapon weapon) {
        if (weapon == null)
            return;
        this.weapon = weapon;
    }

    public int getDefence() {
        return defence;
    }

    protected void setDefence(int defence) {
        if (defence < BASE_DEFENCE) {
            defence = BASE_DEFENCE;
        }
        this.defence = defence;
    }

    private void setAlive(boolean alive) {
        this.isAlive = alive;
    }

    public abstract void equipWeapon(Weapon weapon);

    public abstract Weapon removeWeapon();

    public abstract void equipArmor(Armor armor);

    public abstract Armor removeArmor();

    public void addMoney(int money) {
        setMoney(getMoney() + money);
    }

    public int removeMoney(int money) {
        setMoney(getMoney() - money);
        return money;
    }

    public void addHealth(int health) {
        setHealth(getHealth() + health);
    }

    public int removeHealth(int health) {
        setHealth(getHealth() - health);
        return health;
    }

    //endregion

    @Override
    public AttackResult attack(Attackable target) {
        int attackResult = Attackable.rollDice(1, 20);
        int combinedResult = attackResult + getWeapon().getHitBonus();
        if (attackResult == 20) {
            return target.takeDamage(((Attackable.rollDice(1, 20) +
                            getWeapon().getDamageBonus()) * 2)
                    , getWeapon().getDamageType(), true, this.getName()
                    , ((Character) target).getName());

        } else if (combinedResult >= ((Character) target).getDefence()) {
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
        if (hitOrMiss) {
            int takenDamage;
            if (damageType == getArmor().getResistanceType()) {
                takenDamage = amount / 2;
            } else {
                takenDamage = amount;
            }
            setHealth(getHealth() - takenDamage);
            isAlive();
            return new AttackResult(takenDamage, true, attacker, defender, isAlive());
        }
        return new AttackResult(0, false, attacker, defender, isAlive());
    }

    @Override
    public boolean isAlive() {
        if (getHealth() == MIN_HEALTH) {
            setAlive(false);
        }
        return isAlive;
    }

}
