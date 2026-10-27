package edu.neumont.csc150.models.battles;

import edu.neumont.csc150.controllers.WeaponForge;
import edu.neumont.csc150.models.Characers.*;
import edu.neumont.csc150.models.items.armors.Shirt;
import edu.neumont.csc150.models.items.enums.Quality;

public class BattleMap {
    private Battle[][] battleMap;

    public BattleMap() {
        battleMap = new Battle[][]{
                {new Battle(new Thug(new Shirt(), WeaponForge.createRandomWeapon(Quality.LOW))),
                        new Battle(new Thug(WeaponForge.createRandomArmor(Quality.LOW), WeaponForge.createRandomWeapon(Quality.LOW))),
                        new Battle(new Bandit(WeaponForge.createRandomArmor(Quality.MEDIUM), WeaponForge.createRandomWeapon(Quality.MEDIUM))),
                        new Battle(new Bandit(WeaponForge.createRandomArmor(Quality.MEDIUM), WeaponForge.createRandomWeapon(Quality.MEDIUM)))},

                {new Battle(new Bandit(WeaponForge.createRandomArmor(Quality.MEDIUM), WeaponForge.createRandomWeapon(Quality.HIGH))),
                        new Battle(new CorruptGuard(WeaponForge.createRandomArmor(Quality.HIGH), WeaponForge.createRandomWeapon(Quality.MEDIUM))),
                        new Battle(new Mercenary(WeaponForge.createRandomArmor(Quality.HIGH), WeaponForge.createRandomWeapon(Quality.HIGH))),
                        new Battle(new CorruptGuard(WeaponForge.createRandomArmor(Quality.MEDIUM), WeaponForge.createRandomWeapon(Quality.HIGH)))},

                {new Battle(new Assassin(WeaponForge.createRandomArmor(Quality.HIGH), WeaponForge.createRandomWeapon(Quality.HIGH))),
                        new Battle(new Mercenary(WeaponForge.createRandomArmor(Quality.HIGH), WeaponForge.createRandomWeapon(Quality.HIGH))),
                        new Battle(new CorruptGuard(WeaponForge.createRandomArmor(Quality.LEGENDARY), WeaponForge.createRandomWeapon(Quality.HIGH))),
                        new Battle(new Assassin(WeaponForge.createRandomArmor(Quality.HIGH), WeaponForge.createRandomWeapon(Quality.LEGENDARY)))},

                {new Battle(new FinalBoss())}
        };
    }

    public Battle getBattle(int row, int col) {
        if (row < battleMap.length && col < battleMap[row].length) {
            return battleMap[row][col];
        }
        return null;
    }

    public int getTotalRows() {
        return battleMap.length;
    }

    public int getTotalCols(int row) {
        return battleMap[row].length;
    }
}
