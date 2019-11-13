package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Spell;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private static Game instance;

    private Player player1;
    private Player player2;

    private Timer timer;

    synchronized public static Game getInstance(){
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    private Game(){

    }

    public void startGame(){
        if(player1!= null && player2!=null){
            mulligan();
        }
    }

    public void mulligan(){
        boolean random = new Random().nextBoolean();
        player1.mulligan(random);
        player2.mulligan(!random);
        if(random){
            startTurn(player1);
        } else {
            startTurn(player2);
        }
    }

    public void startTurn(Player player){
        player.startTurn();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endTurn(player);
            }
        }, 2*60*1000);
    }

    public void endTurn(Player player){
        if(player.equals(player1)){
            player2.startTurn();
        } else {
            player2.startTurn();
        }
    }

    public void endGame(Player loser){
        timer.cancel();
        //Terminer la partie, afficher win/lose
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
