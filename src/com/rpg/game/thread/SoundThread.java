package com.rpg.game.thread;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundThread {
    private boolean isPlaying;
    Clip clip;
    public SoundThread(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public void turnOn(boolean isPlaying) throws LineUnavailableException, IOException, InterruptedException {
        if (isPlaying) {
            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/wisdom/Documents/train.wav").getAbsoluteFile());
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
    }

    public void turnOff(boolean isPlaying) {
        if (!isPlaying) {
            clip.stop();
        }
    }
}
