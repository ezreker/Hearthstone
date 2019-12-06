package com.company;

import com.company.GameStructures.*;

public class Player{
    private Deck deck;
    private Hand hand;
    private Hero hero;
    private int fatigue;
    private int mana;
    private int maxmana;

    public double getSignature() {
        return signature;
    }

    private double signature;

    public Player(Deck deck, int hero){
        this.deck = deck;
        this.hero = new Hero(hero,30);
        this.fatigue = 0;
        this.mana = 0;
        this.maxmana = 0;
        this.signature = Math.random();
    }

    public Hand getHand(){
        return this.hand;
    }

    public void deal(int playernumber){
        if(playernumber == 1)
            hand = new Hand(new Discover(deck.getDeck(),3).getOptions());
        else hand = new Hand(new Discover(deck.getDeck(), 4).getOptions());
    }

    public void draw(){
        if(deck.isEmpty()){
            //fatigue
        } else {
            hand.addCard(deck.removeTop());
        }
    }

    public int getMana(){
        return mana;
    }

    public int getMaxMana(){
        return maxmana;
    }

    public int getDeckSize() {
        return deck.getDeck().size();
    }

    public void removeMana(int cost){
        this.mana -= cost;
    }

    public void startTurn() {
        draw();
        if(maxmana < 10) maxmana++;
        mana = maxmana;
    }

    public void endTurn() {

    }
}
