package views.layouts;

import models.Player;
import models.card.Card;
import models.card.Minion;
import views.GameView;

import java.awt.event.MouseEvent;

public class CardBoardLayout extends CardLayout {

    private Player player;

    public CardBoardLayout(Player player, Card card) {
        super(player, card);
        this.player = player;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CardBoardLayout selected = GameView.getInstance().getSelectedCardBoard();
        if(selected == null){
            GameView.getInstance().setSelectedCardBoard(this);
        } else if(this.getCard() != selected.getCard()) {
            Minion selectedMinion = (Minion) selected.getCard();
            Minion targetMinion = (Minion) this.getCard();
            selectedMinion.attack(targetMinion);
            GameView.getInstance().setSelectedCardBoard(null);
            player.getBoard().update();
        }
    }
}
