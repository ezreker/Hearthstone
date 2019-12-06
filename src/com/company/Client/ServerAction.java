package com.company.Client;

import com.company.GameStructures.Board;
import com.company.Player;

import java.util.ArrayList;

public interface ServerAction {
    public void sendBoardState(String boardState);
}
