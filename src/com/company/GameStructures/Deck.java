package com.company.GameStructures;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private ArrayList<Card> cards;

    public Deck(ArrayList<Card> cards){
        this.cards = cards;
    }

    public boolean isEmpty(){
        return cards.isEmpty();
    }

    public ArrayList<Card> getDeck(){
        return cards;
    }

    public Card removeTop(){
        Card out = this.cards.get(0);
        this.cards.remove(0);
        return out;
    }

    public Card checkTop(){
        return this.cards.get(0);
    }


    public void shuffle(){
        ArrayList<Card> newdeck = new ArrayList<>();
        while(!cards.isEmpty()){
            Random r = new Random();
            int e = r.nextInt(cards.size());
            newdeck.add(cards.get(e));
            cards.remove(e);
        }
        cards = newdeck;
    }
}
