package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Part2 {
    public static void main(String[] args) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("day3/input.txt"));
            StringBuilder joinedText = new StringBuilder();

            // Define the regex pattern
            Pattern mulPattern = Pattern.compile("(?<=mul\\()\\d+,\\d+(?=\\))");
            Pattern complexPattern= Pattern.compile("do\\((.*?)\\)\\s*(.*?)\\s*don't\\(\\)");
            Pattern dontPattern= Pattern.compile("don't\\(\\)");
            Integer total= 0;

            for (String line : lines) {
                joinedText.append(line);
            }

            Matcher dontMatcher = dontPattern.matcher(joinedText);
            int splitPosition = -1;
            if (dontMatcher.find()) {
                splitPosition = dontMatcher.start();
            }
            
            // Split the joinedText into beginningText and continueText
            String beginningText = joinedText.substring(0, splitPosition);
            String continueText = joinedText.substring(splitPosition);
            
            System.out.println("Beginning text: " + beginningText);
            System.out.println("Continue text: " + continueText);

            Matcher mulMatcher = mulPattern.matcher(beginningText);
            while (mulMatcher.find()) {
                String[] splitedTuple = mulMatcher.group().split(",");
                int multipliedValue = Integer.parseInt(splitedTuple[0]) * Integer.parseInt(splitedTuple[1]);
                System.out.println("Tuple: " + splitedTuple[0] + "," + splitedTuple[1] + " Multiplied value: " + multipliedValue);
                total += multipliedValue;
            }


            Matcher complexMatcher = complexPattern.matcher(continueText);
        
            while (complexMatcher.find()) {
                String validString = complexMatcher.group();
                System.out.println(validString);
                // Split the tuple and multiply the two numbers
                mulMatcher = mulPattern.matcher(validString);
                while (mulMatcher.find()) {
                    String[] splitedTuple = mulMatcher.group().split(",");
                    int multipliedValue = Integer.parseInt(splitedTuple[0]) * Integer.parseInt(splitedTuple[1]);
                    System.out.println("Tuple: " + splitedTuple[0] + "," + splitedTuple[1] + " Multiplied value: " + multipliedValue);
                    total += multipliedValue;
                }
            }

                              

            System.out.println("Total value: " + total);


        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}