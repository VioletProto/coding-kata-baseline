package edu.neumont.csc150.models.flavorText;

public class AttackResult {
    private int amount;
    private boolean attackHit;
    private boolean isAlive;
    private String attacker;
    private String defender;


    public AttackResult(int amount, boolean attackHit, String attacker, String defender, boolean isAlive) {
        setAmount(amount);
        setAttackHit(attackHit);
        setAttacker(attacker);
        setDefender(defender);
        setAlive(isAlive);
    }

    //region Getter/Setters

    public int getAmount() {
        return amount;
    }

    private void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean getAttackHit() {
        return attackHit;
    }

    private void setAttackHit(boolean attackHit) {
        this.attackHit = attackHit;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getAttacker() {
        return attacker;
    }

    private void setAttacker(String attacker) {
        if(attacker == null)
            throw new NullPointerException("attacker was null for some reason");
        this.attacker = attacker;
    }

    public String getDefender() {
        return defender;
    }

    private void setDefender(String defender) {
        if(defender == null)
            throw new NullPointerException("defender was null for some reason");
        this.defender = defender;
    }

    //endregion

}
