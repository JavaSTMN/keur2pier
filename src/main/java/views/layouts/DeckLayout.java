package views.layouts;

import models.deck.Deck;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DeckLayout extends JPanel implements Observer {

    private JPanel deckView;
    private JLabel cardNumber;
    private JLabel deckText;

    public DeckLayout(Deck deck) {
        deck.addObserver(this);
        JPanel deckContainer = new JPanel();
        deckContainer.setLayout(new FlowLayout());

        deckView = new JPanel();
        deckView.setPreferredSize(new Dimension(100, 150));
        deckView.setLayout(new BoxLayout(deckView, BoxLayout.PAGE_AXIS));

        deckText = new JLabel();
        deckText.setText(deck.getName());
        deckText.setAlignmentY(CENTER_ALIGNMENT);
        deckText.setAlignmentX(CENTER_ALIGNMENT);

        cardNumber = new JLabel();
        cardNumber.setText(deck.getCardCount() + " cartes");
        cardNumber.setAlignmentY(CENTER_ALIGNMENT);
        cardNumber.setAlignmentX(CENTER_ALIGNMENT);


 /*
        JLabel image = new JLabel();

        Image resizedImage = new ImageIcon("src/main/resources/images/cardback.png").getImage();
        resizedImage = resizedImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(resizedImage);  // transform it back
        image.setIcon(img);
*/

        deckView.add(deckText);
        deckView.add(cardNumber);
        //deckView.add(image);
        deckContainer.add(deckView);

        if(deck.getCardCount() == 0) {
            Border border = BorderFactory.createLineBorder(Color.RED, 2);
            deckView.setBorder(border);
        }

        setBackground(Color.DARK_GRAY);
        add(deckContainer);
        setLayout(new FlowLayout(FlowLayout.LEADING));
    }

    @Override
    public void update(Observable observable, Object o) {
        Deck deck = (Deck) o;
        deckText.setText(deck.getName());
        cardNumber.setText(String.valueOf(deck.getCardCount()));
        if(deck.getCardCount() == 0) {
            Border border = BorderFactory.createLineBorder(Color.RED, 2);
            deckView.setBorder(border);
        }
        repaint();
        revalidate();
    }
}