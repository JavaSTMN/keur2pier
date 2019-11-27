package views.layouts;

import models.Game;
import models.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class NextTurnLayout extends JPanel {

    private JButton nextTurn;
    private Player player;

    public NextTurnLayout(Player player){
        this.player = player;
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBackground(Color.DARK_GRAY);
        nextTurn = new JButton("Finir le tour");
        nextTurn.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        nextTurn.setForeground(Color.WHITE);
        nextTurn.setBackground(Color.DARK_GRAY);
        nextTurn.addActionListener(actionEvent -> {
            if(Game.getInstance().getCurrentPlayer() == this.player){
                Game.getInstance().endTurn(this.player);
            }
        });
        add(nextTurn);
    }

}
