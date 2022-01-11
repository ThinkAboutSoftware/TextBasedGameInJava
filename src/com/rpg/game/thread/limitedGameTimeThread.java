package com.rpg.game.thread;

public class limitedGameTimeThread extends Thread {

    private long timeInterval;

    public limitedGameTimeThread(long timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 29; i++) {

                // NOTE: 하루는 1분 (30초 낮 + 30초 밤) 14일(-> 14분) 안에 구출해야 성공한다.
                if (i == 14) {
                    System.out.println("============================");
                    System.err.println("7일이 경과했습니다. 14일 안에 VIP를 구출하지 못하면 게임은 그대로 끝이 납니다.");
                    System.out.println("============================");
                }

                if (i == 29) {
                    System.out.println("============================");
                    System.err.println("14일이 경과했습니다.");
                    System.err.println("GAME OVER");
                    System.out.println("============================");
                    System.exit(0);
                }

                Thread.sleep(timeInterval);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
