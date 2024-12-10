package day2;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day2Part2 {
    public static void main(String[] args) {
        String fileName = "day2/input.txt";
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            
            int safeSequenceCount = 0;
            List<List<Integer>> safeSequences = new ArrayList<>();

            // Process each line
            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length > 0) {
                    List<Integer> numbers = new ArrayList<>();
                    for (String part : parts) {
                        numbers.add(Integer.parseInt(part));
                    }
                    int safeNumbers= 0;
                    
                    for(int i = 0; i < numbers.size(); i++) {
                        List<Integer> numbersCopy = new ArrayList<>(numbers);
                        numbersCopy.remove(i);
                        System.out.println("numbersCopy " + numbersCopy);
                        if (isSafeSequence(numbersCopy)) {
                            safeNumbers++;
                            System.out.println("safeNumbers " + safeNumbers);
                        }
                        if(safeNumbers >= 1 ) {
                            safeSequenceCount++;
                            safeSequences.add(numbers);
                            System.out.println("safeSequenceCount " + safeSequenceCount);
                            safeNumbers=0;
                            break;
                        }
                    }
                    
                }
            }
            
            // Print the total number of safe sequences
            System.out.println("Total number of safe sequences: " + safeSequenceCount);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isSafeSequence(List<Integer> numbers) {
        // Check if the sequence is safe
        boolean isSafe = true;
        for (int i = 1; i < numbers.size(); i++) {
            int diff = Math.abs(numbers.get(i) - numbers.get(i - 1));
            if (diff > 3 || diff == 0) {
                isSafe = false;
                break;
            }
        }
    
        // Check if the sequence is strictly increasing or decreasing
        if (isSafe) {
            boolean isIncreasing = true;
            boolean isDecreasing = true;
            for (int i = 1; i < numbers.size(); i++) {
                if (numbers.get(i) > numbers.get(i - 1)) {
                    isDecreasing = false;
                } else if (numbers.get(i) < numbers.get(i - 1)) {
                    isIncreasing = false;
                }
            }
            if (isIncreasing || isDecreasing) {
                return true;
            }
        }
        return false;
    }

}

