package com.company.Client.Pieces;

public class MinionCard extends Card{

    String name;
    int cost;
    int attack;
    int health;

    public MinionCard(String name, int cost, int attack, int health){
        this.name = name;
        this.cost = cost;
        this.attack = attack;
        this.health = health;
    }

    @Override
    public String getCover() {
        String output = "<html>" + name
                +"</html>";
        return output;
    }

    @Override
    public String getText() {
        String output = "<html>" + name
                + "<br/>Cost: " + cost
                + "<br/>Attack: " + attack
                + "<br/>Health: " + health
                + "</html>";
        return output;
    }
}
