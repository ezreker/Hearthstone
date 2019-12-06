package com.company.GameStructures.CardTypes;

import com.company.GameStructures.Card;

public class MinionCard extends Card {

    public static int type = 1;

    private String name;
    private int cost;
    private int health, maxhealth, basehealth;
    private int attack, baseattack;
    private boolean sleeping;

    public MinionCard(String name, int cost, int attack, int baseattack, int maxhealth, int basehealth){
        this.name = name;
        this.cost = cost;
        this.attack = attack;
        this.baseattack = baseattack;
        this.health = maxhealth;
        this.maxhealth = maxhealth;
        this.basehealth = basehealth;
        this.sleeping = false;
    }

    public void damage(int amount){
        health -= amount;
        if(health <= 0);
    }

    public int getAttack(){
        return attack;
    }

    public int getHealth(){
        return health;
    }

    public int getCost(){
        return cost;
    }

    public void setSleeping(boolean s){
        sleeping = s;
    }

    public boolean isSleeping(){
        return sleeping;
    }

    public String getClientTranslation(){
        String output = name + "|"
                + cost + "|"
                + attack + "|"
                + baseattack + "|"
                + health + "|"
                + maxhealth + "|"
                + basehealth;
        return output;
    }
}
