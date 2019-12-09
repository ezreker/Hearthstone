package com.company.GameStructures.CardTypes;

import com.company.GameStructures.Card;

public class MinionCard extends Card {

    public static int type = 1;

    private String name;
    private int cost;
    private int health, maxhealth, basehealth;
    private int attack, baseattack;
    private int battlecry;
    private boolean sleeping;

    public MinionCard(String name, int cost, int attack, int health, int battlecry){
        this.name = name;
        this.cost = cost;
        this.attack = attack;
        this.baseattack = attack;
        this.health = health;
        this.maxhealth = health;
        this.basehealth = health;
        this.battlecry = battlecry;
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

    @Override
    public int getCost(){
        return cost;
    }

    @Override
    public int getType() {
        return 1;
    }

    public int getBattlecry(){
        return battlecry;
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
