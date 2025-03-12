package org.example.charactermanagementsystem_ij;

public class Mage extends Hero
{
    private final String weapon="Cast Spell";

    public Mage(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower)
    {
        super(heroName,heroClass,healthPoints,defenceLevel,attackPower);
    }
    public String getWeapon() {
        return weapon;
    }
}