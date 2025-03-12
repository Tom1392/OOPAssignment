package org.example.charactermanagementsystem_ij;

public class Archer extends Hero
{
    private final String weapon="Bow and Arrow";

    public Archer(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower)
    {
        super(heroName,heroClass,healthPoints,defenceLevel,attackPower);
    }

    public String getWeapon() {
        return weapon;
    }
}
