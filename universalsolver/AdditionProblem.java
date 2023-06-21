package ru.andrey.problemsolver.universalsolver;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AdditionProblem {

    private int numberOfProblems;
    private AtomicInteger totalResult = new AtomicInteger();
    private ArrayBlockingQueue<Problem> problems;
    private ArrayBlockingQueue<Integer> intermediateResults;
//    private ArrayBlockingQueue<Problem> problems = new ArrayBlockingQueue<>(numberOfProblems);
//    private ArrayBlockingQueue<Integer> intermediateResults = new ArrayBlockingQueue<>(numberOfProblems);

    private Supplier<Problem> supplier = () -> {
        Random random = new Random();
        int x = random.nextInt(100);
        int y = random.nextInt(100);
        Problem problem = new Problem(x, y);
        return problem;
    };

    private Function<Problem, Integer> function = (problem) -> {
        Integer result = problem.getX() + problem.getY();
        return result;
    };

    private Consumer<Integer> consumer = (result) -> {
        getTotalResult().addAndGet(result);
    };

    public AdditionProblem(int numberOfProblems) {
        this.numberOfProblems = numberOfProblems;
        this.problems = new ArrayBlockingQueue<>(numberOfProblems);
        this.intermediateResults = new ArrayBlockingQueue<>(numberOfProblems);
    }



    public AtomicInteger getTotalResult() {
        return totalResult;
    }

    public ArrayBlockingQueue<Problem> getProblems() {
        return problems;
    }

    public ArrayBlockingQueue<Integer> getIntermediateResults() {
        return intermediateResults;
    }

    public Supplier<Problem> getSupplier() {
        return supplier;
    }

    public Function<Problem, Integer> getFunction() {
        return function;
    }

    public Consumer<Integer> getConsumer() {
        return consumer;
    }

    public static class Problem {
        int x;
        int y;

        public Problem(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "Problem{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

}
