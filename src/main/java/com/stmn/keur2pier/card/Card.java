package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Player;
import org.json.simple.JSONObject;

public abstract class Card implements Comparable<Card> {

    protected String name;
    protected int manaCost;
    protected String image;
    protected CardClass cardClass;
    protected Rarity rarity;
    protected String type;

    protected Card(JSONObject jsonObject){
        this.name = (String) jsonObject.getOrDefault("name", "Name");
        this.manaCost = ((Long) jsonObject.getOrDefault("manaCost", 0L)).intValue();
        this.image = (String) jsonObject.getOrDefault("illustration", "default.png");
        this.cardClass = CardClass.valueOf((String) jsonObject.getOrDefault("cardClass", "NEUTRAL"));
        this.rarity = Rarity.valueOf((String) jsonObject.getOrDefault("rarity", "FREE"));
        this.type = (String) jsonObject.getOrDefault("type", "MINION");
    }

    public void playCard(Player owner){

    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public String getImage() {
        return image;
    }

    public CardClass getCardClass() {
        return cardClass;
    }

    public Rarity getRarity() {
        return rarity;
    }

    @Override
    public int compareTo(Card card) {
        return this.name.compareTo(card.getName());
    }
}
