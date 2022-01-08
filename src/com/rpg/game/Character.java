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
    public void checkLevelUp() {

        if (exp >= 20) {
            System.out.println("LEVEL UP");
            this.level++;
            this.hp = maxHp;
            this.power += 10;
            this.defense += 10;
            this.exp = 0;
        }

    }

    public void getMoney(int money) {
        this.money += money;
    }
    public void getExp(int exp) {
        this.exp += exp;
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
        System.out.println("======================================================");
    }
}
