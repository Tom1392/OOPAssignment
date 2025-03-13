package org.example.charactermanagementsystem_ij;

public class Mage extends Hero {
    private String weapon = "FIRE";
    private String defence = "MAGIC SHIELD";

    public Mage(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower) {
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
        return getHeroName() + " casts a powerful "+weapon+" spell!";
    }

    @Override
    public String defenceDescription() {
        return getHeroName() + " Blocks the attack with a " + defence + " spell!";
    }
}