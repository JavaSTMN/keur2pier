package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.deck.Deck;

public class Player {

    private static final int START_MANA = 0;

    private int manaPool;
    private int manaRemaining;
    private Hand hand;
    private Deck deck;
    private Board board;
    private int fatigue;

    public Player(Deck deck){
        this.deck = deck;
        this.hand = new Hand(this);
        this.board = new Board();
        this.manaPool = START_MANA;
        this.fatigue = 0;
    }

    public void startTurn(){
        draw(1);
    }

    public void lose(){
        Game.getInstance().endGame(this);
    }

    public void playCard(Card card){
        assert (manaRemaining - card.getManaCost() >=0);
        hand.remove(card);
        card.playCard(this);
        manaRemaining -= card.getManaCost();
    }

    public void draw(int number){
        for (int i = 0; i < number; i++){
            if(deck.getCardCount()!=0){
                Card card = deck.removeAtIndex(deck.getCardCount() - 1);
                hand.addCard(card);
            } else {
                fatigue++;
                deck.getHero().takeDamage(fatigue);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public int getManaPool() {
        return manaPool;
    }

    public void setManaPool(int manaPool) {
        this.manaPool = manaPool;
    }

    public int getManaRemaining() {
        return manaRemaining;
    }

    public void setManaRemaining(int manaRemaining) {
        this.manaRemaining = manaRemaining;
    }
}
