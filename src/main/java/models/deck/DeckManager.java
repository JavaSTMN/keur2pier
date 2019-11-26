package models.deck;

import com.google.gson.Gson;
import helpers.JSONHelper;
import models.card.Card;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DeckManager {

    private static final int MAX_CAPACITY = 20;

    private final Hashtable<String, Deck> decks = new Hashtable<>();

    public void createDeck(Deck deck){
        decks.put(deck.getName(), deck);
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

    public boolean addCard(Deck deck, Card card) {
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

    public void writeToJSON() throws IOException {
        JSONObject jsonObject = new JSONObject();
        Iterator iterator = decks.entrySet().iterator();
        Gson gson = new Gson();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            jsonObject.put(pair.getKey(), gson.toJsonTree(decks.get(pair.getKey())));
            iterator.remove();
        }
        JSONHelper.writeJSON(jsonObject, new File("src/main/resources/json/decks").getAbsolutePath());
    }

    public Deck getDeckFromJSON(String deckName) throws IOException, ParseException{
        JSONObject jsonObject = JSONHelper.readJSON(new File("src/main/resources/json/decks").getAbsolutePath());
        Deck deck = new Deck((JSONObject) jsonObject.get(deckName), deckName);
        return deck;
    }
}