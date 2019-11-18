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
                if(minion.getHealthPoint() == 0){
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
        if (minion.getHealthPoint() == 0){
            player.draw(1);
        }
    }

    public static void theLolCatCall(int minionId,int nbMinionToSpawn, Board theBoard){
        theBoard.addMinion()
    }

}
