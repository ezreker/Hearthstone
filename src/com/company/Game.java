package com.company;

import com.company.Client.ServerAction;
import com.company.Events.GameEvent;
import com.company.GameStructures.*;
import com.company.GameStructures.CardTypes.MinionCard;

import java.util.ArrayList;

public class Game implements PlayerAction, GameEvent {

    private ArrayList<Player> players;
    private Board board;
    private EventHandler eventHandler;
    private int active;
    private double p1sig, p2sig;
    private ServerAction delegate;

    public Game(Player player1, Player player2){

        players = new ArrayList<>();

        active = 0;
        p1sig = player1.getSignature();
        p2sig = player2.getSignature();

        players.add(player1);
        players.add(player2);
        board = new Board();
        eventHandler = new EventHandler(this);

        player1.deal(1);
        player2.deal(2);

        player1.startTurn();
    }

    public void setDelegate(ServerAction delegate){
        this.delegate = delegate;
        sendBoardState();
    }

    private String translate(Board board, ArrayList<Player> players){

        StringBuilder sb = new StringBuilder();

        //encode board
        for(int i=0;i<2;i++) {
            for (MinionCard minion : board.getBoard(i)) {
                sb.append(minion.getClientTranslation() + "$");
            }
            sb.append("%");
        }

        //encode hands
        for(Player player : players){
            for(Card card : player.getHand().getCards()){
                sb.append(card.getClientTranslation() + "$");
            }
            sb.append("%");
        }

        //encode mana
        for(Player player : players){
            sb.append(player.getMana() + "$");
            sb.append(player.getMaxMana() + "%");
        }

        //encode decks
        for(Player player : players){
            sb.append(player.getDeckSize() + "%");
        }

        //encode healths
        for(Player player : players){
            sb.append(player.getHealth() + "%");
        }

        return sb.toString();
    }

    @Override
    public void playCard(double signature, int c, int p) {

        Player activePlayer = players.get(active);
        int card = c%10;
        int position = p%10;

        Card cardPlayed = activePlayer.getHand().getCard(card);

        int playerHand = c < 10 ? 0 : 1;
        int boardHand = p < 10 ? 0 : 1;

        if(playerHand == boardHand
                && playerHand == active
//                && signature == (active == 0 ? p1sig : p2sig)
        ) {
            if(cardPlayed.getCost() > activePlayer.getMana()){

                System.out.println("Not enough mana");

            } else {

                activePlayer.getHand().remove(card);
                activePlayer.removeMana(cardPlayed.getCost());
                board.summon(active, position, (MinionCard) cardPlayed);
                sendBoardState();

            }

        }
    }

    @Override
    public void attack(double signature, int m1, int m2){

        int attackingPlayer = m1<10 ? 0:1;

        if(attackingPlayer == active) {

            if(m2 >= 20){

                MinionCard attackingMinion = board.getBoard(attackingPlayer)[m1 % 10];
                Player player = players.get(m2-21);

                if (!attackingMinion.isSleeping()) {

                    player.damage(attackingMinion.getAttack());

                    if (player.isDead()) endGame();

                    sendBoardState();
                }

            } else {

                MinionCard attackingMinion = board.getBoard(attackingPlayer)[m1 % 10];
                MinionCard defendingMinion = board.getBoard((attackingPlayer + 1) % 2)[m2 % 10];

                if (!attackingMinion.isSleeping()) {

                    int attack1 = attackingMinion.getAttack();
                    int attack2 = defendingMinion.getAttack();

                    attackingMinion.damage(attack2);
                    defendingMinion.damage(attack1);

                    board.triggerDeaths();

                    sendBoardState();

                } else System.out.println("This minion can't attack yet");
            }

        } else System.out.println("Wrong player's turn");
    }

    @Override
    public void endTurn(double signature) {

        players.get(active).endTurn();
        board.endTurn();

        active = (active+1)%2;

        players.get(active).startTurn();

        sendBoardState();

    }

    private void sendBoardState(){
        delegate.sendBoardState(translate(board, players));
    }

    private void endGame(){
        System.out.println("Game Over");
    }
}
