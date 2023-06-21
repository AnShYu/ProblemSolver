package ru.andrey.problemsolver.universalsolver;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CommonFieldsUtil<T, E> {

    private final int numberOfProblems;
    private final long pause;
    private final ArrayBlockingQueue<T> problems;
    private final ArrayBlockingQueue<E> intermediateResults;
    private final AtomicInteger counterOfMadeProblems = new AtomicInteger();
    private final AtomicInteger counterOfSolvedProblems = new AtomicInteger();
    private final AtomicInteger counterOfAggregatedResults = new AtomicInteger();

    private final CountDownLatch allFinishLatch;
    private final Supplier<T> supplier;

    private final Function<T, E> function;
    private final Consumer<E> consumer;


    public CommonFieldsUtil(int numberOfProblems, long pause, ArrayBlockingQueue<T> problems,
                            ArrayBlockingQueue<E> intermediateResults, CountDownLatch countDownLatch,
                            Supplier<T> supplier, Function<T, E> function, Consumer<E> consumer) {
        this.numberOfProblems = numberOfProblems;
        this.pause = pause;
        this.problems = problems;
        this.intermediateResults = intermediateResults;
        this.supplier = supplier;
        this.function = function;
        this.consumer = consumer;
        this.allFinishLatch = countDownLatch;
    }

    public int getNumberOfProblems() {
        return numberOfProblems;
    }

    public long getPause() {
        return pause;
    }

    public ArrayBlockingQueue<T> getProblems() {
        return problems;
    }
    public ArrayBlockingQueue<E> getIntermediateResults() {
        return intermediateResults;
    }

    public AtomicInteger getCounterOfMadeProblems() {
        return counterOfMadeProblems;
    }
    public AtomicInteger getCounterOfSolvedProblems() {
        return counterOfSolvedProblems;
    }
    public AtomicInteger getCounterOfAggregatedResults() { return counterOfAggregatedResults; }

    public Supplier<T> getSupplier() {
        return supplier;
    }

    public Function<T, E> getFunction() {
        return function;
    }

    public Consumer<E> getConsumer() {
        return consumer;
    }

    public CountDownLatch getAllFinishLatch() {
        return allFinishLatch;
    }
}
