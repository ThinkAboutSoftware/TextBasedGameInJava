package com.rpg.game;

import java.util.Random;

public class Slime {

    private int hp = 50;
    private int skillPoint = 0;
    private boolean isDead = false;
    Random random = new Random();
    GameLogic game = new GameLogic();

    public int getAttackPoint(int defense) {
        long index = game.getIndex();
        int attackPoint = random.nextInt((int) (index * 3));
        return attackPoint;
    }

    public int getDefensePoint() {
        long index = game.getIndex();
        return random.nextInt((int) (index * 3));
    }

    public int getHpPoint(int attackPoint) {
        hp -= attackPoint;
        if (hp < 0) {
            hp = 0;
        }

        return hp;
    }

    public boolean isDead() {
        if (hp <= 0) {
            isDead = true;
        }

        return isDead;
    }

    public int dropTheMoney() {
        long index = game.getIndex();
        return random.nextInt((int) (index * 100));
    }

    public int dropTheExp() {
        long index = game.getIndex();
        return random.nextInt((int) (index * 100));
    }

}
