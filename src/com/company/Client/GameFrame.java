package com.company.Client;

import com.company.Client.Pieces.Card;
import com.company.Client.Pieces.MinionCard;
import com.company.PlayerAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameFrame extends JFrame implements ServerAction {

    private static int DIM_CONST_X = 1000;
    private static int DIM_CONST_Y = 500;

    private static int CARD_CONST_X = 100;
    private static int CARD_CONST_Y = 60;

    private static int INFO_CONST_X = 200;
    private static int INFO_CONST_Y = 150;

    private GridBagConstraints c = new GridBagConstraints();
    private JPanel panel;

    private JLabel weapon1, weapon2;
    private JButton hero1, hero2;
    private JButton heroPower1, heroPower2;

    private JLabel deck1, deck2;
    private int deckSize1, deckSize2;

    private JLabel mana1, mana2;
    private int m1, m2, maxm1, maxm2;

    private JPanel hand1, hand2;
    private ArrayList<Card> handButtons1, handButtons2;

    private JPanel board1, board2;
    private ArrayList<MinionCard> boardButtons1, boardButtons2;
    private JButton endTurn;

    private JPanel cardInfo;
    private JLabel cardInfoLabel;

    private JButton actionButton;

    private int selectedCard, selectedBoardSlot;

    private boolean actionSelected;

    PlayerAction delegate;
    double signature;

    private void addToPanel(Component component, int x, int y, int width, int height){
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = 1;
        panel.add(component,c);
        actionSelected = false;
    }

    private void updateSelectedCard(){
        cardInfo.removeAll();
        cardInfoLabel = new JLabel();

        if(selectedCard > -1) {

            cardInfoLabel.setText(selectedCard < 10  ?
                    handButtons1.get(selectedCard).getText()
                    : handButtons2.get(selectedCard-10).getText());

        } else if(selectedBoardSlot > -1) {

            cardInfoLabel.setText(selectedBoardSlot < 10 ?
                    boardButtons1.get(selectedBoardSlot).getText()
                    : boardButtons2.get(selectedBoardSlot-10).getText());

        } else {

            cardInfoLabel.setText("No info available");

        }

        cardInfo.add(cardInfoLabel);

        revalidate();
        repaint();
    }

    public void setDelegate(PlayerAction delegate){
        this.delegate = delegate;
    }

    private void boardButtonClicked(ActionEvent e){
        int boardSlot = (Integer)((JButton)e.getSource()).getClientProperty( "slot" );
        if(actionSelected == true){
            if(selectedCard > -1) {
                delegate.playCard(signature, selectedCard, boardSlot);
                selectedCard = -1;
                updateSelectedCard();
            } else if(selectedBoardSlot > -1){
                delegate.attack(signature, selectedBoardSlot, boardSlot);
                selectedBoardSlot = -1;
                updateSelectedCard();
            }
            actionSelected = false;
        } else if(boardSlot%10 == (boardSlot<10 ? boardButtons1.size() : boardButtons2.size())) {
            nullSelected(e);
        } else {
            selectedBoardSlot = boardSlot;
            selectedCard = -1;
            updateSelectedCard();
        }
    }

    private void cardSelected(ActionEvent e){
        selectedCard = (Integer)((JButton)e.getSource()).getClientProperty("card");
        updateSelectedCard();
    }

    private void nullSelected(ActionEvent e){
        actionSelected = false;
        selectedCard = -1;
        selectedBoardSlot = -1;
        updateSelectedCard();
    }

    private void endTurnButton(ActionEvent e){
        delegate.endTurn(signature);
    }

    private void confirmButton(ActionEvent e){
        if(selectedCard > -1 || selectedBoardSlot > -1) actionSelected = true;
    }

    public GameFrame(double signature1){

        System.out.println(signature1);

        this.signature = signature1;

        setSize(DIM_CONST_X,DIM_CONST_Y);
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        hand1 = new JPanel();
        hand1.setLayout(new GridLayout(1,10));
        addToPanel(hand1,0,0,5,1);

        hand2 = new JPanel();
        hand2.setLayout(new GridLayout(1,10));
        addToPanel(hand2,0,5,5,1);

        weapon1 = new JLabel("<html>Attack: 1<br/>Durability: 4</html>");
        addToPanel(weapon1, 0,1,1,1);

        weapon2 = new JLabel("<html>Attack: 3<br/>Durability: 2</html>");
        addToPanel(weapon2, 0,4,1,1);

        hero1 = new JButton("<html>Paladin<br/>Health: 30</html>");
        addToPanel(hero1,1,1,1,1);

        hero2 = new JButton("<html>Warrior<br/>Health: 30</html>");
        addToPanel(hero2,1,4,1,1);

        heroPower1 = new JButton("<html>Reinforce<br/>Cost: 2</html>");
        addToPanel(heroPower1,2,1,1,1);

        heroPower2 = new JButton("<html>Armor Up!<br/>Cost: 2</html>");
        addToPanel(heroPower2,2,4,1,1);

        deck1 = new JLabel("<html>Cards: 30</html>");
        addToPanel(deck1,3,1,1,1);

        deck2 = new JLabel("<html>Cards: 30</html>");
        addToPanel(deck2,3,4,1,1);

        mana1 = new JLabel("<html>Mana: 0/0</html>");
        addToPanel(mana1,4,1,1,1);

        mana2 = new JLabel("<html>Mana: 0/0</html>");
        addToPanel(mana2,4,4,1,1);

        board1 = new JPanel();
        board1.setLayout(new GridLayout(1,7));
        addToPanel(board1,0,2,4,1);

        board2 = new JPanel();
        board2.setLayout(new GridLayout(1,7));
        addToPanel(board2,0,3,4,1);

        boardButtons1 = new ArrayList<>();
        boardButtons2 = new ArrayList<>();
        for(int i=0;i<8;i++){
            JButton b1 = new JButton();
            b1.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            JButton b2 = new JButton();
            b2.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            board1.add(b1);
            board2.add(b2);
        }

        endTurn = new JButton("End Turn");
        endTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endTurnButton(e);
            }
        });
        addToPanel(endTurn,4,2,1,1);

        cardInfo = new JPanel();
        cardInfo.setPreferredSize(new Dimension(INFO_CONST_X,INFO_CONST_Y));
        cardInfoLabel = new JLabel("No info available");
        cardInfo.add(cardInfoLabel);
        addToPanel(cardInfo,0,6,1,1);

        actionButton = new JButton("Confirm Action");
        actionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmButton(e);
            }
        });
        addToPanel(actionButton,1,6,1,1);

        handButtons1 = new ArrayList<>();
        handButtons2 = new ArrayList<>();
        for(int i=0;i<10;i++){
            JButton b1 = new JButton();
            b1.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            JButton b2 = new JButton();
            b2.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            hand1.add(b1);
            hand2.add(b2);
        }

        selectedCard = -1;

        setContentPane(panel);
        setVisible(true);

        refresh();
    }

    private void refresh(){
        int count;

        //update hand1
        hand1.removeAll();
        count = 10;
        for(Card card : handButtons1) {
            JButton b = new JButton(card.getCover());
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.putClientProperty("card",10-count);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    cardSelected(e);
                }
            });
            hand1.add(b);
            count--;
        }
        for(int i=0;i<count;i++){
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    nullSelected(e);
                }
            });
            hand1.add(b);
        }

        //update hand2
        hand2.removeAll();
        count = 10;
        for(Card card : handButtons2) {
            JButton b = new JButton(card.getCover());
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.putClientProperty("card",20-count);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    cardSelected(e);
                }
            });
            hand2.add(b);
            count--;
        }
        for(int i=0;i<count;i++){
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e){
                    nullSelected(e);
                }
            });
            hand2.add(b);
        }

        //update board1
        count = 0;
        board1.removeAll();
        for(MinionCard minion : boardButtons1) {

            JButton b = new JButton(minion.getCover());
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.putClientProperty("slot",count);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boardButtonClicked(e);
                }
            });
            board1.add(b);
            count++;
        }
        {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.putClientProperty("slot",count);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boardButtonClicked(e);
                }
            });
            board1.add(b);
        }

        //update board2
        board2.removeAll();
        count = 10;
        for(MinionCard minion : boardButtons2) {
            JButton b = new JButton(minion.getCover());
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.putClientProperty("slot",count);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boardButtonClicked(e);
                }
            });
            board2.add(b);
            count++;
        }
        {
            JButton b = new JButton();
            b.setPreferredSize(new Dimension(CARD_CONST_X,CARD_CONST_Y));
            b.putClientProperty("slot",count);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boardButtonClicked(e);
                }
            });
            board2.add(b);
        }

        //update mana
        mana1.setText("Mana: " + m1 + "/" + maxm1);
        mana2.setText("Mana: " + m2 + "/" + maxm2);

        deck1.setText(deckSize1 + "");
        deck2.setText(deckSize2 + "");

        revalidate();
        repaint();
    }

    @Override
    public void sendBoardState(String boardState) {

        ArrayList<MinionCard> bb1 = new ArrayList<>();
        ArrayList<MinionCard> bb2 = new ArrayList<>();
        ArrayList<Card> h1 = new ArrayList<>();
        ArrayList<Card> h2 = new ArrayList<>();

        String[] parts = boardState.split("%",-1);

        String[] board1String = parts[0].split("\\$");

        for(String s : board1String){
            if(s.isEmpty()) break;
            String[] info = s.split("\\|");
            bb1.add(new MinionCard(info[0],
                    Integer.valueOf(info[1]),
                    Integer.valueOf(info[2]),
                    Integer.valueOf(info[4])));
        }

        String[] board2String = parts[1].split("\\$");

        for(String s : board2String){
            if(s.isEmpty()) break;
            String[] info = s.split("\\|");
            bb2.add(new MinionCard(info[0],
                    Integer.valueOf(info[1]),
                    Integer.valueOf(info[2]),
                    Integer.valueOf(info[4])));
        }

        String[] hand1String = parts[2].split("\\$");

        for(String s : hand1String){
            if(s.isEmpty()) break;
            String[] info = s.split("\\|");
            h1.add(new MinionCard(info[0],
                    Integer.valueOf(info[1]),
                    Integer.valueOf(info[2]),
                    Integer.valueOf(info[4])));
        }

        String[] hand2String = parts[3].split("\\$");

        for(String s : hand2String){
            if(s.isEmpty()) break;
            String[] info = s.split("\\|");
            h2.add(new MinionCard(info[0],
                    Integer.valueOf(info[1]),
                    Integer.valueOf(info[2]),
                    Integer.valueOf(info[4])));
        }

        String[] mana1String = parts[4].split("\\$");
        m1 = Integer.valueOf(mana1String[0]);
        maxm1 = Integer.valueOf(mana1String[1]);

        String[] mana2String = parts[5].split("\\$");
        m2 = Integer.valueOf(mana2String[0]);
        maxm2 = Integer.valueOf(mana2String[1]);

        String deck1String = parts[6];
        deckSize1 = Integer.valueOf(deck1String);

        String deck2String = parts[7];
        deckSize2 = Integer.valueOf(deck2String);

        boardButtons1 = bb1;
        boardButtons2 = bb2;
        handButtons1 = h1;
        handButtons2 = h2;

        refresh();
    }

}
