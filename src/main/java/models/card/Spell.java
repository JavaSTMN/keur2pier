package models.card;

import models.Player;
import org.json.simple.JSONObject;

public class Spell extends Card {

    private SpellMechanics spellMechanics;

    public Spell(JSONObject jsonObject) {
        super(jsonObject);
        this.spellMechanics = new SpellMechanics((JSONObject) jsonObject.get("mechanics"));
    }

    @Override
    public void playCard(Player owner){
        spellMechanics.trigger(owner);
    }
}
