package org.example.charactermanagementsystem_ij;

public abstract class Hero
{
    //hero attributes
protected String heroName;
protected String heroClass;
protected int healthPoints;
protected int defenceLevel;
protected int attackPower;
protected int experiencePoints;

protected abstract String attackDescription();
protected abstract String defenceDescription();


public Hero(String heroName, String heroClass, int healthPoints, int defenceLevel, int attackPower)
{
    this.heroName=heroName;
    this.heroClass=heroClass;
    this.healthPoints=healthPoints;
    this.defenceLevel=defenceLevel;
    this.attackPower=attackPower;
    this.experiencePoints=0;
}

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(String heroClass) {
        this.heroClass = heroClass;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getDefenceLevel() {
        return defenceLevel;
    }

    public void setDefenceLevel(int defenceLevel) {
        this.defenceLevel = defenceLevel;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

}
