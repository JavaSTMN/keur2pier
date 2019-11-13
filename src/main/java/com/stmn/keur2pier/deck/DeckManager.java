package com.stmn.keur2pier.deck;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.util.JSONUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DeckManager {

    private static final int MAX_CAPACITY = 20;

    private final Hashtable<String, Deck> decks = new Hashtable<>();
    private JSONObject cardCollection;

    public DeckManager() throws Exception {
        this.cardCollection = readFromJSON();
    }

    public Deck getDeck(String name){
        return decks.get(name);
    }

    public void createDeck(Deck deck){
        decks.put(deck.getName(), deck);
    }

    public void removeDeck(Deck deck){
        decks.remove(deck.getName());
    }


    public boolean removeCard(Deck deck, Card card){
        List<Card> cards = deck.getCards();
        for (Card c : cards){
            if(card.equals(c)){
                cards.remove(card);
                return true;
            }
        }
        return false;
    }

    public boolean addCard(Deck deck, Card card) throws Exception {
        List<Card> cards = deck.getCards();
        assert (cards.size() < MAX_CAPACITY);
        int count = 0;
        for (Card c : cards){
            if(card.equals(c)){
                count++;
            }
        }
        if(count<2){
            cards.add(card);
            return true;
        }
        return false;
    }

    public JSONObject readFromJSON() throws IOException, ParseException {
        return JSONUtils.readJSON(new File("src/main/resources/json/cards").getAbsolutePath());
    }

    public void writeToJSON() throws IOException {
        JSONObject jsonObject = new JSONObject();
        Iterator iterator = decks.entrySet().iterator();
        Gson gson = new Gson();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            jsonObject.put(pair.getKey(), gson.toJsonTree(decks.get(pair.getKey())));
            iterator.remove();
        }
        JSONUtils.writeJSON(jsonObject, new File("src/main/resources/json/decks").getAbsolutePath());
    }

    public Deck getDeckFromJSON(String deckName) throws IOException, ParseException{
        JSONObject jsonObject = JSONUtils.readJSON(new File("src/main/resources/json/decks").getAbsolutePath());
        Deck deck = new Deck(jsonObject, deckName);
        return deck;
    }

    public JSONObject getCardCollection() {
        return cardCollection;
    }
}
