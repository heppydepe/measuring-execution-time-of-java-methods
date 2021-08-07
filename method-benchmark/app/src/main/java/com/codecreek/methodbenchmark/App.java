package com.codecreek.methodbenchmark;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.ArrayList;

import com.google.common.math.Stats;

public class App {
    public static void main(String[] args) {
        int iterations = 10005;
        int skipFirst  = 5;

        RandomNumbers randomNumbers = new RandomNumbers();
        List<Long> measureList = new ArrayList<>();
        long startTime, endTime;
        try {
            for (int i = 0; i < iterations; i++) {
                startTime = System.nanoTime();
                randomNumbers.writeToFile();
                endTime = System.nanoTime();
                if (i >= skipFirst) {
                    measureList.add(endTime - startTime);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Wall Clock Time:\n\t" + Stats.of(measureList));

        ThreadMXBean tmb = ManagementFactory.getThreadMXBean();
        measureList.clear();
        try {
            for (int i = 0; i < iterations; i++) {
                startTime = tmb.getCurrentThreadCpuTime();
                randomNumbers.writeToFile();
                endTime = tmb.getCurrentThreadCpuTime();
                if (i >= skipFirst) {
                    measureList.add(endTime - startTime);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("CPU Time:\n\t" + Stats.of(measureList));
    }
}
