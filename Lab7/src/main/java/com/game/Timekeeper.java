package com.game;

import java.util.concurrent.atomic.AtomicBoolean;

public class Timekeeper extends Thread {
    private final long startMillis;
    private final int limitSeconds;
    private final AtomicBoolean gameOver;
    private final Object turnLock;

    public Timekeeper(int limitSeconds, AtomicBoolean gameOver, Object turnLock) {
        super("Timekeeper");
        this.limitSeconds = limitSeconds;
        this.gameOver = gameOver;
        this.turnLock = turnLock;
        this.startMillis = System.currentTimeMillis();
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!gameOver.get()) {
            long elapsed = (System.currentTimeMillis() - startMillis) / 1000;
            System.out.println("Elapsed time: " + elapsed + "s");
            if (elapsed >= limitSeconds) {
                System.out.println("Time limit reached. Ending game.");
                gameOver.set(true);
                synchronized (turnLock) {
                    turnLock.notifyAll();
                }
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) { }
        }
    }
}