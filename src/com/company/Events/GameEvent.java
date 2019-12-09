package com.company.Events;

import com.company.GameStructures.CardTypes.MinionCard;

public interface GameEvent {

    void drawCard(int player);
    void summonMinion(MinionCard minion,int position);

}
