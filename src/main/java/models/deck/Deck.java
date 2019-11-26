package models.deck;

import models.card.Card;
import models.card.CardManager;
import models.hero.HeroClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Deck extends Observable {

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
        update();
    }

    public Card removeAtIndex(int index){
        Card card =  cards.remove(index);
        update();
        return card;
    }

    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void update(){
        setChanged();
        notifyObservers(this);
    }
}