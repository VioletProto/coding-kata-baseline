package edu.neumont.csc150.controllers;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

//This Part of my code was written by chatGPT and also googled to get working

public class MusicController {
    private Clip clip;
    private Thread musicThread;
    private volatile boolean isPlaying = false;

    public MusicController(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.err.println("Error: File not found - " + filePath);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (clip != null) {
            stop();
            isPlaying = true;
            musicThread = new Thread(() -> {
                clip.setFramePosition(0);
                clip.start();
                while (isPlaying && clip.isRunning()) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            musicThread.setDaemon(true);
            musicThread.start();
        }
    }

    public void stop() {
        if (clip != null && isPlaying) {
            isPlaying = false;
            clip.stop();
            clip.flush();
            clip.setFramePosition(0);
        }
        if (musicThread != null) {
            musicThread.interrupt();
        }
    }

    public void loop() {
        if (clip != null) {
            stop();
            isPlaying = true;

            musicThread = new Thread(() -> {
                clip.setFramePosition(0);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                while (isPlaying) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            musicThread.setDaemon(true);
            musicThread.start();
        }
    }

}
