package OS.lab3;

import OS.lab1.async.AsyncEchoClient;
import OS.lab3.impl.AtomicImpl;
import OS.lab3.impl.RaceConditionImpl;
import OS.lab3.impl.SynchronizationImpl;
import OS.lab3.impl.VolatileImpl;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import org.junit.*;
import org.junit.rules.TestRule;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class Bechmark {

    private static ExecutorService executorService;
    private static int cycles;

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @BeforeClass
    public static void setup(){
        executorService = Executors.newFixedThreadPool(3);
        cycles = 8_000_000;
    }

    @AfterClass
    public static void afterClass(){
        executorService.shutdown();
    }

    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void raceIncTest() throws InterruptedException {
        RaceConditionImpl.testSpeedIncrement(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void racePlusTest() throws InterruptedException {
        RaceConditionImpl.testSpeedPlusTwo(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void volatileIncTest() throws InterruptedException {
        VolatileImpl.testSpeedIncrement(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void volatilePlusTest() throws InterruptedException {
        VolatileImpl.testSpeedPlusTwo(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void syncIncTest() throws InterruptedException {
        SynchronizationImpl.testSpeedIncrement(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void syncPlusTest() throws InterruptedException {
        SynchronizationImpl.testSpeedPlusTwo(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void atomicIncTest() throws InterruptedException {
        AtomicImpl.testSpeedIncrement(cycles, executorService);

    }
    @Test
    @BenchmarkOptions(warmupRounds = 1, benchmarkRounds = 10)
    public void atomicPlusTest() throws InterruptedException {
        AtomicImpl.testSpeedPlusTwo(cycles, executorService);

    }

}
