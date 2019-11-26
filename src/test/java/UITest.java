import models.Game;
import models.Player;
import models.card.Card;
import models.card.CardManager;
import models.deck.DeckManager;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import views.GameView;
import views.layouts.CardLayout;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class UITest {

    @Test
    public void Test() throws IOException, ParseException {

        DeckManager deckManager = new DeckManager();

        GameView gameView = GameView.getInstance();
        gameView.init(new Player(deckManager.getDeckFromJSON("Nathan")), new Player(deckManager.getDeckFromJSON("Paul")));

        Game game = Game.getInstance();
        Player player1 = game.getCurrentPlayer();
        Player player2 = game.getOpponent(player1);
        List<Card> cards = player1.getHand().getCards();

        player1.playCard(cards.get(0));
        game.endTurn(player1);
    }

}
