package OS.lab3.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatileImpl {

    public static int testSpeedPlusTwo(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new PlusTwoVolatile(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return PlusTwoVolatile.target;

    }

    public static int testSpeedIncrement(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new IncrementVolatile(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return IncrementVolatile.target;
    }
}

class PlusTwoVolatile implements Runnable {
    private final int loops;
    public static volatile int target = 0;

    public PlusTwoVolatile(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            target += 2;

        }
    }
}

class IncrementVolatile implements Runnable {
    private final int loops;
    public static volatile int target = 0;

    public IncrementVolatile(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            target++;

        }
    }
}
