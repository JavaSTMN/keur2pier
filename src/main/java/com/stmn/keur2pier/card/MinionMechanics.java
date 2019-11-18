package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.effect.BattleCry;
import com.stmn.keur2pier.card.effect.DeathRattle;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MinionMechanics {

    private boolean taunt;
    private boolean divineShield;
    private boolean charge;
    private JSONObject battleCry;
    private JSONObject deathRattle;

    public MinionMechanics(JSONObject jsonObject) {
        this.taunt = (boolean) jsonObject.getOrDefault("taunt", false);
        this.divineShield = (boolean) jsonObject.getOrDefault("divineShield", false);
        this.charge = (boolean) jsonObject.getOrDefault("charge", false);
        this.battleCry = (JSONObject) jsonObject.getOrDefault("battleCry", null);
        this.deathRattle = (JSONObject) jsonObject.getOrDefault("deathRattle", null);
    }

    public boolean isTaunt() {
        return taunt;
    }

    public boolean isDivineShield() {
        return divineShield;
    }

    public boolean isCharge() {
        return charge;
    }

    public void setTaunt(boolean taunt) {
        this.taunt = taunt;
    }

    public void setDivineShield(boolean divineShield) {
        this.divineShield = divineShield;
    }

    public void setCharge(boolean charge) {
        this.charge = charge;
    }

    public void battleCry(Player owner) {
        if(battleCry != null){
            try {
                Method method = BattleCry.class.getMethod((String) battleCry.get("method"), owner.getClass(), battleCry.getClass());
                method.invoke(battleCry.get("method"), owner, battleCry);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void deathRattle(Player owner) {
        if(deathRattle != null){
            try {
                Method method = DeathRattle.class.getMethod((String) deathRattle.get("method"), owner.getClass(), deathRattle.getClass());
                method.invoke(deathRattle.get("method"), owner, deathRattle);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
