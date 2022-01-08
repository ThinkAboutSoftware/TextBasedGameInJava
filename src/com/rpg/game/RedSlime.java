package com.rpg.game;


public class RedSlime extends Slime {

    @Override
    public int getAttackPoint(int defense) {
        long index = game.getIndex();
        int attackPoint = random.nextInt((int) (index * 10));
        return attackPoint;
    }
}
