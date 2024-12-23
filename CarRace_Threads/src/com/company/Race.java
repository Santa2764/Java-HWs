package com.company;

import com.company.entity.RaceCarRunnable;
import com.company.utils.TimeWork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Race {
    private static final int MAX_NUM_LOOP = 3;
    private static final int COUNT_CARS = 3;

    public static List<RaceCarRunnable> cars;
    public static List<Thread> threadCars;
    public static AtomicLong startRaceTime;

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(COUNT_CARS);

        cars = new ArrayList<>();
        cars.add(new RaceCarRunnable("DanCar_1", 350, 300, latch));
        cars.add(new RaceCarRunnable("DanCar_2", 150, 300, latch));
        cars.add(new RaceCarRunnable("DanCar_3", 250, 300, latch));

        // создаём потоки
        threadCars = new ArrayList<>();
        for (RaceCarRunnable car : cars) {
            threadCars.add(new Thread(car));
        }

        startRace(threadCars);
        startRaceTime = new AtomicLong(System.currentTimeMillis());
        try {
            latch.await();  // ожидаем пока все машины доедут до финиша

            System.out.println();
            for (RaceCarRunnable car : cars) {
                System.out.println(car.getName() + " FINISHED");
            }
            showWinner(cars);  // вывод победителя
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException in Race.main: " + e.getMessage());
        }
    }

    public static void startRace(final List<Thread> cars) {
        new Thread(() -> {
            try {
                for (int i = MAX_NUM_LOOP; i > 0; i--) {
                    System.out.print(i + "... ");
                    Thread.sleep(1000);
                }
                System.out.println("GO!!!\n");

                for (Thread car : cars) {
                    car.start();
                }
            }
            catch (InterruptedException e) {
                System.out.println("InterruptedException in Race.startRace: " + e.getMessage());
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    private static void showWinner(final List<RaceCarRunnable> cars) {
        if (cars == null || cars.isEmpty()) {
            System.out.println("Car objects have not been created...");
            return;
        }

        RaceCarRunnable winnerCar = cars.getFirst();
        for (int i = 1; i < cars.size(); i++) {
            RaceCarRunnable car = cars.get(i);
            if (car.getFinishTime() < winnerCar.getFinishTime()) {
                winnerCar = car;
            }
        }

        String formatTime = TimeWork.convertToTime(winnerCar.getFinishTime());
        System.out.println("\n!!! Car <" + winnerCar.getName() + "> is winner to " + formatTime + " seconds !!!");
    }
}
