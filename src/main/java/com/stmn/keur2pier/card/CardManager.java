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
        JSONObject card = (JSONObject) cards.get(id);
        return createFromJSON(card);
    }
    public Card createFromJSON(JSONObject card){
        switch ((String) card.get("type")){
            case "MINION":
                return new Minion(card);

            case "SPELL":
                return new Spell(card);

            case "WEAPON":
                return new Weapon(card);
        }
        return null;
    }

    private JSONObject readFromJSON() throws IOException, ParseException {
        return JSONUtils.readJSON(new File("src/main/resources/json/cards").getAbsolutePath());
    }
}
