package OS.lab3.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicImpl {


    public static int testSpeedPlusTwo(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new PlusTwoAtomic(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return PlusTwoAtomic.target.get();

    }

    public static int testSpeedIncrement(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new IncrementAtomic(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return IncrementAtomic.target.get();
    }
}

class PlusTwoAtomic implements Runnable {
    private final int loops;
    public static AtomicInteger target = new AtomicInteger(0);

    public PlusTwoAtomic(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            target.addAndGet(2);

        }
    }
}

class IncrementAtomic implements Runnable {
    private final int loops;
    public static AtomicInteger target = new AtomicInteger(0);

    public IncrementAtomic(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            target.incrementAndGet();

        }
    }
}


