package views.layouts;

import models.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ManaLayout extends JPanel implements Observer {

    private JLabel manaView;

    public ManaLayout(Player player){
        player.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.DARK_GRAY);
        manaView = new JLabel("MANA: " + player.getManaRemaining() + "/" + player.getManaPool());
        manaView.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        manaView.setForeground(Color.WHITE);
        manaView.setBackground(Color.DARK_GRAY);

        add(manaView);
    }

    @Override
    public void update(Observable observable, Object o) {
        Player player = (Player) o;
        removeAll();
        manaView = new JLabel("MANA: " + player.getManaRemaining() + "/" + player.getManaPool());
        manaView.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        manaView.setForeground(Color.WHITE);
        manaView.setBackground(Color.DARK_GRAY);
        add(manaView);
        repaint();
        revalidate();
    }
}
