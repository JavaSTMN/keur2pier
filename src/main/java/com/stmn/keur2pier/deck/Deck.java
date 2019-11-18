package com.stmn.keur2pier.deck;

import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.card.CardManager;
import com.stmn.keur2pier.card.Minion;
import com.stmn.keur2pier.hero.Hero;
import com.stmn.keur2pier.hero.HeroClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private String name;
    private List<Card> cards;
    private HeroClass heroClass;

    public Deck(JSONObject jsonObject, String deckName){
        this.name = deckName;
        this.cards = new ArrayList<>();
        CardManager cardManager = new CardManager();
        JSONArray jsonArray = (JSONArray) jsonObject.getOrDefault("cards",null);
        if(jsonArray != null){
            for (Object o : jsonArray){
                this.cards.add(cardManager.createFromJSON((JSONObject) o));
            }
        }
        this.heroClass = HeroClass.valueOf((String) jsonObject.get("heroClass"));

    }

    public Deck(String name, HeroClass hero){
        this.name = name;
        this.heroClass = hero;
        this.cards = new ArrayList<>();
    }

    public void shuffle(){
        Collections.shuffle(this.cards);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getCardCount(){
        return cards.size();
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public Card removeAtIndex(int index){
        return cards.remove(index);
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }
}