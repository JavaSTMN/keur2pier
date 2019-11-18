package com.stmn.keur2pier.card.effect;

import com.stmn.keur2pier.Game;
import com.stmn.keur2pier.IFighter;
import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.CardManager;
import com.stmn.keur2pier.card.Minion;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Abilities {

    /**
     * Fait piocher {amount} cartes
     * JSON :
     *  - amount : nombre de carte à piocher
     * @param owner
     * @param effect
     */
    public static void draw(Player owner, JSONObject effect){
        owner.draw(((Long) effect.get("amount")).intValue());
    }

    /**
     * JSON :
     *  - amount : nombre de cristal temporaire ajouté
     * @param owner
     * @param effect
     */
    public static void addManaForTurn(Player owner, JSONObject effect){
        owner.setManaRemaining(owner.getManaRemaining() + ((Long) effect.get("amount")).intValue());
    }

    /**
     * JSON :
     *  - amount : nombre de cristal vide ajouté
     * @param owner
     * @param effect
     */
    public static void addEmptyMana(Player owner, JSONObject effect){
        owner.setManaPool(owner.getManaPool() + ((Long) effect.get("amount")).intValue());
    }

    /**
     * JSON :
     *  - target : nombre de cibles
     *  - damage : dégâts
     * @param owner
     * @param effect
     */
    public static void dealDamageToRandom(Player owner, JSONObject effect){
        List<IFighter> fighters = new ArrayList<>();
        Player opponent = Game.getInstance().getOpponent(owner);
        fighters.add(opponent.getHero());
        fighters.addAll(opponent.getBoard().getMinions());
        Random rand = new Random();
        for (int i = 0; i < ((Long) effect.get("target")).intValue(); i++){
            fighters.get(rand.nextInt(fighters.size())).takeDamage(((Long) effect.get("damage")).intValue());
        }
    }

    /**
     * JSON :
     *  - damage : dégâts
     * @param owner
     * @param effect
     */
    public static void dealDamageToOpponent(Player owner, JSONObject effect){
        Player opponent = Game.getInstance().getOpponent(owner);
        opponent.getHero().takeDamage(((Long) effect.get("damage")).intValue());
    }

    /**
     * JSON :
     *  - minionId : id de la carte
     * @param owner
     * @param effect
     */
    public static void summonMinions(Player owner, JSONObject effect){
        CardManager cardManager = new CardManager();
        owner.getBoard().addMinion(new Minion(cardManager.getJSONFromId((String) effect.get("minionId"))));
    }

    /**
     * JSON :
     *  - minionId : id de la carte
     * @param owner
     * @param effect
     */
    public static void addCardInHand(Player owner, JSONObject effect){
        CardManager cardManager = new CardManager();
        owner.getHand().addCard(cardManager.getCardFromId((String) effect.get("minionId")));
    }

    /**
     * JSON :
     *  - attack : attaque à ajouter
     *  - health : vie à ajouter
     * @param owner
     * @param effect
     */
    public static void buffAllies(Player owner, JSONObject effect){
        List<Minion> minions = owner.getBoard().getMinions();
        for (Minion minion: minions) {
            minion.setAttack(minion.getAttack() + ((Long) effect.getOrDefault("attack", 0)).intValue());
            minion.setHealth(minion.getHealth() + ((Long) effect.getOrDefault("health", 0)).intValue());
        }
    }

    /**
     * JSON :
     *  - damage : aégâts
     * @param owner
     * @param effect
     */
    public static void dealDamageToAll(Player owner, JSONObject effect){
        List<IFighter> fighters = new ArrayList<>();
        fighters.addAll(Game.getInstance().getPlayer1().getBoard().getMinions());
        fighters.addAll(Game.getInstance().getPlayer2().getBoard().getMinions());
        fighters.add(owner.getHero());
        fighters.add(Game.getInstance().getOpponent(owner).getHero());
        for (IFighter fighter: fighters) {
            fighter.takeDamage(((Long) effect.get("damage")).intValue());
        }
    }
}
