import com.stmn.keur2pier.card.CardManager;
import com.stmn.keur2pier.deck.Deck;
import com.stmn.keur2pier.deck.DeckManager;
import com.stmn.keur2pier.hero.HeroClass;
import com.stmn.keur2pier.util.JSONUtils;
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
        deckManager.createDeck(paulDeck);

        Deck nathanDeck = new Deck("Nathan", HeroClass.WARLOCK);
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("2"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("3"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("4"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("5"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("6"));
        deckManager.addCard(nathanDeck, cardManager.getCardFromId("7"));
        deckManager.createDeck(nathanDeck);

        deckManager.writeToJSON();
        assertNotNull(JSONUtils.readJSON(new File("src/main/resources/json/decks").getAbsolutePath()));
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