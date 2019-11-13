package com.stmn.keur2pier.card;

import com.stmn.keur2pier.util.JSONUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;

public class CardManager {

    private JSONObject cards;

    public CardManager() {
        try {
            this.cards = readFromJSON();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public Card getCardFromId(String id){
        return new Spell((JSONObject) cards.get(id));
    }

    private JSONObject readFromJSON() throws IOException, ParseException {
        return JSONUtils.readJSON(new File("src/main/resources/json/cards").getAbsolutePath());
    }
}
