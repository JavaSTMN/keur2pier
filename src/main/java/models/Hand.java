package models;

import models.card.Card;

import java.util.*;

public class Hand extends Observable {

    private final int MAX_SIZE = 10;
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardCount(){
        return cards.size();
    }

    public void addCard(Card card){
        if(getCardCount() < MAX_SIZE){
            cards.add(card);
        }
        setChanged();
        notifyObservers(this);
    }

     synchronized public void remove(Card card){
         cards.removeIf(card1 -> card1.compareTo(card) == 0);
         setChanged();
         notifyObservers(this);
    }
}
