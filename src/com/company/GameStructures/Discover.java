package com.company.GameStructures;

import java.util.ArrayList;
import java.util.Random;

public class Discover {
    ArrayList<Card> options;

    public Discover(ArrayList<Card> cardpool, int number){

        //Determine if there are enough cards to fill
        int count = Math.min(number, cardpool.size());

        //Initialize random
        Random r = new Random();

        //Pick each card (limit duplicates when making cardpool)
        options = new ArrayList<>();
        for(int i=0;i<count;i++){
            int e = r.nextInt(cardpool.size());
            options.add(cardpool.get(e));
            cardpool.remove(e);
        }
    }

    public ArrayList<Card> getOptions(){
        return options;
    }
}
