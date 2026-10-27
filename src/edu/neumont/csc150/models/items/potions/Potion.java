package edu.neumont.csc150.models.items.potions;

import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.enums.Quality;

public class Potion extends Item {
    private int healthAmount;

    public Potion(Quality quality) {
        super("Health potion", quality, 1);
        setHealthAmount(20);

        switch (getQuality()){
            case LOW -> setHealthAmount(getHealthAmount() - 2);
            case HIGH -> setHealthAmount(getHealthAmount() * 2);
            case LEGENDARY -> setHealthAmount(getHealthAmount() * 3);
            case BROKEN -> setHealthAmount(0);
        }
    }

    //region Getters/Setters

    public int getHealthAmount() {
        return healthAmount;
    }

    protected void setHealthAmount(int healthAmount) {
        this.healthAmount = healthAmount;
    }

    //endregion

    public int heal(){
        int returnedHealthAmount = getHealthAmount();
        setHealthAmount(0);
        use();
        return returnedHealthAmount;
    }

    @Override
    public void use() {
        setDurability(0);
        setQuality(Quality.BROKEN);
    }
}
