package org.example.charactermanagementsystem_ij;

public class Warrior extends Hero
{
    private final String weapon="Sword";

    public Warrior(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower)
    {
        super(heroName,heroClass,healthPoints,defenceLevel,attackPower);
    }

    public String getWeapon() {
        return weapon;
    }
}