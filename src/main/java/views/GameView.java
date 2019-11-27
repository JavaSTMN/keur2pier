package views;

import models.Game;
import models.Player;
import views.layouts.BoardLayout;
import views.layouts.CardLayout;
import views.layouts.PlayerLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GameView extends JFrame implements Observer {

    private JFrame window;
    private JPanel gameView;
    private JPanel board;

    private PlayerLayout playerLayout1;
    private PlayerLayout playerLayout2;

    private BoardLayout boardPlayer1;
    private BoardLayout boardPlayer2;

    private CardLayout selectedCard;

    private static GameView gameViewObject;

    public static synchronized GameView getInstance(){
        if(gameViewObject == null){
            gameViewObject = new GameView();
        }
        return gameViewObject;
    }

    private GameView(){
        super();
    }

    public void init(Player player1, Player player2) {
        Game.getInstance().startGame(player1, player2);
        Game.getInstance().addObserver(this);
        playerLayout1 = new PlayerLayout(player1);
        playerLayout2 = new PlayerLayout(player2);
        boardPlayer1 = new BoardLayout(player1.getBoard());
        boardPlayer2 = new BoardLayout(player2.getBoard());

        board = new JPanel();
        board.setLayout(new BoxLayout(board, BoxLayout.PAGE_AXIS));
        board.add(boardPlayer2);
        board.add(boardPlayer1);

        gameView = new JPanel();
        gameView.setBackground(Color.GRAY);
        gameView.setLayout(new BorderLayout());
        gameView.add(playerLayout1, BorderLayout.PAGE_END);
        gameView.add(playerLayout2, BorderLayout.PAGE_START);
        gameView.add(board, BorderLayout.CENTER);

        window = new JFrame();
        window.setTitle("Keur2Pier");
        window.setSize(getMaximumSize());
        window.setContentPane(gameView);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public CardLayout getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(CardLayout selectedCard) {
        this.selectedCard = selectedCard;
    }

    public PlayerLayout getPlayerLayout1() {
        return playerLayout1;
    }

    public PlayerLayout getPlayerLayout2() {
        return playerLayout2;
    }

    public BoardLayout getBoardPlayer1() {
        return boardPlayer1;
    }

    public BoardLayout getBoardPlayer2() {
        return boardPlayer2;
    }

    @Override
    public void update(Observable observable, Object o) {
        repaint();
        revalidate();
    }
}