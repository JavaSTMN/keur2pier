package com.stmn.keur2pier.card;

import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.effect.SpellEffect;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpellMechanics {

    private JSONObject effect;

    public SpellMechanics(JSONObject mechanics) {
        this.effect = (JSONObject) mechanics.get("method");
    }

    public void trigger(Player owner) {
        try {
            Method method = SpellEffect.class.getMethod((String) effect.get("method"), owner.getClass(), effect.getClass());
            method.invoke(effect.get("method"), owner, effect);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
