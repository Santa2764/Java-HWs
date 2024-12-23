package com.company.entity;

import com.company.Race;
import com.company.utils.TimeWork;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class RaceCarRunnable extends Car implements Runnable {
    private static final String SHOW_TEMPLATE = "%s => speed: %.1f; progress: %.1f/%.1f";

    private float passed;
    private final float distance;
    private boolean isFinish;
    private long finishTime;
    private final CountDownLatch latch;

    public RaceCarRunnable(String name, int maxSpeed, float distance, CountDownLatch latch) {
        super(name, maxSpeed);
        this.distance = distance;
        this.passed = 0;
        this.isFinish = false;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (!isFinish) {
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            float currSpeed = getRandomSpeed();
            passed += (float)(currSpeed / 3.6);  // расчет пройденной дистанции за 1 сек (из км/ч в м/с)

            String str = String.format(SHOW_TEMPLATE, name, currSpeed, passed, distance);
            System.out.println(str);

            // если гонка успешно пройдена
            if (passed >= distance) {
                isFinish = true;
                latch.countDown();
                finishTime = System.currentTimeMillis() - Race.startRaceTime.get();
                String formatTime = TimeWork.convertToTime(finishTime);
                System.out.println("\n----\n" + name + " finished the race in " + formatTime + " seconds\n----");
            }
        }
    }

    public float getRandomSpeed() {
        Random rand = new Random();
        return rand.nextFloat((float) maxSpeed / 2, maxSpeed);
    }

    public float getPassed() {
        return passed;
    }

    public float getDistance() {
        return distance;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setPassed(float passed) {
        if (passed > 0) {
            this.passed = passed;
        }
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }
}
