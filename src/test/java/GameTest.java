import models.Game;
import models.Player;
import models.deck.DeckManager;
import org.junit.Test;

public class GameTest {

    @Test
    public void mainTest() throws Exception {
        Game game = Game.getInstance();
        DeckManager deckManager = new DeckManager();
        Player player1 = new Player(deckManager.getDeckFromJSON("Paul"));
        Player player2 = new Player(deckManager.getDeckFromJSON("Nathan"));
        game.startGame(player1, player2);
        Player currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer = game.getCurrentPlayer();
        game.endTurn(currentPlayer);
        currentPlayer.lose();
    }

}
