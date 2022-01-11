package com.rpg.game.thread;

import com.rpg.game.Character;

public class helpNPCThread extends Thread {

    private Character hero;
    public helpNPCThread(Character hero) {
        this.hero = hero;
    }

    @Override
    public void run() {
        hero.getBonusStat();
    }
}
