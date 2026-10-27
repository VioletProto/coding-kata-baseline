package edu.neumont.csc150.models;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.armors.ChainMailArmor;
import edu.neumont.csc150.models.items.armors.LeatherArmor;
import edu.neumont.csc150.models.items.armors.PlateArmor;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.potions.Potion;
import edu.neumont.csc150.models.items.weapons.*;

import java.util.ArrayList;
import java.util.Random;

public class Trader {
    private final ArrayList<Item> sellableItems = new ArrayList<>();
    private static final Random rand = new Random();
    private static Quality quality;
    private static final int MAX_PRICE = 100;
    private static final int MIN_PRICE = 0;
    private static final int POTION_MIN_PRICE = 20;

    public Trader(Quality quality) {
        Trader.quality = quality;
        refreshStock();
    }

    public ArrayList<Item> getSellableItems() {
        return new ArrayList<>(sellableItems);
    }

    public void refreshStock() {
        sellableItems.clear();
        for (int i = 0; i < 5; i++) {
            sellableItems.add(getRandomItem());
        }
    }

    private Item getRandomItem() {
        int randomItem = rand.nextInt(8);
        switch (randomItem) {
            case 0:
                return new LeatherArmor(quality);
            case 1:
                return new ChainMailArmor(quality);
            case 2:
                return new PlateArmor(quality);
            case 3:
                return new Potion(quality);
            case 4:
                return new Shortsword(quality);
            case 5:
                return new Longsword(quality);
            case 6:
                return new Greatsword(quality);
            case 7:
                return new Rapier(quality);
            default:
                return new Mace(quality);
        }
    }

    public int getItemCost(int Index) {
        Item item = sellableItems.get(Index);
        double cost = 0;
        if (item instanceof Potion) {
            cost = POTION_MIN_PRICE + (1 + (((Potion) item).getHealthAmount() / 4) * (MAX_PRICE - 1) / 50);
        } else {
            cost = MIN_PRICE + ((item.getDurability() / 3) * (MAX_PRICE - 1) / 50);
        }
        return (int) Math.round(cost);
    }

    public int sellItem(Item item) {
        double cost = 0;
        if (item instanceof Potion) {
            cost = POTION_MIN_PRICE + (1 + (((Potion) item).getHealthAmount() / 4) * (MAX_PRICE - 1) / 50);
        } else {
            cost = MIN_PRICE + ((item.getDurability() / 3) * (MAX_PRICE - 1) / 50);
        }
        return (int) Math.round(cost);
    }

    public Item buyItem(int index){
        Item item = sellableItems.get(index);
        sellableItems.remove(index);
        return item;
    }
}
