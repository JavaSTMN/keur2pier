package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int MAX_CAPACITY = 7;

    private List<Card> minions;

    public Board(){
        this.minions = new ArrayList<>(MAX_CAPACITY);
    }

    public boolean addMinion(Card minion){
        if(minions.size() < MAX_CAPACITY){
            minions.add(minion);
            return true;
        }
        return false;
    }

    public void removeMinion(Card minion){
        minion.destroy();
        minions.remove(minion);
    }

    public List<Card> getMinions() {
        return minions;
    }
}
