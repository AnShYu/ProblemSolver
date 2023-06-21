package ru.andrey.problemsolver.universalsolver;

import java.util.ArrayList;
import java.util.List;

public class ThreadsCreatorUtil<T, E> {

    public List<ProblemMaker<T,E>> createProblemMakers (int numberOfInstances, CommonFieldsUtil<T, E> commonFields) {
        List<ProblemMaker<T,E>> problemMakers = new ArrayList<>();
        for (int i = 0; i < numberOfInstances; i++) {
            problemMakers.add(new ProblemMaker<T,E>(commonFields));
        }
        return problemMakers;
    }

    public List<ProblemSolver<T,E>> createProblemSolvers (int numberOfInstances, CommonFieldsUtil<T, E> commonFields) {
        List<ProblemSolver<T,E>> problemSolvers = new ArrayList<>();
        for (int i = 0; i < numberOfInstances; i++) {
            problemSolvers.add(new ProblemSolver<T,E>(commonFields));
        }
        return problemSolvers;
    }

    public List<ResultsAggregator<T,E>> createResultsAggregators (int numberOfInstances, CommonFieldsUtil<T, E> commonFields) {
        List<ResultsAggregator<T,E>> resultsAggregators = new ArrayList<>();
        for (int i = 0; i < numberOfInstances; i++) {
            resultsAggregators.add(new ResultsAggregator<T,E>(commonFields));
        }
        return resultsAggregators;
    }

}
