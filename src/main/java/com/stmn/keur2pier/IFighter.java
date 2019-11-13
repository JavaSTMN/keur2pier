package com.stmn.keur2pier;

public interface IFighter {
    boolean hasAttacked();
    void newTurn();
    void attack(IFighter target);
    void takeDamage(int damage);
    int getAttack();
    void die();
}
