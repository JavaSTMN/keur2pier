package views.layouts;

import models.Hand;
import models.Player;
import models.card.Card;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class HandLayout extends JPanel implements Observer {

    private JPanel cardContainer;
    private JPanel handContainer;

    private Player player;

    public HandLayout(Player player, Hand hand) {
        hand.addObserver(this);

        this.player = player;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.DARK_GRAY);

        handContainer = new JPanel();
        handContainer.setLayout(new FlowLayout());
        handContainer.setBackground(Color.DARK_GRAY);

        cardContainer = new JPanel();
        cardContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        cardContainer.setBackground(Color.DARK_GRAY);

        for (Card card : hand.getCards()) {
            cardContainer.add(new CardLayout(player, card));
        }

        handContainer.add(cardContainer);
        add(handContainer);
    }

    @Override
    public void update(Observable observable, Object o) {
        Hand hand = (Hand) o;
        cardContainer.removeAll();
        for (Card card : hand.getCards()) {
            cardContainer.add(new CardLayout(player, card));
        }
        repaint();
        revalidate();
    }
}