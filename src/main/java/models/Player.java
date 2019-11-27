package models;

import models.card.Card;
import models.deck.Deck;
import models.hero.Hero;

import java.util.Observable;

public class Player extends Observable {

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

    public void playCard(Card card){
        if(manaRemaining - card.getManaCost() >=0){
            hand.remove(card);
            card.playCard(this);
            manaRemaining -= card.getManaCost();
        }
        update();
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
        setChanged();
        notifyObservers(this);
    }

    public int getManaRemaining() {
        return manaRemaining;
    }

    public void setManaRemaining(int manaRemaining) {
        this.manaRemaining = manaRemaining;
        setChanged();
        notifyObservers(this);
    }

    public Hero getHero() {
        return hero;
    }

    public void update(){
        setChanged();
        notifyObservers(this);
    }
}
