package day11;

import java.math.BigInteger;
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

        List<BigInteger> numbers = new ArrayList<>();

        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(fileName));

            // Check if the file is not empty
            if (!lines.isEmpty()) {
                // Read the first line
                String firstLine = lines.get(0);

                // Split the line into individual numbers
                String[] numberStrings = firstLine.split("\\s+");

                // Convert the strings to BigInteger and store them in the ArrayList
                for (String numberString : numberStrings) {
                    numbers.add(new BigInteger(numberString));
                }
            }
            System.out.println(numbers.toString());
            // Initialize total
            BigInteger total = BigInteger.ZERO;
            // Perform the specified number of iterations for each number
            for (BigInteger number : numbers) {
                total = total.add(stoneConditions(number, iterations));
            }
            // Print the total to verify
            System.out.println("Total number of numbers: " + total);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static BigInteger stoneConditions(BigInteger number, int iterations) {
        if (iterations == 0) {
            return number;
        }

        BigInteger total = BigInteger.ZERO;
        if (number.equals(BigInteger.ZERO)) {
            return stoneConditions(BigInteger.ONE, iterations - 1);
        } else {
            String numberStr = number.toString();
            int digits = numberStr.length();
            if (digits % 2 == 0) {
                BigInteger front = new BigInteger(numberStr.substring(0, digits / 2));
                BigInteger back = new BigInteger(numberStr.substring(digits / 2));
                return stoneConditions(front, iterations - 1).add(stoneConditions(back, iterations - 1));
            } else {
                BigInteger mult = number.multiply(BigInteger.valueOf(2024));
                total = total.add(stoneConditions(mult,iterations - 1));
                return total;
            }
        }
    }
}
