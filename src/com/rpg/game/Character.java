package com.rpg.game;

import java.util.Random;

public class Character {

    Random random = new Random();

    int magic = 0;
    String name;
    int hp = 200;
    int maxHp = 200;
    int mp = 0;
    public int maxMp;
    int defense = 55;
    int power = 55;
    int speed = 55;
    int level = 1;
    int exp = 0;
    int money = 100000;
    boolean isDead = false;

    public void getDayPowerPoint() {
        this.power *= 1.7;
    }

    public void getNightPowerPoint() {
        this.power *= 0.5;
    }


    public int getHeroAttackPoint(int slimeDefense) {
        int attackPoint = random.nextInt(power + speed) - slimeDefense;
        if (attackPoint < 0) {
            attackPoint = 0;
        }
        return attackPoint;
    }

    public boolean isDead() {
        if (hp <= 0) {
            isDead = true;
        }

        return isDead;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getHeroDefensePoint() {
        return random.nextInt(this.defense);
    }

    public int getHeroHpPoint(int attackPoint) {
        hp -= attackPoint;

        if (hp < 0) {
            hp = 0;
        }

        return hp;
    }

    public boolean checkLevelUp() {
        boolean levelUp = this.exp >= 20;
        if (levelUp) {
            this.level++;
            this.hp = maxHp;
            this.power += 10;
            this.defense += 10;
            System.out.println("LEVEL UP!");
            System.out.println("레벨: " + this.getLevel());
            System.out.println("경험치: +" + this.getExp(exp));
            System.out.println("돈: +" + this.getMoney(money));

            this.exp = 0;
            return true;
        }
        return false;
    }

    public void getBonusStat() {
        if (this.getLevel() % 3 == 0 && this.exp == 0) {

            int newPower = random.nextInt(this.power);
            this.power += newPower;
            int newDefense = random.nextInt(this.defense);
            this.defense += newDefense;

            System.out.println("======================================================");
            System.out.println("도움NPC의 특별 보너스 능력치 당첨!");
            System.out.println("======================================================");
            System.out.println("BONUS STAT");
            System.out.println("Power: +" + newPower);
            System.out.println("Defense: +" + +newDefense);
        }
    }

    public int getMoney(int money) {
        return this.money += money;
    }

    public int getExp(int exp) {
        return this.exp += exp;
    }

    public int getLevel() {
        return this.level;
    }

    public void getInfo() {
        System.out.println("======================================================");
        System.out.println("히어로 상태창");
        System.out.println("level = " + level);
        System.out.println("hp = " + hp);
        System.out.println("maxHp = " + maxHp);
        System.out.println("power = " + power);
        System.out.println("speed = " + speed);
        System.out.println("defense = " + defense);
        System.out.println("mp = " + mp);
        System.out.println("maxMp = " + maxMp);
        System.out.println("magic = " + magic);
        System.out.println("money = " + money);
        System.out.println("exp = " + exp);
        System.out.println("======================================================");
    }
}
