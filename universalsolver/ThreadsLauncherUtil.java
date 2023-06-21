package ru.andrey.problemsolver.universalsolver;

import java.util.List;

public class ThreadsLauncherUtil {

    public static <T extends Thread> void launchThreads (List<T> threads) {
        for (T thread: threads) {
            thread.start();
        }
    }

}
