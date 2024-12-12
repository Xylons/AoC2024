package day11;

import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Day11 <fileName> <iterations>");
            return;
        }
        String fileName = args[0];
        int iterations = Integer.parseInt(args[1]);

        List<Integer> numbers = new ArrayList<>();

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            // Check if the file is not empty
            if (!lines.isEmpty()) {
                // Read the first line
                String firstLine = lines.get(0);

                // Split the line into individual numbers
                String[] numberStrings = firstLine.split("\\s+");

                // Convert the strings to integers and store them in the ArrayList
                for (String numberString : numberStrings) {
                    numbers.add(Integer.parseInt(numberString));
                }
            }
            System.out.println(numbers.toString());
            // Perform the specified number of iterations
            List<Integer> resultList = new ArrayList<>(numbers);
            for (int i = 0; i < iterations; i++) {
                List<Integer> newResultList = new ArrayList<>();
                for (int number : resultList) {
                    newResultList.addAll(stoneConditions(number));
                }
                resultList = newResultList;
            }
            // Print the numbers to verify
            System.out.println("Numbers: " + resultList.toString().replace(",", "") + "\n" + "Count: " + resultList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static List<Integer> stoneConditions(int number) {
        List<Integer> newNumbers = new ArrayList<>();

            if (number == 0) {
                newNumbers.add(1);
            } else {
                String numberStr = String.valueOf(number);
                if (numberStr.length() % 2 == 0) {
                    int mid = numberStr.length() / 2;
                    int leftHalf = Integer.parseInt(numberStr.substring(0, mid));
                    int rightHalf = Integer.parseInt(numberStr.substring(mid));
                    newNumbers.add(leftHalf);
                    newNumbers.add(rightHalf);
                } else {
                    newNumbers.add(number * 2024);
                }
            }
        

        return newNumbers;
    }
}

