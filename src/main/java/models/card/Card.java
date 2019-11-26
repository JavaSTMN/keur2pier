package models.card;

import models.Player;
import org.json.simple.JSONObject;

import java.util.Observable;

public abstract class Card extends Observable implements Comparable<Card> {

    protected String name;
    protected String description;
    protected int manaCost;
    protected String image;
    protected CardClass cardClass;
    protected Rarity rarity;
    protected String type;

    protected Card(JSONObject jsonObject){
        this.name = (String) jsonObject.getOrDefault("name", "Name");
        this.description = (String) jsonObject.getOrDefault("description", "");
        this.manaCost = ((Long) jsonObject.getOrDefault("manaCost", 0L)).intValue();
        this.image = (String) jsonObject.getOrDefault("image", "default.png");
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

    public String getDescription() {
        return description;
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

    protected void update(){
        setChanged();
        notifyObservers(this);
    }
}
