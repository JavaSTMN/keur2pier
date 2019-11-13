package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.card.Spell;
import com.stmn.keur2pier.deck.Deck;
import com.stmn.keur2pier.hero.Hero;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final int START_MANA = 0;
    private static final int MANA_MAX = 10;

    private int manaPool;
    private int manaRemaining;
    private Hand hand;
    private Deck deck;
    private Board board;
    private int fatigue;

    public Player(Deck deck){
        this.deck = deck;
        this.board = new Board();
        this.manaPool = START_MANA;
        this.fatigue = 0;
    }

    public void mulligan(boolean isFirstPlayer){
        hand = new Hand(this);
        hand.addCard(deck.removeAtIndex(deck.getCardCount()-1));
        hand.addCard(deck.removeAtIndex(deck.getCardCount()-1));
        hand.addCard(deck.removeAtIndex(deck.getCardCount()-1));
        if (!isFirstPlayer){
            hand.addCard(deck.removeAtIndex(deck.getCardCount()-1));
            hand.addCard(new Spell(null));
        }
    }

    public void startTurn(){
        draw(1);
        if(manaPool < MANA_MAX) {
            manaPool++;
        }
        this.manaRemaining = manaPool;
    }

    public void endTurn(){
        Game.getInstance().endTurn(this);
    }

    public void draw(int number){
        for (int i = 0; i < number; i++){
            if(deck.getCardCount()!=0){
                Card card = deck.removeAtIndex(deck.getCardCount() - 1);
                draw(card);
            } else {
                fatigue++;
                deck.getHero().setHealth(deck.getHero().getHealth() - fatigue);
            }
        }
    }

    public void playCard(Card card){
        assert (manaRemaining - card.getManaCost() >=0);
        hand.remove(card);
        board.addMinion(card);
        manaRemaining -= card.getManaCost();
    }

    public void lose(){
        Game.getInstance().endGame(this);
    }

    public void draw(Card card){
        if(!hand.addCard(card)){
            card.destroy();
        }
    }
}
