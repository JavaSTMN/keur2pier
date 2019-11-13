package com.stmn.keur2pier.deck;

import com.stmn.keur2pier.card.Minion;
import com.stmn.keur2pier.card.Spell;
import com.stmn.keur2pier.hero.Hero;
import com.stmn.keur2pier.hero.HeroClass;
import com.stmn.keur2pier.util.JSONUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class DeckManagerTest {
    @Test
    public void readFromJSON() throws Exception {
        DeckManager deckManager = new DeckManager();
        assertNotNull(deckManager.readFromJSON());
        Hero hero = new Hero(HeroClass.MAGE);
        Deck deck = new Deck("drfcgfchfcgcf", hero);
        deckManager.addCard(deck, new Spell((JSONObject) deckManager.getCardCollection().get("1")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("2")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.createDeck(deck);

        Hero nathanLeHero = new Hero(HeroClass.WARLOCK);
        Deck leDeckDeNathan = new Deck("Nathan", nathanLeHero);
        deckManager.addCard(leDeckDeNathan, new Spell((JSONObject) deckManager.getCardCollection().get("1")));
        deckManager.addCard(leDeckDeNathan, new Minion((JSONObject) deckManager.getCardCollection().get("2")));
        deckManager.addCard(leDeckDeNathan, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.addCard(leDeckDeNathan, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.addCard(leDeckDeNathan, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.createDeck(leDeckDeNathan);


        deckManager.writeToJSON();
        assertNotNull(JSONUtils.readJSON(new File("src/main/resources/json/decks").getAbsolutePath()));
    }

    @Test
    public void getDeckFromJson() throws Exception {
        Deck leDeckDeNathan = (new DeckManager().getDeckFromJSON("Nathan"));
        Deck myTestDeck = (new DeckManager().getDeckFromJSON("drfcgfchfcgcf"));
        assertNotNull(leDeckDeNathan);
        assertNotNull(myTestDeck);
        assertEquals("drfcgfchfcgcf", myTestDeck.getName());
        assertEquals("Nathan", leDeckDeNathan.getName());
    }
}
