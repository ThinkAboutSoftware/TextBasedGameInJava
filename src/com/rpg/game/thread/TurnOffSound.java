package com.rpg.game.thread;

public class TurnOffSound {

    private boolean isPlaying;
    private SoundThread soundThread;

    public TurnOffSound(boolean isPlaying, SoundThread soundThread) {
        this.isPlaying = isPlaying;
        this.soundThread = soundThread;
    }

    public void Off(boolean isPlaying) {
        soundThread.turnOff(isPlaying);
    }
}
