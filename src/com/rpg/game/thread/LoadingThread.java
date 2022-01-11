package com.rpg.game.thread;

public class LoadingThread extends Thread {
    @Override
    public void run() {
        System.out.println();
        String loadingSentence = "Loading.....";

        for (int i = 0; i < loadingSentence.length(); i++) {
            System.out.print(loadingSentence.charAt(i));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println();
        for (int j = 0; j < 30; j++) {
            System.out.print('■');
            System.out.print('■');
            System.out.print('■');
            try {
                Thread.sleep(10 * 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println();
     }
}
