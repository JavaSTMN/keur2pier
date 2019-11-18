package com.stmn.keur2pier.card;

import org.json.simple.JSONObject;

public class Mechanics {

    private boolean taunt;
    private boolean divineShield;

    public Mechanics(JSONObject jsonObject) {
        this.taunt = (boolean) jsonObject.getOrDefault("taunt", false);
        this.divineShield = (boolean) jsonObject.getOrDefault("divineShield", false);
    }
}
