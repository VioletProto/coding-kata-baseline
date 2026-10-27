package edu.neumont.csc150.models.items;

import edu.neumont.csc150.models.items.enums.Quality;

import java.util.Random;

public abstract class Item {
    private String itemName;
    private Quality quality;
    private int durability;
    public static final int MAX_DURABILITY = 100;
    public static final int MIN_DURABILITY = 0;
    protected static final Random random = new Random();

    public Item(String itemName, Quality quality, int durability) {
        setItemName(itemName);
        setQuality(quality);
        setDurability(durability);
    }

    //region Getters/Setters

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        if (itemName == null || itemName.isBlank())
            throw new IllegalArgumentException("itemName cannot be null or empty");
        this.itemName = itemName;
    }

    public Quality getQuality() {
        return quality;
    }

    protected void setQuality(Quality quality) {
        if (quality == null)
            throw new IllegalArgumentException("quality cannot be null");
        this.quality = quality;
    }

    public int getDurability() {
        return durability;
    }

    protected void setDurability(int durability) {
        if (durability < 0) {
            durability = 0;
            setQuality(Quality.BROKEN);
        } else if (durability > 100) {
            durability = 100;
        }
        this.durability = durability;
    }

    //endregion

    public abstract void use();
}
