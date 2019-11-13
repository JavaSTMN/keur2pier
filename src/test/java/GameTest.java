import com.stmn.keur2pier.Game;
import com.stmn.keur2pier.Player;
import com.stmn.keur2pier.deck.DeckManager;
import org.junit.Test;

public class GameTest {

    @Test
    public void mainTest() throws Exception {
        Game game = Game.getInstance();
        DeckManager deckManager = new DeckManager();
        Player player1 = new Player(deckManager.getDeck("test1"));
        Player player2 = new Player(deckManager.getDeck("test1"));
        game.startGame(player1, player2);
        game.mulligan();
    }

}
