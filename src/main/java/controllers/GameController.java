package controllers;

import models.Player;
import models.deck.DeckManager;
import views.GameView;

import javax.swing.*;

public class GameController extends JFrame {

    public static void main(String[] args) throws Exception {
        DeckManager deckManager = new DeckManager();
        GameView gameView = GameView.getInstance();
        gameView.init(new Player(deckManager.getDeckFromJSON("Nathan")), new Player(deckManager.getDeckFromJSON("Paul")));
    }

}