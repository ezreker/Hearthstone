package com.company.GameStructures;

import com.company.GameStructures.CardTypes.MinionCard;

import java.util.ArrayList;

public class Board {

    private ArrayList<MinionCard> minions;
    private ArrayList<Integer> positions;
    private int[] minionCounts;

    public Board(){
        minions = new ArrayList<>();
        positions = new ArrayList<>();
        minionCounts = new int[]{0, 0};
    }

    public void summon(int player, int position, MinionCard minion){

        if(minionCounts[player] < 7) {
            position += (player*10);
            for (int i = 0; i < minions.size(); i++) {

                int x = positions.get(i);
                if (x >= position && x < (player+1) * 10)
                    positions.set(i, x + 1);

            }

            minion.setSleeping(true);
            minionCounts[player]++;
            minions.add(minion);
            positions.add(position);

        }
    }

    public MinionCard[] getBoard(int i){

        int boardSize = minionCounts[i];
        MinionCard[] cards = new MinionCard[boardSize];

        int j = 0;
        int k = 0;
        while(j<boardSize){
            int pos = positions.get(k);
            if(pos >= i*10 && pos < (i+1)*10) {
                cards[pos-(i*10)] = minions.get(k);
                j++;
                k++;
            } else {
                k++;
            }
        }

        return cards;
    }

    public void triggerDeaths(){

        int i=0;
        while(i < minions.size()) {

            int position = positions.get(i);

            if(minions.get(i).getHealth() <= 0) {

                for (int j = 0; j < minions.size(); j++) {

                    int x = positions.get(j);
                    if (x > position && x < ((position / 10)+1) * 10)
                        positions.set(j, x - 1);

                }

                minionCounts[position / 10]--;
                positions.remove(i);
                minions.remove(i);

            } else {
                i++;
            }
        }
    }

    public ArrayList<MinionCard> getMinions(){
        return this.minions;
    }

    public void endTurn(){
        for (MinionCard minion : minions){
            minion.setSleeping(false);
        }
    }

}
