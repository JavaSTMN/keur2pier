package views.layouts;

import models.Board;
import models.Game;
import models.Player;
import models.card.*;
import views.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class BoardLayout extends JPanel implements Observer, MouseListener {

    private Player player;
    private Board board;

    public BoardLayout(Player player, Board board) {
        this.board = board;
        this.player = player;
        board.addObserver(this);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        setBackground(new Color(207, 204, 141));
        setLayout(new FlowLayout(FlowLayout.CENTER));

        for(Card card: board.getMinions()) {
            CardBoardLayout cardLayout = new CardBoardLayout(player, card);
            add(cardLayout);
        }
        addMouseListener(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        Board board = (Board) o;
        removeAll();
        for (Card card: board.getMinions()) {
            add(new CardBoardLayout(player, card));
        }
        repaint();
        revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        CardLayout cardLayout = GameView.getInstance().getSelectedCard();
        if(cardLayout != null){
            Game.getInstance().getCurrentPlayer().playCard(cardLayout.getCard());
            GameView.getInstance().setSelectedCard(null);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}