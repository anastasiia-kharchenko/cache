package OS.lab3.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceConditionImpl {

    public static int testSpeedPlusTwo(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new PlusTwoRaceCondition(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return PlusTwoRaceCondition.target;

    }

    public static int testSpeedIncrement(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new IncrementRaceCondition(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return IncrementRaceCondition.target;
    }

}

class PlusTwoRaceCondition implements Runnable {
    private final int loops;
    public static int target = 0;

    public PlusTwoRaceCondition(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            target += 2;

        }
    }
}

class IncrementRaceCondition implements Runnable {
    private final int loops;
    public static int target = 0;

    public IncrementRaceCondition(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            target++;

        }
    }
}

