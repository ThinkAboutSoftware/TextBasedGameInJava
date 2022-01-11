package com.rpg.game.thread;

import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class TurnOnSound  {

    private boolean isPlaying;
    private SoundThread soundThread;

    public TurnOnSound(boolean isPlaying, SoundThread soundThread) {
        this.isPlaying = isPlaying;
        this.soundThread = soundThread;
    }

    public void On(boolean isPlaying) {
        try {
            soundThread.turnOn(isPlaying);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
