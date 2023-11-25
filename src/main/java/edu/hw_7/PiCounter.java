package edu.hw_7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * Расчет числа пи с использованием уравнения окружности x^2 + y^2 = 1.
 * Для это можно взять 1/4 часть окружности и вписать дугу в квадрат со стороной 1.
 * Квадрат помещаем одной строной на ось X, другой стороной на ось Y.
 * ThreadLocalRandom дает точки с координатами <1, т.е. все они лежат внутри квадрата.
 * Далее нужно отследить те, которые оказываются при этом и внутри дуги окружности.
 * Далее делим последнюю величину на общее количество точек.
 **/
@SuppressWarnings("MagicNumber")
public class PiCounter {
    int howMany;
    LongAdder total = new LongAdder();
    LongAdder insideBow = new LongAdder();
    int threadsNumber;
    double piManyThreads;
    double piSingleThread;

    public final double pi = Math.PI;

    double x;
    double y;

    int[] statsInput = new int[] {1, 5, 100, 10_000, 100_000, 1_000_000, 10_000_000};
    double[] statsOutPut = new double[statsInput.length];

    public PiCounter(int howMany, int threadsNumber) {
        this.howMany = howMany;
        this.threadsNumber = threadsNumber;
    }

    public PiCounter() {
    }

    public double countPi() {
        if (threadsNumber > 1) {
            return countPiManyThreads();
        } else {
            return countPiSingleThread(howMany);
        }
    }

    private double countPiSingleThread(int number) {
        int all = 0;
        int inCircle = 0;
        for (int i = 0; i < number; i++) {
            x = ThreadLocalRandom.current().nextFloat();
            y = ThreadLocalRandom.current().nextFloat();
            all++;
            if (x * x + y * y <= 1) {
                inCircle++;
            }
        }
        piSingleThread = 4 * (double) inCircle / (double) all;
        return piSingleThread;
    }

    private void forThreadFun() {
        int all = 0;
        int inCircle = 0;
        for (int i = 0; i < howMany; i++) {
            x = ThreadLocalRandom.current().nextFloat();
            double sqX = Math.pow(x, 2);
            y = ThreadLocalRandom.current().nextFloat();
            double sqY = Math.pow(y, 2);
            all++;
            if (sqX + sqY <= 1) {
                inCircle++;
            }
        }
        insideBow.add(inCircle);
        total.add(all);
    }

    double countPiManyThreads() {
        try (ExecutorService service = Executors.newFixedThreadPool(threadsNumber)) {
            service.execute(this::forThreadFun);
            service.shutdown();
        } finally {
            piManyThreads = 4 * (double) insideBow.sumThenReset() / (double) total.sumThenReset();
        }
       /* List<Thread> threads = new ArrayList<>();
        for (int j = 0; j < threadsNumber; j++) {
            threads.add(new Thread(this::forThreadFun));
        }
        for (Thread t : threads) {
            t.start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        return piManyThreads;
    }

    private void makePiStats() {
        for (int i = 0; i < statsInput.length; i++) {
            double piCounted = countPiSingleThread(statsInput[i]);
            statsOutPut[i] = 100 * ((Math.abs(piCounted - pi) / pi));
        }
    }

    String showPiStats() {
        makePiStats();
        var sb = new StringBuilder();
        sb.append("""
            Кол-во точек     Погрешность, %
                        """);
        sb.append("\n");
        for (int i = 0; i < statsInput.length; i++) {
            sb.append(String.format("%-10s", statsInput[i]));
            sb.append("       ");
            sb.append(String.format("%-30.3f", statsOutPut[i]));
            sb.append("\n");
        }
        return sb.toString();
    }
}


