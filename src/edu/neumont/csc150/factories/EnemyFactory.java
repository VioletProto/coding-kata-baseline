package edu.neumont.csc150.factories;

import edu.neumont.csc150.models.Characers.*;

public class EnemyFactory {

    public Assassin createAssassin(EquipmentFactory equipment){
        return new Assassin(
                equipment.createArmor(),
                equipment.createWeapon()
        );
    }

    public Bandit createBandit(EquipmentFactory equipment){
        return new Bandit(
                equipment.createArmor(),
                equipment.createWeapon()
        );
    }

    public CorruptGuard createCorruptGuard(EquipmentFactory equipment){
        return new CorruptGuard(
                equipment.createArmor(),
                equipment.createWeapon()
        );
    }

    public Mercenary createMercenary(EquipmentFactory equipment){
        return new Mercenary(
                equipment.createArmor(),
                equipment.createWeapon()
        );
    }

    public Thug createThug(EquipmentFactory equipment){
        return new Thug(
                equipment.createArmor(),
                equipment.createWeapon()
        );
    }

}
