package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Board;
import org.json.simple.JSONObject;

import java.awt.*;

public abstract class Card {

    protected String name;
    protected String effect;
    protected int manaCost;
    protected Image illustration;
    protected CardClass type;
    protected Rarity rarity;

    protected Card(JSONObject jsonObject){
        this.name = (String) jsonObject.getOrDefault("name", "Name");
        this.effect = (String) jsonObject.getOrDefault("effect", "Effect");
        this.manaCost = ((Long) jsonObject.getOrDefault("cost", 0L)).intValue();
        this.illustration = setImage((String) jsonObject.getOrDefault("illustration", "default"));
        this.type = setType((String) jsonObject.getOrDefault("type", ""));
        this.rarity = setRarity((String) jsonObject.getOrDefault("rarity", ""));
    }

    private Rarity setRarity(String rarity){
        switch (rarity){
            case "COMMON":
                return Rarity.COMMON;

            case "RARE":
                return Rarity.RARE;

            case "EPIC":
                return Rarity.EPIC;

            case "LEGENDARY":
                return Rarity.LEGENDARY;

            default:
                return Rarity.FREE;
        }
    }

    private CardClass setType(String type){
        switch (type){
            case "DRUID":
                return CardClass.DRUID;

            case "HUNTER":
                return CardClass.HUNTER;

            case "WARLOCK":
                return CardClass.WARLOCK;

            case "MAGE":
                return CardClass.MAGE;

            case "WARRIOR":
                return CardClass.WARRIOR;

            case "ROGUE":
                return CardClass.ROGUE;

            case "PALADIN":
                return CardClass.PALADIN;

            case "SHAMAN":
                return CardClass.SHAMAN;

            case "PRIEST":
                return CardClass.PRIEST;

            default:
                return CardClass.NEUTRAL;
        }
    }

    private Image setImage(String imageName){
        return null;
    }

    public void destroy(){

    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public int getManaCost() {
        return manaCost;
    }

    public Image getIllustration() {
        return illustration;
    }

    public CardClass getType() {
        return type;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
