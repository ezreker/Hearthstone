package com.company.Events;

import com.company.GameStructures.CardTypes.MinionCard;

public class BattlecryHandler {

    private GameEvent delegate;

    public BattlecryHandler(GameEvent delegate) {
        this.delegate = delegate;
    }

    public void triggerBattlecry(int battlecry, int position){

        switch(battlecry){
            case 0:

                MinionCard murlocScout =
                        new MinionCard("Murloc Scout",1,1,1,-1);
                delegate.summonMinion(murlocScout,position+1);
                break;

            case 1:

                delegate.drawCard(0);
                break;

            case 2:

                MinionCard boar =
                        new MinionCard("Boar",1,1,1,-1);
                delegate.summonMinion(boar,position+1);
                break;

            case 3:

                MinionCard mechanicalDragonling =
                        new MinionCard("Mechanical Dragonling",1,2,1,-1);
                delegate.summonMinion(mechanicalDragonling,position+1);
        }

    }
}
