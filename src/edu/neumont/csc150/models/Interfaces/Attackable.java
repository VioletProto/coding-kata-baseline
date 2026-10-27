package edu.neumont.csc150.models.Interfaces;

import edu.neumont.csc150.models.flavorText.AttackResult;
import edu.neumont.csc150.models.items.enums.DamageType;

import java.util.Random;

public interface Attackable {
    AttackResult attack(Attackable target);

    AttackResult takeDamage(int amount, DamageType type, boolean hitOrMiss, String attacker, String defender);

    boolean isAlive();
    static int rollDice(int numDice, int numberOfSidesOnDie) {
        Random rand = new Random();
        int rolls = 0;
        for (int i = 0; i < numDice; i++) {
            rolls += rand.nextInt((numberOfSidesOnDie) + 1);
        }
        return rolls;
    }
}
