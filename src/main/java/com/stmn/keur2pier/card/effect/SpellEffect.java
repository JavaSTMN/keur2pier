package com.stmn.keur2pier.card.effect;

import com.stmn.keur2pier.Game;
import com.stmn.keur2pier.IFighter;
import com.stmn.keur2pier.Player;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpellEffect {

    public static void draw(Player owner, JSONObject effect){
        owner.draw(((Long) effect.get("number")).intValue());
    }

    public static void addManaForTurn(Player owner, JSONObject effect){
        owner.setManaRemaining(owner.getManaRemaining() + ((Long) effect.get("mana")).intValue());
    }

    public static void addEmptyMana(Player owner, JSONObject effect){
        owner.setManaPool(owner.getManaPool() + ((Long) effect.get("mana")).intValue());
    }

    public static void dealDamageToRandom(Player owner, JSONObject effect){
        List<IFighter> fighters = new ArrayList<>();
        Player opponent = Game.getInstance().getOpponent(owner);
        fighters.add(opponent.getHero());
        fighters.addAll(opponent.getBoard().getMinions());
        Random rand = new Random();
        for (int i = 0; i < ((Long) effect.get("number")).intValue(); i++){
            fighters.get(rand.nextInt(fighters.size())).takeDamage(((Long) effect.get("damage")).intValue());
        }
    }
}
