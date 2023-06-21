package ru.andrey.problemsolver.universalsolver;

public class ProblemSolver<T, E> extends Thread {

    private final CommonFieldsUtil<T, E> commonFields;

    public ProblemSolver(CommonFieldsUtil<T, E> commonFields) {
        this.commonFields = commonFields;
    }

    @Override
    public void run() {
        while (commonFields.getCounterOfSolvedProblems().incrementAndGet()<commonFields.getNumberOfProblems() + 1) {
            try {
                T problem = commonFields.getProblems().take();
                E result = commonFields.getFunction().apply(problem);
                commonFields.getIntermediateResults().put(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
