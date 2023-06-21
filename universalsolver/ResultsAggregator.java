package ru.andrey.problemsolver.universalsolver;

public class ResultsAggregator<T, E> extends Thread {

    private final CommonFieldsUtil<T, E> commonFields;

    public ResultsAggregator(CommonFieldsUtil<T, E> commonFields) {
        this.commonFields = commonFields;
    }

    @Override
    public void run() {
        while(commonFields.getCounterOfAggregatedResults().incrementAndGet()<commonFields.getNumberOfProblems() + 1) {
            try {
                E intermediateResult = commonFields.getIntermediateResults().take();
                commonFields.getConsumer().accept(intermediateResult);
                commonFields.getAllFinishLatch().countDown();
//                System.out.println(Thread.currentThread().getName() + " посчитал сумму промежуточных результатов " + totalResult.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
