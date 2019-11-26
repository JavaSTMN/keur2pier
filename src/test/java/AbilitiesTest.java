import models.Game;
import models.Player;
import models.card.Card;
import models.card.CardManager;
import models.card.Minion;
import models.deck.DeckManager;
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
    public void gainMana() {
        Card DogeCoin = cardManager.getCardFromId("1");
        Player player = game.getCurrentPlayer();
        player.getHand().addCard(DogeCoin);
        List<Card> hand = player.getHand().getCards();
        int currentManaRemaining = player.getManaRemaining();
        player.playCard(hand.get(hand.size() - 1));
        assertEquals(currentManaRemaining + 1, player.getManaRemaining());
    }


    @Test
    public void buffAllies() {
        Card ElonMusk = cardManager.getCardFromId("11");
        Player player = game.getCurrentPlayer();
        player.getHand().addCard(ElonMusk);
        List<Card> hand = player.getHand().getCards();
        Card Arouf1 = cardManager.getCardFromId("3");
        Card Arouf2 = cardManager.getCardFromId("3");
        player.playCard(Arouf1);
        player.playCard(Arouf2);
        player.setManaRemaining(10);
        int attack = ((Minion) Arouf1).getAttack();
        player.playCard(hand.get(hand.size() - 1));
        assertEquals(attack + 2, player.getBoard().getMinions().get(0).getAttack());
        assertEquals(attack + 2, player.getBoard().getMinions().get(1).getAttack());
    }

    @Test
    public void dealDamageToRandom() {
        Card Gavin = cardManager.getCardFromId("14");
        Player player = game.getCurrentPlayer();
        player.getHand().addCard(Gavin);
        List<Card> hand = player.getHand().getCards();
        player.playCard(hand.get(hand.size() - 1));
        assertEquals(28, Game.getInstance().getOpponent(player).getHero().getHealth());
    }

    @Test
    public void dealDamageToAll() {
        Card AH = cardManager.getCardFromId("8");
        Player player = game.getCurrentPlayer();
        player.getHand().addCard(AH);
        List<Card> hand = player.getHand().getCards();
        player.playCard(hand.get(hand.size() - 1));
        assertEquals(27, Game.getInstance().getOpponent(player).getHero().getHealth());
        assertEquals(27, player.getHero().getHealth());
    }
}
