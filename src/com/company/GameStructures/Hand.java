package com.company.GameStructures;

import com.company.GameStructures.CardTypes.*;
import com.company.Player;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> cards){
        this.cards = cards;
    }

    public void play(int card, int position, Player player){
        Card c = cards.get(card);
        if(c.getType() == 1) {

        } else {

        }
        cards.remove(card);
    }

    public Card getCard(int card){
        return cards.get(card);
    }

    public ArrayList<Card> getCards(){
        return cards;
    }

    public void remove(int card){
        cards.remove(card);
    }

    public int size() {
        return this.cards.size();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
