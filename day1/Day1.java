package day1;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) {
        String fileName = "day1/input.txt"; // Path to the input file
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            
            // Arrays to store the numbers
            List<Integer> firstNumbers = new ArrayList<>();
            List<Integer> secondNumbers = new ArrayList<>();
            
            // Process each line
            for (String line : lines) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length > 0) {
                    // Store the first number
                    firstNumbers.add(Integer.parseInt(parts[0]));
                    
                    // Store the rest of the numbers
                    for (int i = 1; i < parts.length; i++) {
                        secondNumbers.add(Integer.parseInt(parts[i]));
                    }
                }
            }
            
            // Sort the lists
            List<Integer> sortedFirstNumbers = firstNumbers.stream().sorted().collect(Collectors.toList());
            List<Integer> sortedSecondNumbers = secondNumbers.stream().sorted().collect(Collectors.toList());
            
            // Ensure both lists have the same size for subtraction
            int minSize = Math.min(sortedFirstNumbers.size(), sortedSecondNumbers.size());
            sortedFirstNumbers = sortedFirstNumbers.subList(0, minSize);
            sortedSecondNumbers = sortedSecondNumbers.subList(0, minSize);
            
            // Subtract values and ensure non-negative results
            int totalSubtraction = 0;
            for (int i = 0; i < minSize; i++) {
                totalSubtraction += Math.abs(sortedFirstNumbers.get(i) - sortedSecondNumbers.get(i));
            }
            
            // Print the total of the subtractions
            System.out.println("Total of the subtractions: " + totalSubtraction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}