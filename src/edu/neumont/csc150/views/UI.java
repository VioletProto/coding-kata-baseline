package edu.neumont.csc150.views;

import edu.neumont.csc150.Console;
import edu.neumont.csc150.models.Characers.Player;
import edu.neumont.csc150.models.Trader;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.potions.Potion;
import edu.neumont.csc150.models.items.weapons.Weapon;

import java.util.ArrayList;

public class UI {


    public String getString(String prompt) {
        return Console.getStringInput(prompt, false);
    }

    public void printSlowly(String text, long delay){
        for(char c : text.toCharArray()){
            Console.write(String.valueOf(c));
            try{
                Thread.sleep(delay);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    public void printWholeSlowly(ArrayList<String> text, long delay){
        for(String s : text){
            Console.writeLn(s);
            try{
                Thread.sleep(delay);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        printHR();
    }

    public void printNormal(String text){
        Console.writeLn(text);
    }

    public void printHR(){
        Console.writeLn("----------------------------------------------");
    }

    public void startingText(){
        ArrayList<String> startingText = new ArrayList<>();
        startingText.add("Growing up you looked up to the adventurers, they were your heroes.");
        startingText.add("It is your turn now, to fight for freedom and have stories written about you");
        startingText.add("Get Stronger and stop corruption, for the greater good");
        printWholeSlowly(startingText, 5000);
    }

    public int traderMenu(Trader trader, Player player){
        StringBuilder traderMenu = new StringBuilder();
        for (int i = 0; i < trader.getSellableItems().size(); i++) {
            Item item = trader.getSellableItems().get(i);
            traderMenu.append(i +1);
            traderMenu.append(": $");
            traderMenu.append(trader.getItemCost(i));
            traderMenu.append(" - ");
            traderMenu.append(item.getItemName());
            traderMenu.append(", Quality: ");
            traderMenu.append(item.getQuality().toString());
            traderMenu.append(", Durability: ");
            traderMenu.append(item.getDurability());
            if(item instanceof Potion){
                traderMenu.append(", Health: ");
                traderMenu.append(((Potion) item).getHealthAmount());
            }
            traderMenu.append("\n");
        }

        traderMenu.append(trader.getSellableItems().size() + 1);
        traderMenu.append(": sell");
        traderMenu.append("\n");
        traderMenu.append(trader.getSellableItems().size() + 2);
        traderMenu.append(": I don't want to buy anything at this time\n");
        Console.writeLn("Player money: $" + player.getMoney());
        Console.writeLn(traderMenu.toString());
        int selection = Console.getIntInput("Which would you like? ",
                1, trader.getSellableItems().size() + 2);
        if(selection == trader.getSellableItems().size() + 2){
            return -1;
        } else if(selection == trader.getSellableItems().size() + 1){
            return -2;
        }else {
            return selection - 1;
        }
    }

    public int LootAfterBattle(ArrayList<Item> items){
        StringBuilder lootString = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            lootString.append(i + 1);
            lootString.append(": ");
            lootString.append(item.getItemName());
            lootString.append(", quality: ");
            lootString.append(item.getQuality().toString());
            lootString.append(", Durability: ");
            lootString.append(item.getDurability());
            lootString.append("\n");
        }
        lootString.append(items.size() + 1);
        lootString.append(": Drop item");
        lootString.append("\n");
        lootString.append(items.size() + 2);
        lootString.append(": Exit");
        Console.writeLn(lootString.toString());
        int selection = getUserIntInput("Would you like to pick up anything?",
                1, items.size() + 2);
        if(selection == items.size() + 2){
            return -1;
        } else if(selection == items.size() + 1){
            return -2;
        }else {
            return selection - 1;
        }
    }

    public int selectItemToDrop(ArrayList<Item> items){
        StringBuilder selectString = new StringBuilder();
        items.trimToSize();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            selectString.append(i + 1);
            selectString.append(": ");
            selectString.append(item.getItemName());
            selectString.append(", quality: ");
            selectString.append(item.getQuality().toString());
            selectString.append(", Durability: ");
            selectString.append(item.getDurability());
            if(item instanceof Potion){
                selectString.append(", Health: ");
                selectString.append(((Potion) item).getHealthAmount());
            }
            selectString.append("\n");
        }
        selectString.append(items.size() + 1);
        selectString.append(": Exit");
        Console.writeLn(selectString.toString());
        int selection = getUserIntInput("What would you like to do?",
                1, items.size() + 1);
        if(selection == items.size() + 1){
            return -1;
        }else {
            return selection - 1;
        }
    }

    public int equipWeapon(ArrayList<Weapon> weapons){
        if(weapons.isEmpty()){
            Console.writeLn("You do not have any extra weapons in Backpack");
            return -1;
        }
        StringBuilder equipString = new StringBuilder();
        for(int i = 0; i < weapons.size(); i++){
            equipString.append(i + 1);
            equipString.append(": ");
            equipString.append(weapons.get(i).getItemName());
            equipString.append(", quality: ");
            equipString.append(weapons.get(i).getQuality());
            equipString.append(", Durability: ");
            equipString.append(weapons.get(i).getDurability());
            equipString.append("\n");
        }
        equipString.append(weapons.size() + 1);
        equipString.append(": Exit");
        Console.writeLn(equipString.toString());
        int selection = getUserIntInput("Which weapon do you want to equip",
                1, weapons.size() + 1);
        if(selection == weapons.size() + 1){
            return -1;
        }else {
            return selection - 1;
        }
    }

    public int equipArmor(ArrayList<Armor> armors){
        if(armors.isEmpty()){
            Console.writeLn("You do not have any extra armor in Backpack");
            return -1;
        }
        StringBuilder equipString = new StringBuilder();
        for(int i = 0; i < armors.size(); i++){
            equipString.append(i + 1);
            equipString.append(": ");
            equipString.append(armors.get(i).getItemName());
            equipString.append(", quality: ");
            equipString.append(armors.get(i).getQuality());
            equipString.append(", Durability: ");
            equipString.append(armors.get(i).getDurability());
            equipString.append("\n");
        }
        equipString.append(armors.size() + 1);
        equipString.append(": Exit");
        Console.writeLn(equipString.toString());
        int selection = getUserIntInput("Which armor do you want to Equip",
                1, armors.size() + 1);
        if(selection == armors.size() + 1){
            return -1;
        } else {
            return selection - 1;
        }
    }

    public int selectConsumable(ArrayList<Potion> consumables){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < consumables.size(); i++) {
            Potion item = consumables.get(i);
            sb.append(i + 1);
            sb.append(": ");
            sb.append(item.getItemName());
            sb.append(", Quality: ");
            sb.append(item.getQuality());
            sb.append(", Health: ");
            sb.append(item.getHealthAmount());
            sb.append("\n");
        }
        sb.append(consumables.size() + 1);
        sb.append(": Exit");
        Console.writeLn(sb.toString());
        int selection = getUserIntInput("Which would you like?",
                1, consumables.size() + 1);
        if(selection == consumables.size() + 1){
            return -1;
        }else{
            return selection - 1;
        }
    }

    public int getUserIntInput(String prompt, int lowerBound, int upperBound){
        return Console.getIntInput(prompt, lowerBound, upperBound);
    }

    public boolean getUserBoolean(String prompt){
        return Console.getBooleanInput(prompt, "yes", "no");
    }
}
