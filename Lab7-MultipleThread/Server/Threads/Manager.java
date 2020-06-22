package Threads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

public class Manager {
    public static ReentrantLock reentrantLock = new ReentrantLock();
    public static ExecutorService fixedTheadPool = Executors.newFixedThreadPool(10);
    public static ForkJoinPool forkJoinPool = new ForkJoinPool();
    public Manager(){};
}
