package com.company.GameStructures;

public abstract class Card {
    private int cost;
    private String name;
    public static int type;

    public int getCost(){
        return cost;
    }

    public int getType(){
        return type;
    }

    public String getName() {
        return name;
    }

    public abstract String getClientTranslation();
}
