package ru.andrey.problemsolver.universalsolver;

public class ProblemMaker<T, E> extends Thread {

    private final CommonFieldsUtil<T, E> commonFields;

    public ProblemMaker(CommonFieldsUtil<T, E> commonFields) {
        this.commonFields = commonFields;
    }

    @Override
    public void run() {
        while (commonFields.getCounterOfMadeProblems().incrementAndGet() < commonFields.getNumberOfProblems() + 1) {
            T problem = commonFields.getSupplier().get();
            try {
                commonFields.getProblems().put(problem);
                System.out.println(Thread.currentThread().getName() + " Создал и положил проблему: " + problem);
                Thread.sleep(commonFields.getPause());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
