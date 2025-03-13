package org.example.charactermanagementsystem_ij;

public class Archer extends Hero {
    private String weapon = "STEEL HEAD";
    private String defence = "QUICK FEET";

    public Archer(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower) {
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
        return getHeroName() + " shoots a "+weapon+" arrow!";
    }
    @Override
    public String defenceDescription() {
        return getHeroName() + " dodges the attack using "+defence;
    }
}
