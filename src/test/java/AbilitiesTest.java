import com.stmn.keur2pier.Game;
import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.card.Card;
import com.stmn.keur2pier.card.CardManager;
import com.stmn.keur2pier.card.Minion;
import com.stmn.keur2pier.deck.DeckManager;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AbilitiesTest {

    private Player player1;
    private Player player2;
    private Game game;
    private DeckManager deckManager;
    private CardManager cardManager;

    @Before
    public void initGame() throws IOException, ParseException {
        game = Game.getInstance();
        deckManager = new DeckManager();
        cardManager = new CardManager();
        player1 = new Player(deckManager.getDeckFromJSON("Paul"));
        player2 = new Player(deckManager.getDeckFromJSON("Nathan"));
        player1.setManaPool(10);
        player2.setManaPool(10);
        game.startGame(player1, player2);
    }

    @Test
    public void minionAbilities() throws IOException, ParseException {
        Card abomination = cardManager.getCardFromId("2");
        Player player = game.getCurrentPlayer();
        player.getHand().addCard(abomination);
        List<Card> hand = player.getHand().getCards();
        player.playCard(hand.get(hand.size() - 1));
        player.getBoard().getMinions().get(0).destroy(player);
        assertEquals(28, player.getHero().getHealth());
    }
}
