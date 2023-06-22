package ru.andrey.problemsolver.universalsolver;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SymbolsInStringsProblem {
//    подсчет количества символов: входные задачи - случайные строки из латинских букв и пробелов, цель - посчитать общее количество вхождений каждого символа

    private int numberOfProblems;

    private Map<Character, Integer> totalOccurances = new HashMap<>();
    private ArrayBlockingQueue<String> problems;
    private ArrayBlockingQueue<Map<Character, Integer>> intermediateResults;
    private Supplier<String> supplier = () -> {
        Random random = new Random();
        String problem = "";
        String symbols = "abcdefghijklmnopqrstuvwxyz_ABCDEFGHIJKLMNOPQRSTUVWXUZ";

        int lengthOfText = random.nextInt(100);
        char[] chars = symbols.toCharArray();
        for (int i = 0; i<lengthOfText; i++) {
            int numberOfSymbol = random.nextInt(52);
            problem = problem + chars[numberOfSymbol];
        }
        return problem;
    };

    private Function<String, Map<Character, Integer>> function = (problem) -> {
        Map<Character, Integer> occurances = new HashMap<>();
        for (Character character: problem.toCharArray()) {
//            заглавные и прописные буквы считаются равнозначными
            character = Character.toLowerCase(character);
            if (!occurances.containsKey(character)) {
                occurances.put(character, 1);
            } else {
                Integer increasedValue = occurances.get(character) + 1;
                occurances.put(character, increasedValue);
            }
        }
        return occurances;
    };

    private Consumer<Map<Character, Integer>> consumer = (occurances) -> {
        for (Character character: occurances.keySet()) {
            if (!totalOccurances.containsKey(character)) {
                totalOccurances.put(character, occurances.get(character));
            } else {
                Integer updatedTotalResult = totalOccurances.get(character) + occurances.get(character);
                totalOccurances.put(character, updatedTotalResult);
            }
        }
    };

    public SymbolsInStringsProblem(int numberOfProblems) {
        this.numberOfProblems = numberOfProblems;
        this.problems = new ArrayBlockingQueue<>(numberOfProblems);
        this.intermediateResults = new ArrayBlockingQueue<>(numberOfProblems);
    }


    public Map<Character, Integer> getTotalOccurances() {
        return totalOccurances;
    }

    public ArrayBlockingQueue<String> getProblems() {
        return problems;
    }

    public ArrayBlockingQueue<Map<Character, Integer>> getIntermediateResults() {
        return intermediateResults;
    }

    public Supplier<String> getSupplier() {
        return supplier;
    }

    public Function<String, Map<Character, Integer>> getFunction() {
        return function;
    }

    public Consumer<Map<Character, Integer>> getConsumer() {
        return consumer;
    }
}
