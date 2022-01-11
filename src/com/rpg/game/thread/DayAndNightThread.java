package com.rpg.game.thread;

import com.rpg.game.Character;

public class DayAndNightThread extends Thread {

    private long timeInterval;
    private Character hero;

    public DayAndNightThread(long timeInterval, Character hero) {
        this.timeInterval = timeInterval;
        this.hero = hero;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 29; i++) {

                if (i % 2 == 0) {
                    hero.getNightPowerPoint();
                    System.out.println("============================");
                    System.out.println("밤입니다. 히어로의 공격력이 0.7배가 됩니다.");
                    System.out.println("============================");
                } else {
                    hero.getDayPowerPoint();
                    System.out.println("============================");
                    System.out.println("낮입니다. 히어로의 공격력이 1.5배가 됩니다.");
                    System.out.println("============================");
                }

                Thread.sleep(timeInterval);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
