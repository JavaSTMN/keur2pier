package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final Player owner;
    private final int MAX_SIZE = 10;
    private List<Card> cards;

    public Hand(Player owner) {
        this.owner = owner;
        this.cards = new ArrayList<>();
    }

    public Hand(Player owner, List<Card> cards){
        this.owner = owner;
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Player getOwner() {
        return owner;
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
            if (c.equals(card)){
                return cards.remove(c);
            }
        }
        return false;
    }
}
