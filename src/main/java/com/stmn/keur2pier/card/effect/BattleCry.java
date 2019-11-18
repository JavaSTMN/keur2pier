package com.stmn.keur2pier.card.effect;

import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.Minion;
import org.json.simple.JSONObject;

import java.util.List;

public class BattleCry {



    public static void dealDamageToTarget(Player owner, JSONObject jsonObject){

    }

    public static void dealDamageToRandom(Player owner, JSONObject jsonObject){

    }

    public static void summonMinions(Player owner, JSONObject jsonObject){
        owner.getBoard().addMinion(new Minion((JSONObject) jsonObject.get("minion")));
    }

    public static void buffAllies(Player owner, JSONObject jsonObject){
        List<Minion> minions = owner.getBoard().getMinions();
        for (Minion minion: minions) {
            minion.setAttack(minion.getAttack() + ((Long) jsonObject.getOrDefault("attack", 0)).intValue());
            minion.setHealth(minion.getHealth() + ((Long) jsonObject.getOrDefault("health", 0)).intValue());
        }
    }

}
