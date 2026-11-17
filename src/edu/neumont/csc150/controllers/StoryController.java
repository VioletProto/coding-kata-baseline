package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Characers.Enemy;
import edu.neumont.csc150.models.Characers.Player;
import edu.neumont.csc150.models.Trader;
import edu.neumont.csc150.models.battles.Battle;
import edu.neumont.csc150.models.battles.BattleMap;
import edu.neumont.csc150.models.battles.BattleOutcome;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.armors.Armor;
import edu.neumont.csc150.models.items.enums.Quality;
import edu.neumont.csc150.models.items.potions.Potion;
import edu.neumont.csc150.models.items.weapons.Weapon;
import edu.neumont.csc150.views.UI;

import java.util.ArrayList;

public class StoryController {
    private MusicController musicPlayer;
    public static final UI ui = new UI();
    private static Player playerCharacter;
    private int traderIteration = 1;
    private int battleIteration = 0;
    private static Trader trader;
    private BattleController battleController;
    private BattleMap battleMap;
    private BattleFacade battleFacade;
    private int currentRow = 0;
    private int currentCol = 0;

    public void run()  {
        battleController = new BattleController();
        musicPlayer = new MusicController("assets/battle-music.wav");
        battleFacade = new BattleFacade(battleController,ui, musicPlayer);
        battleMap = new BattleMap();
//        ui.startingText();
        playerCharacter = new Player(ui.getString("Enter a name"));
        playerCharacter.addToBackpack(new Potion(Quality.MEDIUM));
        playerCharacter.addToBackpack(new Potion(Quality.MEDIUM));
        visitTrader();

        while (currentRow < battleMap.getTotalRows()) {
            startNextBattle();
            if (!playerCharacter.isAlive()) {
                ui.printSlowly("Game Over!", 500);
                break;
            }
        }
    }

    private void startNextBattle() {
        Battle nextBattle = battleMap.getBattle(currentRow, currentCol);

        if (nextBattle != null) {
            Enemy enemy = nextBattle.getEnemy();
            startBattle(enemy);

            currentCol++;
            if (currentCol >= battleMap.getTotalCols(currentRow)) {
                currentCol = 0;
                currentRow++;
            }
        }
    }

    public void startBattle(Enemy enemy) {
        BattleOutcome outcome = battleFacade.fight(playerCharacter, enemy);

        if (outcome == BattleOutcome.VICTORY) {
            battleIteration++;

            if (battleIteration % 4 == 0) {
                traderIteration++;
                visitTrader();
            }
        }

        if (outcome == BattleOutcome.VICTORY || outcome == BattleOutcome.FLED) {
            inBetweenMenu();
        }

    }

    public void inBetweenMenu(){
        ArrayList<Weapon> weapons = new ArrayList<>();
        ArrayList<Armor> armors = new ArrayList<>();
        int selection = 0;
        for (Item item: playerCharacter.getBackpack()) {
            if(item instanceof Weapon){
                weapons.add((Weapon) item);
            } else if (item instanceof Armor){
                armors.add((Armor) item);
            }
        }
        do {
            selection = ui.getUserIntInput("""
                    1. Equip Weapon
                    2. Equip Armor
                    3. Exit
                    """, 1, 3);
            switch (selection) {
                case 1 -> {
                    int weaponSelection = ui.equipWeapon(weapons);
                    if (weaponSelection != -1) {
                        playerCharacter.removeItemFromBackpack(weapons.get(weaponSelection));
                        playerCharacter.equipWeapon(weapons.get(weaponSelection));
                        weapons.remove(weaponSelection);
                    } else {
                        break;
                    }
                }
                case 2 -> {
                    int armorSelection = ui.equipArmor(armors);
                    if (armorSelection != -1) {
                        playerCharacter.removeItemFromBackpack(armors.get(armorSelection));
                        playerCharacter.equipArmor(armors.get(armorSelection));
                        armors.remove(armorSelection);
                    } else {
                        break;
                    }
                }
                case 3 -> {
                    return;
                }
            }
        }while (true);
    }

    public static void dropItem(){
        int selection = 0;
        ArrayList<Item> tempBackpack = new ArrayList<>();
        for(Item item: playerCharacter.getBackpack()){
            if(item == null){
                break;
            }
            tempBackpack.add(item);
        }
        do{
            selection = ui.selectItemToDrop(tempBackpack);
            if(selection != -1){
                playerCharacter.removeItemFromBackpack(tempBackpack.get(selection));
                tempBackpack.remove(selection);
            }else {
                break;
            }
        }while (true);
    }


    public void visitTrader() {
        int selection = 0;

        switch (traderIteration) {
            case 1 -> trader = new Trader(Quality.LOW);
            case 2 -> trader = new Trader(Quality.MEDIUM);
            case 3 -> trader = new Trader(Quality.HIGH);
            case 4 -> trader = new Trader(Quality.LEGENDARY);
        }
        do {
            selection = ui.traderMenu(trader, playerCharacter);
            if (selection != -1 && selection != -2) {
                int itemCost = trader.getItemCost(selection);
                if (!playerCharacter.canAffordItem(itemCost)) {
                    ui.printNormal("You cannot afford this item!");
                } else if (playerCharacter.canAffordItem(itemCost)) {
                    Item item = trader.buyItem(selection);
                    playerCharacter.removeMoney(itemCost);
                    playerCharacter.addToBackpack(item);
                }
            } else if(selection == -2){
                ArrayList<Item> tempBackpack = new ArrayList<>();
                for(Item item: playerCharacter.getBackpack()){
                    if(item == null){
                        break;
                    }
                    tempBackpack.add(item);
                }
                int selectionTwo = ui.selectItemToDrop(tempBackpack);
                if(selectionTwo != -1) {
                    int sellCost = trader.sellItem(tempBackpack.get(selectionTwo));
                    tempBackpack.remove(selectionTwo);
                    playerCharacter.removeItemFromBackpack(tempBackpack.get(selectionTwo));
                    playerCharacter.addMoney(sellCost);
                }
            }else {
                break;
            }
        } while (true);
    }

    public void startBattleMusic() {
        if (musicPlayer == null) {
            musicPlayer = new MusicController("assets/battle-music.wav");
        }
        musicPlayer.play();
    }

    public void endBattleMusic() {
        if (musicPlayer != null) {
            musicPlayer.stop();
        }
    }
}


