package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day3/input.txt"));

            // Define the regex pattern
            Pattern pattern = Pattern.compile("(?<=mul\\()\\d+,\\d+(?=\\))");
            Integer total= 0;
            // Process each line
            for (String line : lines) {
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    String tuple = matcher.group();
                    // Split the tuple and multiply the two numbers
                    String[] splitedTuple = tuple.split(",");
                    int multipliedValue = Integer.parseInt(splitedTuple[0]) * Integer.parseInt(splitedTuple[1]);
                    System.out.println("Tuple: " + tuple + " Multiplied value: " + multipliedValue);
                    total+= multipliedValue;
                }

            }

            // Print the total
            System.out.println("Total value: " + total);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}