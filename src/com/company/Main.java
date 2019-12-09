package com.company;

import com.company.GameStructures.*;
import com.company.Client.GameFrame;

public class Main {

    public static void main(String[] args) {

        int[] deck1 = {1,2,3,4,9,10,11,12};
        int[] deck2 = {5,6,7,8,13,14,0};

        DeckBuilder deckBuilder = new DeckBuilder(deck1, deck2);

        Deck p1deck = new Deck(deckBuilder.getDeck(1));
        Deck p2deck = new Deck(deckBuilder.getDeck(2));
        Player player1 = new Player(p1deck,0);
        Player player2 = new Player(p2deck,0);

        Game game = new Game(player1,player2);
        GameFrame client = new GameFrame(player1.getSignature());
        System.out.println(player1.getSignature());

        game.setDelegate(client);
        client.setDelegate(game);
    }
}
