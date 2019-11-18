package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final int MAX_SIZE = 10;
    private List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public Hand(List<Card> cards){
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardCount(){
        return cards.size();
    }

    public boolean addCard(Card card){
        if(getCardCount() < MAX_SIZE){
            cards.add(card);
            return true;
        }
        return false;
    }

    public boolean remove(Card card){
        for (Card c: cards) {
            if (c.compareTo(card) == 0){
                return cards.remove(c);
            }
        }
        return false;
    }
}
