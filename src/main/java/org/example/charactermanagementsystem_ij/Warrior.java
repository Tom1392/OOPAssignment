package org.example.charactermanagementsystem_ij;

public class Warrior extends Hero {
    private String weapon = "STEEL";
    private String defence = "IRON";
    public Warrior(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower) {
        super(heroName, heroClass, healthPoints, defenceLevel, attackPower);
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public void setDefence(String defence) {
        this.defence = defence;
    }
    @Override
    public String attackDescription() {
        return getHeroName() + " swings a mighty "+weapon+" sword!";
    }

    @Override
    public String defenceDescription() {
        return getHeroName() + " deflects the attack with a "+defence+" shield!";
    }
}