package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Characers.Enemy;
import edu.neumont.csc150.models.Characers.Player;
import edu.neumont.csc150.models.flavorText.AttackResult;
import edu.neumont.csc150.models.items.Item;
import edu.neumont.csc150.models.items.armors.Shirt;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.models.items.potions.Potion;

import java.util.ArrayList;
import java.util.Random;

import static edu.neumont.csc150.controllers.StoryController.ui;

public class BattleController {
    public final static Random rand = new Random();
    private boolean activeBattle = true;

    public boolean getActiveBattle() {
        return activeBattle;
    }

    protected void setActiveBattle(boolean activeBattle) {
        this.activeBattle = activeBattle;
    }

    public void playerTurn(Player player, Enemy target) {
        if (!activeBattle) return;

        ui.printHR();
        ui.printNormal(player.getName() + ", Health amount: " + player.getHealth());
        int selection = ui.getUserIntInput("""
                1. Attack
                2. Use Consumable
                3. Flee
                """, 1, 3);
        switch (selection) {
            case 1 -> {
                AttackResult playerAttack = player.attack(target);
                StringBuilder sb = new StringBuilder();
                if (playerAttack.getAttackHit()) {
                    sb.append("You hit ");
                    sb.append(target.getName());
                    sb.append(" for ");
                    sb.append(playerAttack.getAmount());
                    sb.append(" damage");
                } else if (!playerAttack.getAttackHit()) {
                    sb.append("You Missed");
                }
                ui.printNormal(sb.toString());
            }
            case 2 -> useConsumable(player, target);
            case 3 -> fleeAttempt(target);
        }
        if (!target.isAlive()) {
            if (target.getStatus() == Status.BEGGING) {
                player.removeKarma(rand.nextInt(10) + 4);
            }
            lootEnemy(player, target);
            activeBattle = false;
        }
    }

    public void lootEnemy(Player player, Enemy enemy) {
        int selection = 0;
        ArrayList<Item> enemyLoot = new ArrayList<>();
        enemyLoot.add(enemy.removeWeapon());
        if (enemy.getArmor().getItemName() != new Shirt().getItemName()) {
            enemyLoot.add(enemy.removeArmor());
        }
        ui.printNormal(enemy.getName() + " had $" + enemy.getMoney() + " on them!");
        player.addMoney(enemy.removeMoney(enemy.getMoney()));
        do {
            selection = ui.LootAfterBattle(enemyLoot);
            if (selection != -1 && selection != -2) {
                player.addToBackpack(enemyLoot.get(selection));
                enemyLoot.remove(selection);
            } else if (selection == -2) {
                StoryController.dropItem();
            } else {
                break;
            }
        } while (true);
    }

    private void useConsumable(Player player, Enemy target) {
        ArrayList<Potion> consumables = new ArrayList<>();
        for (Item item : player.getBackpack()) {
            if (item instanceof Potion) {
                consumables.add((Potion) item);
            }
        }

        int selection = ui.selectConsumable(consumables);
        if (selection == -1) {
            playerTurn(player, target);
        } else {
            Potion tempPotion = consumables.get(selection);
            player.addHealth(tempPotion.heal());
            player.removeItemFromBackpack(tempPotion);
        }
    }

    private void fleeAttempt(Enemy target) {
        if (target.getStatus() != Status.BEGGING) {
            int chance = rand.nextInt(100) + 1;
            if (chance < 25) {
                ui.printNormal("You successfully fled");
                activeBattle = false;
            } else {
                ui.printNormal("Failed to escape!");
            }
        } else if (target.getStatus() == Status.BEGGING) {
            ui.printNormal(" You Fled!");
            activeBattle = false;
        }
    }
}
