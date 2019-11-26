package views.layouts;

import models.card.Card;
import models.card.Minion;
import views.GameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class CardLayout extends JPanel implements Observer, MouseListener {

    private final int CARD_WIDTH = 100;
    private final int CARD_HEIGHT = 150;

    private JLabel mana;
    private JLabel image;
    private JLabel name;
    private JLabel attack;
    private JLabel health;

    private Card card;

    private boolean selected;
    private boolean waitingForTarget;

    public CardLayout(Card card) {
        this.card = card;
        card.addObserver(this);

        setOpaque(true);
        selected = false;
        waitingForTarget = false;
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setSize(CARD_WIDTH, CARD_HEIGHT);
        setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        setBackground(Color.DARK_GRAY);

        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        mana = new JLabel();
        mana.setText("Mana: " + card.getManaCost());
        mana.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
        mana.setForeground(Color.WHITE);

        image = new JLabel();

        System.out.print("src/main/resources/images/card-components/illustrations/" + card.getImage());
        Image resizedImage = new ImageIcon("src/main/resources/images/card-components/illustrations/" + card.getImage()).getImage();
        resizedImage = resizedImage.getScaledInstance(this.getWidth(), (int)(this.getHeight() * 0.3f), Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(resizedImage);
        image.setIcon(img);

        name = new JLabel();
        name.setText(card.getName());
        name.setForeground(Color.LIGHT_GRAY);


        add(mana);
        add(image);
        add(name);

        // Specific layout for a minion card
        if(card instanceof Minion) {
            Minion minionCard = (Minion)card;

            // Attack label
            attack = new JLabel();
            attack.setText("Atk: " + minionCard.getAttack());
            add(attack);
            attack.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
            attack.setForeground(Color.WHITE);

            // Health label
            health = new JLabel();
            health.setText("Vie: "+minionCard.getHealth());
            add(health);
            health.setFont(new Font(Font.DIALOG, Font.BOLD,  15));
            health.setForeground(Color.WHITE);

        }

        this.setVisible(true);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setSelected(!isSelected());
        GameView.getInstance().setSelectedCard(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Rien
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Rien
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected()){
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 4));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected()) {
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        }
    }

    @Override
    public void update(Observable observable, Object o) {
        Card card = (Card) o;
        mana.setText("Mana: " + card.getManaCost());
        name.setText(card.getName());
        if(card instanceof Minion) {
            attack.setText("Atk: " + ((Minion) card).getAttack());
            health.setText("Vie: " + ((Minion) card).getHealth());
        }
        repaint();
        revalidate();
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected){
            setBorder(BorderFactory.createLineBorder(Color.ORANGE, 4));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        }
    }


    public boolean isSelected() {
        return this.selected;
    }

    public boolean isWaitingForTarget() {
        return waitingForTarget;
    }

    public void setWaitingForTarget(boolean waitingForTarget) {
        this.waitingForTarget = waitingForTarget;
        if (waitingForTarget){
            setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
        } else {
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        }
    }

    public Card getCard() {
        return card;
    }
}
