import com.stmn.keur2pier.card.Minion;
import com.stmn.keur2pier.card.Spell;
import com.stmn.keur2pier.deck.Deck;
import com.stmn.keur2pier.deck.DeckManager;
import com.stmn.keur2pier.hero.Hero;
import com.stmn.keur2pier.hero.HeroClass;
import com.stmn.keur2pier.util.JSONUtils;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class DeckTest {

    @Test
    public void createDeck() throws Exception {
        DeckManager deckManager = new DeckManager();
        assertNotNull(deckManager.readFromJSON());
        Hero hero = new Hero(HeroClass.MAGE);
        Deck deck = new Deck("test1", hero);
        deckManager.addCard(deck, new Spell((JSONObject) deckManager.getCardCollection().get("1")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("2")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.addCard(deck, new Minion((JSONObject) deckManager.getCardCollection().get("3")));
        deckManager.createDeck(deck);
        deckManager.writeToJSON();
        assertNotNull(JSONUtils.readJSON(new File("src/main/resources/json/decks").getAbsolutePath()));
    }
}
