package edu.neumont.csc150.models.items.weapons;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

public abstract class Weapon extends Item {
    private static final int LOWER_DURABILITY_CHANCE = 80;
    private int hitBonus;
    private int damageBonus;
    private DamageType damageType;

    public Weapon(String itemName, Quality quality, DamageType damageType, int damageBonus, int hitBonus) {
        super(itemName, quality, random.nextInt(50, 101));
        setDamageType(damageType);
        setDamageBonus(damageBonus);
        setHitBonus(hitBonus);

        switch (getQuality()) {
            case LOW -> {
                setDamageBonus((int) (getDamageBonus() * 0.8));
                setHitBonus((int) (getHitBonus() * 0.8));
                setDurability((int) (getDurability() * 0.8));
            }
            case HIGH -> {
                setDamageBonus((int) (getDamageBonus() * 1.5));
                setHitBonus((int) (getHitBonus() * 1.5));
                setDurability((int) (getDurability() * 1.5));
            }
            case LEGENDARY -> {
                setDamageBonus((getDamageBonus() * 2));
                setHitBonus((getHitBonus() * 2));
                setDurability((getDurability() * 2));
            }
        }
    }

    //region Getters/Setters

    public int getHitBonus() {
        return hitBonus;
    }

    protected void setHitBonus(int hitBonus) {
        if (hitBonus < 0) {
            hitBonus = 0;
        } else if (hitBonus > 12) {
            hitBonus = 12;
        }
        this.hitBonus = hitBonus;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    protected void setDamageBonus(int damageBonus) {
        if (damageBonus < 0) {
            damageBonus = 0;
        } else if (damageBonus > 14) {
            damageBonus = 14;
        }
        this.damageBonus = damageBonus;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    protected void setDamageType(DamageType damageType) {
        if (damageType == null)
            throw new IllegalArgumentException("damageType cannot be null");
        this.damageType = damageType;
    }

    //endregion

    @Override
    public void use() {
        int probability = random.nextInt(100);
        if (probability > LOWER_DURABILITY_CHANCE) {
            setDurability(getDurability() - 1);
        }
    }

}
