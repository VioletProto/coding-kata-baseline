package edu.neumont.csc150.controllers;

import edu.neumont.csc150.models.Characers.Enemy;
import edu.neumont.csc150.models.Characers.Player;
import edu.neumont.csc150.models.battles.BattleOutcome;

public class MusicBattleDecorator implements BattleEncounter {
    private final BattleEncounter encounter;
    private final MusicController music;

    public MusicBattleDecorator(BattleEncounter encounter, MusicController music){
        this.encounter = encounter;
        this.music = music;
    }

    @Override
    public BattleOutcome fight(Player player, Enemy enemy){
        try{
            music.play();
            return encounter.fight(player, enemy);
        } finally {
            music.stop();
        }
    }

}
