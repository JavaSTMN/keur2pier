package models;

public interface IFighter {
    boolean hasAttacked();
    void newTurn();
    void attack(IFighter target);
    void takeDamage(int damage);
    int getAttack();
    void die();
}
