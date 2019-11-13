package com.stmn.keur2pier.deck;

import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.hero.Hero;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final int MAX_SIZE = 20;

    private String name;
    private List<Card> cards;
    private Hero hero;

    public Deck(String name, Hero hero){
        this.name = name;
        this.hero = hero;
        this.cards = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public Hero getHero() {
        return hero;
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

    public Card removeAtIndex(int index){
        return cards.remove(index);
    }
}