package com.stmn.keur2pier;

import com.stmn.keur2pier.card.Minion;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Abilities {

    public static void cardPicking(int numberOfCardToPick, Player player){
        player.draw(numberOfCardToPick);
    }

    public static void attackXTimesRandomly(int xTime, List<IFighter> fighters,int value){
        int randomNum;
        while(xTime != 0){
            randomNum = ThreadLocalRandom.current().nextInt(1,fighters.size()+1);
            fighters.get(randomNum).takeDamage(value);
            xTime--;
        }
    }

    public static void attackAllMinions(List<Minion> minions, int value){
        boolean countdown = true;
        while(countdown){
            countdown = false;
            for(Minion minion : minions){
                minion.takeDamage(value);
                if(minion.getHealth() == 0){
                    countdown = true;
                }
            }

        }
    }

    public static void attackOneMinionOverAll(int value,Minion minion){
        minion.takeDamage(value);
    }

    public static void attackTheOpponent(int value, IFighter fighter){
        fighter.takeDamage(value);
    }


    public static void onMinionDeathPickACard(int value, Minion minion, Player player){
        minion.takeDamage(value);
        if (minion.getHealth() == 0){
            player.draw(1);
        }
    }

    public static void attackEveryThings(int value,List<IFighter> fighters){
        for (IFighter fighter : fighters ) {
            fighter.takeDamage(value);
        }
    }

    public static void attackAsMuchAsYouHaveMana(List<Minion> minions, Player player){
        int randomNum;
        randomNum = ThreadLocalRandom.current().nextInt(1,minions.size()+1);
        minions.get(randomNum).takeDamage(player.getManaRemaining());
    }

    public static void theCoin(Player owner, int value){
        Game theGame = Game.getInstance();
        Player current = theGame.getCurrentPlayer();
        owner.setManaPool(owner.getManaRemaining()+1);
        boolean playerHasChanged = false;
        while (!playerHasChanged){
            current = theGame.getCurrentPlayer();
            if(current != owner){
                playerHasChanged = true;
            }
        }
        owner.setManaPool(owner.getManaRemaining()-1);
    }

    public static void addManaRemaining(Player owner,int value){
        if (!(owner.getManaRemaining()== 10)){
            owner.setManaRemaining(owner.getManaRemaining()+1);
        } else{
            owner.draw(1);
        }
    }
}

