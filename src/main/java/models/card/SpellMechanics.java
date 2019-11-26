package models.card;

import models.Player;
import models.card.effect.Abilities;
import org.json.simple.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SpellMechanics {

    private JSONObject mechanic;

    public SpellMechanics(JSONObject mechanic) {
        this.mechanic = mechanic;
    }

    public void trigger(Player owner) {
        try {
            Method method = Abilities.class.getMethod((String) mechanic.get("method"), owner.getClass(), mechanic.getClass());
            method.invoke(mechanic.get("method"), owner, mechanic);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
