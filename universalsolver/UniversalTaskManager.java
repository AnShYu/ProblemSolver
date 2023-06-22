package ru.andrey.problemsolver.universalsolver;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class UniversalTaskManager {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество задач:");
        int numberOfProblems = scanner.nextInt();
        System.out.println("Введите интервал между созданием задач (сек.):");
        long pause = scanner.nextLong()*1000l;
        System.out.println("Введите количество потоков каждого типа:");
        int numberOfThreads = scanner.nextInt();

//        AdditionProblem additionProblem = new AdditionProblem(numberOfProblems);
//
//        CommonFieldsUtil<AdditionProblem.Problem, Integer> commonFields = new CommonFieldsUtil<>(numberOfProblems, pause,
//                additionProblem.getProblems(), additionProblem.getIntermediateResults(),
//                new CountDownLatch(numberOfProblems), additionProblem.getSupplier(), additionProblem.getFunction(),
//                additionProblem.getConsumer());
//
//        ThreadsCreatorUtil<AdditionProblem.Problem, Integer> threadCreator = new ThreadsCreatorUtil<>();
//
//        List<ProblemMaker<AdditionProblem.Problem, Integer>> problemMakers = threadCreator.createProblemMakers(numberOfThreads,
//                commonFields);
//        List<ProblemSolver<AdditionProblem.Problem, Integer>> problemSolvers =
//                threadCreator.createProblemSolvers(numberOfThreads, commonFields);
//        List<ResultsAggregator<AdditionProblem.Problem, Integer>> resultsAggregators =
//                threadCreator.createResultsAggregators(numberOfThreads, commonFields);
//
//        ThreadsLauncherUtil.launchThreads(problemMakers);
//        ThreadsLauncherUtil.launchThreads(problemSolvers);
//        ThreadsLauncherUtil.launchThreads(resultsAggregators);
//
//        commonFields.getAllFinishLatch().await();
//        System.out.println("Итоговый результат: " + additionProblem.getTotalResult());


        SymbolsInStringsProblem symbolsInStringsProblem = new SymbolsInStringsProblem(numberOfProblems);

        CommonFieldsUtil<String, Map<Character, Integer>> commonFields = new CommonFieldsUtil<>(numberOfProblems, pause,
                symbolsInStringsProblem.getProblems(), symbolsInStringsProblem.getIntermediateResults(),
                new CountDownLatch(numberOfProblems), symbolsInStringsProblem.getSupplier(),
                symbolsInStringsProblem.getFunction(), symbolsInStringsProblem.getConsumer());

        ThreadsCreatorUtil<String, Map<Character, Integer>> threadCreator = new ThreadsCreatorUtil<>();

        List<ProblemMaker<String, Map<Character, Integer>>> problemMakers = threadCreator.createProblemMakers(numberOfThreads,
                commonFields);
        List<ProblemSolver<String, Map<Character, Integer>>> problemSolvers =
                threadCreator.createProblemSolvers(numberOfThreads, commonFields);
        List<ResultsAggregator<String, Map<Character, Integer>>> resultsAggregators =
                threadCreator.createResultsAggregators(numberOfThreads, commonFields);

        ThreadsLauncherUtil.launchThreads(problemMakers);
        ThreadsLauncherUtil.launchThreads(problemSolvers);
        ThreadsLauncherUtil.launchThreads(resultsAggregators);

        commonFields.getAllFinishLatch().await();
        System.out.println("Итоговый результат: " + symbolsInStringsProblem.getTotalOccurances());
    }
}
