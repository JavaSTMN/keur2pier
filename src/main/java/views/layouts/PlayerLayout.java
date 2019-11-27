package views.layouts;

import models.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerLayout extends JPanel {

    public PlayerLayout(Player player) {
        setBackground(Color.DARK_GRAY);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        Box horizontalBox = Box.createHorizontalBox();

        add(horizontalBox);
        horizontalBox.add(new HeroLayout(player.getHero()));
        horizontalBox.add(new HandLayout(player, player.getHand()));
        horizontalBox.add(new DeckLayout(player.getDeck()));
        horizontalBox.add(new ManaLayout(player));
        horizontalBox.add(new NextTurnLayout(player));
    }

}