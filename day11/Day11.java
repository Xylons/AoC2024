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
            // Perform the specified number of iterations
            List<BigInteger> resultList = new ArrayList<>(numbers);
            for (int i = 0; i < iterations; i++) {
                List<BigInteger> newResultList = new ArrayList<>();
                for (BigInteger number : resultList) {
                    newResultList.addAll(stoneConditions(number));
                }
                resultList = newResultList;
            }
            // Print the numbers to verify
            System.out.println("Number of numbers: " + resultList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static List<BigInteger> stoneConditions(BigInteger number) {
        List<BigInteger> newNumbers = new ArrayList<>();

        if (number.equals(BigInteger.ZERO)) {
            newNumbers.add(BigInteger.ONE);
            //System.out.println("Number: 1");
        } else {
            String numberStr = number.toString();
            int digits = numberStr.length();
            if (digits % 2 == 0) {
                BigInteger front = new BigInteger(numberStr.substring(0, digits / 2));
                BigInteger back = new BigInteger(numberStr.substring(digits / 2));
                newNumbers.add(front);
                newNumbers.add(back);
                //System.out.println("Front: " + front);
                //System.out.println("Back: " + back);
            } else {
                newNumbers.add(number.multiply(BigInteger.valueOf(2024)));
                //System.out.println("Number * 2024: " + number.multiply(BigInteger.valueOf(2024)));
            }
        }

        return newNumbers;
    }
}
