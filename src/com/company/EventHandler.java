package com.company;

import com.company.Events.BattlecryHandler;
import com.company.Events.GameEvent;

public class EventHandler {

    private BattlecryHandler battlecryHandler;

    EventHandler(GameEvent delegate){
        battlecryHandler = new BattlecryHandler(delegate);
    }

    public void triggerBattlecry(int id, int position){
        battlecryHandler.triggerBattlecry(id,position);
    }
}
