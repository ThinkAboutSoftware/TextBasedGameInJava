package com.rpg.game;

public class BlueSlime extends Slime {

    private int hp = 55;
    private int skillPoint = 2;
    @Override
    public int getAttackPoint(int defense) {
        long index = game.getIndex();
        int attackPoint = random.nextInt((int) (index * 5));
        if (waterPower()) {
            attackPoint*=skillPoint;
        }
        return attackPoint;
    }

    public boolean waterPower() {

        int n = random.nextInt(2);
        if (n == 0) {
            System.out.println("======================================================");
            System.out.println("Blue Slime WaterPower 발동 ====> 데미지 "+skillPoint+"배");
            System.out.println("======================================================");
            return true;
        }
        return false;
    }
}
