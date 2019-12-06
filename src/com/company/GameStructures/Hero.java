package com.company.GameStructures;

public class Hero {
    private int hero;
    private int health;
    private int maxhealth;
    private int armor;
    private int attack;

    public Hero(int hero, int health){
        this.hero = hero;
        this.health = health;
        this.maxhealth = health;
        this.armor = 0;
    }

    public void hit(int amount){

    }
}
