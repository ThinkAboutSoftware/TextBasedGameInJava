package com.rpg.game.thread;

import com.rpg.game.Store;

public class PotionMakerThread {
    private final long index;
    private Store store;

    public PotionMakerThread(Store store, long index) {
        this.store = store;
        this.index = index;
    }

    public void makePotion(int index) {
        long i = index;
        // NOTE: 짝수 칸일때만 포션 만든다.
        if (i % 2 == 0) {
            store.makePotion(index);
        }
    }

}
