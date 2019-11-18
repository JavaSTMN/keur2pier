package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.deck.Deck;
import com.stmn.keur2pier.hero.Hero;

public class Player {

    private static final int START_MANA = 0;

    private int manaPool;
    private int manaRemaining;
    private Hand hand;
    private Deck deck;
    private Hero hero;
    private Board board;
    private int fatigue;

    public Player(Deck deck){
        this.deck = deck;
        this.hero = new Hero(deck.getHeroClass());
        this.hand = new Hand();
        this.board = new Board();
        this.manaPool = START_MANA;
        this.fatigue = 0;
    }

    public void lose(){
        Game.getInstance().endGame(this);
    }

    public boolean playCard(Card card){
        if(manaRemaining - card.getManaCost() >=0){
            hand.remove(card);
            card.playCard(this);
            manaRemaining -= card.getManaCost();
            return true;
        }
        return false;
    }

    public void draw(int number){
        for (int i = 0; i < number; i++){
            if(deck.getCardCount()!=0){
                Card card = deck.removeAtIndex(deck.getCardCount() - 1);
                hand.addCard(card);
            } else {
                fatigue++;
                hero.takeDamage(fatigue);
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

    public Hero getHero() {
        return hero;
    }


}
