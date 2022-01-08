package com.rpg.game;

public class Wizard extends Character {
    private int mp = 200;
    private final int maxMp = 200;
    private int magic = 20;

    public Wizard(String name) {
        super();
    }

    @Override
    public int getHeroAttackPoint(int slimeDefense) {
        int attackPoint = 0;
        if (mp >= 10) {
            attackPoint = magic(slimeDefense);
        } else {
            System.out.println("mp가 부족해 마법을 쓸 수 없습니다. 기본 공격이 나갑니다.");
            attackPoint = random.nextInt(power + speed) - slimeDefense;
            if (attackPoint < 0) {
                attackPoint = 0;
            }
        }

        return attackPoint;
    }

    private int magic(int slimeDefense) {
        int magicAttackPoint = random.nextInt(magic) - slimeDefense;
        return magicAttackPoint;
    }
}
