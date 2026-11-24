package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Characers.Enemy;
import edu.neumont.csc150.models.Characers.Player;
import edu.neumont.csc150.models.battles.BattleOutcome;
import edu.neumont.csc150.models.flavorText.AttackResult;
import edu.neumont.csc150.models.items.enums.Status;
import edu.neumont.csc150.views.UI;

public class BattleFacade implements BattleEncounter {
    private final BattleController battleController;
    private final UI ui;

    public BattleFacade(BattleController battleController, UI ui){
        this.battleController = battleController;
        this.ui = ui;
    }

    public BattleOutcome fight(Player player, Enemy enemy){
        ui.printNormal("A battle begins against " + enemy.getName()
                + "(Health: " + enemy.getHealth() + ")");

        battleController.setActiveBattle(true);

        while (player.isAlive() && enemy.isAlive()) {
            battleController.playerTurn(player, enemy);
            if (!enemy.isAlive()) {
                ui.printNormal(enemy.getName() + " has been defeated!");
                return BattleOutcome.VICTORY;
            }

            if (!battleController.getActiveBattle()) {
                return BattleOutcome.FLED;
            }

            AttackResult enemyAttack = enemy.attack(player);

            if (enemy.getStatus() == Status.BEGGING) {
                ui.printNormal("Please let me go");
            } else if (enemyAttack.getAttackHit()) {
                ui.printNormal(enemy.getName() + " Attacks you for "
                        + enemyAttack.getAmount() + " damage");
            } else {
                ui.printNormal(enemy.getName() + " misses their attack");
            }
        }
        return BattleOutcome.DEFEAT;
    }

}
