package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Minion;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private static final int MAX_CAPACITY = 7;

    private final List<Minion> minions;

    public Board(){
        this.minions = new ArrayList<>(MAX_CAPACITY);
    }

    public void addMinion(Minion minion){
        if(minions.size() < MAX_CAPACITY){
            minions.add(minion);
        }
    }

    public void removeMinion(Minion minion){
        minions.remove(minion);
    }

    public List<Minion> getMinions() {
        return minions;
    }
}
