package com.rpg.game;

public class BossSlime extends Slime{

    private int hp = 100;
    private int skillPoint = 5;

    @Override
    public int getAttackPoint(int defense) {
        long index = game.getIndex();
        int attackPoint = random.nextInt((int) (index * 7));
        if (bossSlimePowerSkill()) {
            attackPoint *= skillPoint;
        }
        return attackPoint;
    }


    public boolean bossSlimePowerSkill() {
        int n = random.nextInt(2);
        if (n == 0) {
            System.out.println("======================================================");
            System.out.println("Boss Slime Power Skill 발동 ====> 데미지 "+skillPoint+"배");
            System.out.println("======================================================");
            return true;
        }
        return false;
    }
}
