package OS.lab3.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizationImpl {

    public static int testSpeedPlusTwo(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new PlusTwoSynchronization(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return PlusTwoSynchronization.target;

    }

    public static int testSpeedIncrement(int loops, ExecutorService executorService) throws InterruptedException {
        //ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<Object>> todo = new ArrayList<>();
        for (int i = 0; i <3; i++) {
            todo.add(Executors.callable( new IncrementSynchronization(loops)));
        }
        executorService.invokeAll(todo);
        //executorService.shutdown();

        return IncrementSynchronization.target;
    }

}



class PlusTwoSynchronization implements Runnable {
    private final int loops;
    public static int target = 0;

    public PlusTwoSynchronization(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            synchronized (PlusTwoSynchronization.class){
                target += 2;
            }
        }
    }
}

class IncrementSynchronization implements Runnable {
    private final int loops;
    public static int target = 0;

    public IncrementSynchronization(int loops) {
        this.loops = loops;
    }

    @Override
    public void run() {
        for (int i = 0; i < loops; i++) {
            synchronized (IncrementSynchronization.class){
                target++;
            }
        }
    }
}