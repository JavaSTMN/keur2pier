import models.card.CardManager;
import models.deck.Deck;
import models.deck.DeckManager;
import models.hero.HeroClass;
import helpers.JSONHelper;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeckTest {
    @Test
    public void writeToJSON() throws Exception {
        DeckManager deckManager = new DeckManager();
        CardManager cardManager = new CardManager();
        Deck paulDeck = new Deck("Paul", HeroClass.MAGE);
        deckManager.addCard(paulDeck, cardManager.getCardFromId("2"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("3"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("4"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("5"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("6"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("7"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("8"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("9"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("10"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("11"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("12"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("13"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("14"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("15"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("16"));
        deckManager.addCard(paulDeck, cardManager.getCardFromId("17"));
        deckManager.createDeck(paulDeck);

        Deck nathanDeck = new Deck("Nathan", HeroClass.WARLOCK);
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("2"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("3"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("4"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("5"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("6"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("7"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("8"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("9"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("10"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("11"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("12"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("13"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("14"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("15"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("16"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("17"));
        deckManager.createDeck(nathanDeck);

        deckManager.writeToJSON();
        assertNotNull(JSONHelper.readJSON(new File("src/main/resources/json/decks").getAbsolutePath()));
    }

    @Test
    public void getDeckFromJson() throws Exception {
        Deck nathanDeck = (new DeckManager().getDeckFromJSON("Nathan"));
        Deck myTestDeck = (new DeckManager().getDeckFromJSON("Paul"));
        assertNotNull(nathanDeck);
        assertNotNull(myTestDeck);
        assertEquals("Paul", myTestDeck.getName());
        assertEquals("Nathan", nathanDeck.getName());
    }
}