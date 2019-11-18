package com.stmn.keur2pier.card.effect;

import com.stmn.keur2pier.Game;
import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.Minion;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeathRattle {

    public static void dealDamageToTarget(Player owner, JSONObject jsonObject){

    }

    public static void dealDamageToAll(Player owner, JSONObject jsonObject){
        List<Minion> minions = new ArrayList<>();
        minions.addAll(Game.getInstance().getPlayer1().getBoard().getMinions());
        minions.addAll(Game.getInstance().getPlayer2().getBoard().getMinions());
        for (Minion minion: minions) {
            minion.takeDamage(((Long) jsonObject.get("damage")).intValue());
        }
    }

    public static void summonMinions(Player owner, JSONObject jsonObject){
        owner.getBoard().addMinion(new Minion((JSONObject) jsonObject.get("minion")));
    }
}
