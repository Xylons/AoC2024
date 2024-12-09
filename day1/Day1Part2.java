package day1;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Day1Part2 {
    public static void main(String[] args) {
        String fileName = "day1/input.txt"; // Path to the input file
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            
            // Arrays to store the numbers
            List<Integer> firstNumbers = new ArrayList<>();
            Map<Integer, Integer> secondNumbersCount = new HashMap<>();

            // Process each line
            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length > 0) {
                    // Store the first number
                    firstNumbers.add(Integer.parseInt(parts[0]));
                    
                    // Store the rest of the numbers as a tuple
                    for (int i = 1; i < parts.length; i++) {
                        int number = Integer.parseInt(parts[i]);
                        secondNumbersCount.put(number, secondNumbersCount.getOrDefault(number, 0) + 1);
                    }
                }
            }
            
            // Sort the lists
            List<Integer> sortedFirstNumbers = firstNumbers.stream().sorted().collect(Collectors.toList());
            
            // Multiply values with the similarity
            int totalSimilarityValue = 0;
            for (int firstNumber : sortedFirstNumbers) {
                totalSimilarityValue += firstNumber * secondNumbersCount.getOrDefault(firstNumber, 0);
            }
            
            // Print the total of the similarity
            System.out.println("Total of the similarity: " + totalSimilarityValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}