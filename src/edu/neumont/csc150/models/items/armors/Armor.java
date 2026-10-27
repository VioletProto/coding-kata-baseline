package edu.neumont.csc150.models.items.armors;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.enums.DamageType;

import java.util.Random;

public abstract class Armor extends Item {
    private int BlockStat;
    private DamageType resistanceType;

    public Armor(String itemName, Quality quality, int blockStat, DamageType resistanceType) {
        super(itemName, quality, random.nextInt(50, 101));
        setBlockStat(blockStat);
        setResistanceType(resistanceType);

        switch (getQuality()) {
            case LOW -> {
                setBlockStat((int) (getBlockStat() * 0.8));
                setDurability((int) (getDurability() * 0.8));
            }
            case HIGH -> {
                setBlockStat((int) (getBlockStat() * 1.5));
                setDurability((int) (getDurability() * 1.5));
            }
            case LEGENDARY -> {
                setBlockStat((getBlockStat() * 2));
                setDurability((getDurability() * 2));
            }
        }
    }

    //region Getters/Setters

    public int getBlockStat() {
        return BlockStat;
    }

    protected void setBlockStat(int blockStat) {
        if (blockStat < 0) {
            blockStat = 0;
        } else if (blockStat > 10) {
            blockStat = 10;
        }
        BlockStat = blockStat;
    }

    public DamageType getResistanceType() {
        return resistanceType;
    }

    protected void setResistanceType(DamageType resistanceType) {
        if (resistanceType == null)
            throw new IllegalArgumentException("resistanceType cannot be null");
        this.resistanceType = resistanceType;
    }

    //endregion

    @Override
    public void use() {
        setDurability(getDurability() - random.nextInt((5) + 1));
    }

}
