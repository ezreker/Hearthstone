package com.company;

import com.company.GameStructures.*;
import com.company.Client.GameFrame;
import com.company.GameStructures.CardTypes.MinionCard;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Card> deck1 = new ArrayList<>();
        deck1.add(new MinionCard("Chillwind Yeti",4,4,4,5,5));
        deck1.add(new MinionCard("War Golem",7,7,7,7,7));
        deck1.add(new MinionCard("Wisp",0,1,1,1,1));
        deck1.add(new MinionCard("Boulderfist Ogre",6,6,6,7,7));
        deck1.add(new MinionCard("Ultrasaur",10,7,7,14,14));
        deck1.add(new MinionCard("Pit Fighter",5,5,5,6,6));

        ArrayList<Card> deck2 = new ArrayList<>();
        deck2.add(new MinionCard("Captured Jormungar",7,5,5,9,9));
        deck2.add(new MinionCard("Am'Gam Rager",3,1,1,5,5));
        deck2.add(new MinionCard("Eldritch Horror",8,6,6,10,10));
        deck2.add(new MinionCard("Faceless Behemoth",10,10,10,10,10));
        deck2.add(new MinionCard("Worgen Greaser",4,6,6,3,3));

        Deck p1deck = new Deck(deck1);
        Deck p2deck = new Deck(deck2);
        Player player1 = new Player(p1deck,0);
        Player player2 = new Player(p2deck,0);

        Game game = new Game(player1,player2);
        GameFrame client = new GameFrame(player1.getSignature());
        System.out.println(player1.getSignature());
        game.setDelegate(client);
        client.setDelegate(game);
    }
}
