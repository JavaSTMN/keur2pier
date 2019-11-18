package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.card.CardManager;
import com.stmn.keur2pier.card.Spell;
import com.stmn.keur2pier.util.JSONUtils;
import org.json.simple.JSONObject;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private static final int MANA_MAX = 10;

    private static Game instance;

    private Player player1;
    private Player player2;

    private Player currentPlayer;

    private Timer timer;

    synchronized public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    private Game(){

    }

    public void startGame(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        timer = new Timer();
        mulligan();
    }

    public void mulligan(){
        player1.getDeck().shuffle();
        player2.getDeck().shuffle();
        boolean random = new Random().nextBoolean();
        CardManager manager = new CardManager();
        Card theCoin = manager.getCardFromId("1");
        if(random){
            player1.draw(3);
            player2.draw(4);
            player2.getHand().addCard(theCoin);
            startTurn(player1);
        } else {
            player2.draw(3);
            player1.draw(4);
            player1.getHand().addCard(theCoin);
            startTurn(player2);
        }
    }

    public void startTurn(Player player){
        currentPlayer = player;
        player.draw(1);
        if(player.getManaPool() < MANA_MAX) {
            player.setManaPool(player.getManaPool() + 1);
        }
        player.setManaRemaining(player.getManaPool());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endTurn(player);
            }
        }, 2*60*1000);
    }

    public void endTurn(Player player){
        if(player.equals(player1)){
            startTurn(player2);
        } else {
            startTurn(player1);
        }
    }

    public void endGame(Player loser){
        timer.cancel();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
