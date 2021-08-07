package com.codecreek.methodbenchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomNumbers {
    private List<Integer> list;

    public RandomNumbers() {
        Random rand = new Random();
        list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(rand.nextInt());
        }
    }

    public void writeToFile() throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(this.toString());
        fileWriter.close();
    }

    public String toString() {
        return list.toString();
    }
}