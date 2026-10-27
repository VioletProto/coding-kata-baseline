package edu.neumont.csc150.models.battles;

import edu.neumont.csc150.models.Characers.Enemy;
import edu.neumont.csc150.models.Characers.Player;

public class Battle {
    private Player player;
    private Enemy target;
    private boolean activeBattle;

    public Battle(Enemy enemy) {
        this.target = enemy;
    }

    public void SetPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return this.target;
    }

    public void LeaveBattle() {
        this.player = null;
        endBattle();
    }

    public boolean isActive() {
        return activeBattle;
    }

    public void endBattle() {
        activeBattle = false;
    }

}
