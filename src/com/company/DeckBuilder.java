package com.company;

import com.company.GameStructures.Card;
import com.company.GameStructures.CardTypes.MinionCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class DeckBuilder {

    private ArrayList<Card> deck1, deck2;
    private Hashtable<Integer,String[]> cardsTable;

    DeckBuilder(int[] d1, int[] d2) {

        cardsTable = new Hashtable<>();
        deck1 = new ArrayList<>();
        deck2 = new ArrayList<>();

        File cardsFile = new File(System.getProperty("user.dir")
                        + "/src/com/company/Cards/cards.csv");

        try {

            Scanner scanner = new Scanner(cardsFile);
            scanner.nextLine();

            while(scanner.hasNext()){
                String[] currentCard = scanner.nextLine().split(",");
                cardsTable.put(Integer.valueOf(currentCard[0]),currentCard);
            }

        } catch(FileNotFoundException e) {

            System.out.println("File not found");

        }

        for(int card : d1){
            deck1.add(createCard(card));
        }
        for(int card : d2){
            deck2.add(createCard(card));
        }
    }

    private Card createCard(int card){
        String[] currentCard = cardsTable.get(card);

        String name = currentCard[1];
        int cost = Integer.valueOf(currentCard[2]);
        int attack = Integer.valueOf(currentCard[3]);
        int health = Integer.valueOf(currentCard[4]);

        return new MinionCard(name,cost,attack,attack,health,health);
    }

    public ArrayList<Card> getDeck(int deck){
        return deck == 1 ? deck1 : deck2;
    }
}
