package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Characers.Enemy;
import edu.neumont.csc150.models.Characers.Player;
import edu.neumont.csc150.models.battles.BattleOutcome;

public interface BattleEncounter {
    BattleOutcome fight(Player player, Enemy enemy);
}
