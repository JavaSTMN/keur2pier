package views.layouts;

import models.hero.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class HeroLayout extends JPanel implements Observer {

    private JLabel heroHealth;

    public HeroLayout(Hero hero) {
        hero.addObserver(this);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel heroView = new JPanel();
        heroView.setLayout(new FlowLayout(FlowLayout.CENTER));
        heroView.setBackground(Color.DARK_GRAY);

        heroHealth = new JLabel("VIE: " + hero.getHealth());
        heroHealth.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
        heroHealth.setForeground(Color.WHITE);

        heroView.add(heroHealth);
        add(heroView);
    }

    @Override
    public void update(Observable observable, Object o) {
        Hero hero = (Hero) o;
        heroHealth.setText("VIE: " + hero.getHealth());
    }
}