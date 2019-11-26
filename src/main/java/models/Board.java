package models;

import models.card.Minion;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Board extends Observable {

    private static final int MAX_CAPACITY = 7;

    private List<Minion> minions;
    private List<Minion> tauntList;

    public Board(){
        this.minions = new ArrayList<>(MAX_CAPACITY);
        this.tauntList = new ArrayList<>(MAX_CAPACITY);
    }

    public void addMinion(Minion minion){
        if(minions.size() < MAX_CAPACITY){
            minions.add(minion);
            if(minion.getMinionMechanics() != null && minion.getMinionMechanics().isTaunt()){
                tauntList.add(minion);
            }
        }
        setChanged();
        notifyObservers(this);
    }

    public void removeMinion(Minion minion){
        minions.remove(minion);
        if(minion.getMinionMechanics().isTaunt()){
            tauntList.remove(minion);
        }
        setChanged();
        notifyObservers(this);
    }

    public List<Minion> getAttackableMinions(){
        if(tauntList.size() > 0){
            return tauntList;
        }
        return minions;
    }

    public List<Minion> getMinions() {
        return minions;
    }

    public List<Minion> getTauntList() {
        return tauntList;
    }

}
