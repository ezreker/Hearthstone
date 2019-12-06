package com.company;

public interface PlayerAction {
    void playCard(double signature, int card, int position);
    void attack(double signature, int minion1, int minion2);
    void endTurn(double signature);
}
