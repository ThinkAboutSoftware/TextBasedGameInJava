package com.rpg.game;

public class BlackSlime extends Slime{

    private int hp = 60;
    private int skillPoint = 3;

    @Override
    public int getAttackPoint(int defense) {
        long index = game.getIndex();
        int attackPoint = random.nextInt((int) (index * 2));
        if (fire()) {
            attackPoint*=skillPoint;
        }
        return attackPoint;
    }

    public boolean fire() {

        int n = random.nextInt(2);
        if (n == 0) {
            System.out.println("======================================================");
            System.out.println("Black Slime Fire 발동 ====> 데미지 "+skillPoint+"배");
            System.out.println("======================================================");
            return true;
        }
        return false;
    }
}
