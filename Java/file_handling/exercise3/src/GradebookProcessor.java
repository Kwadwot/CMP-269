import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class GradebookProcessor {

    static double calculateAverageScore(int a, int b, int c) {
        return (a + b + c) / 3.0;
    }

    public static void main(String[] args) {
        ArrayList<String> results = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("src/students.txt"))) {
            while(scanner.hasNextLine()) {
                String name = "";
                int grade1, grade2, grade3 = 0;
                double average = 0.0;

                try {
                    String[] tokens = scanner.nextLine().split(" ");

                    if (tokens.length != 4) {
                        throw new InputMismatchException("incorrect number of tokens in this input line");
                    }

                    name = tokens[0]; // keeps the underscore since not instructed otherwise
                    // Assuming grade inputs are within the 0-100 range (might want to check later):
                    grade1 = Integer.parseInt(tokens[1]);
                    grade2 = Integer.parseInt(tokens[2]);
                    grade3 = Integer.parseInt(tokens[3]);

//                    System.out.printf("%s has grades: %d, %d, %d\n", name, grade1, grade2, grade3);
                    average = calculateAverageScore(grade1, grade2, grade3);
                    if (average < 60){
                        throw new LowGradeException("Warning: low grade");
                    }
                    else{
                        results.add(String.format("Student: %s | Average: %.2f\n", name, average));
                    }

                } catch (InputMismatchException e){
                    System.out.println("Warning: wrong input: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Warning: wrong number format: " + e.getMessage());
                } catch (LowGradeException e) {
                    // Assumes the warning is printed after the Average:
                    results.add(String.format("Student: %s | Average: %.2f | %s\n", name, average, e.getMessage()));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }

        try(FileWriter writer = new FileWriter("src/grades_report.txt")) {
            for (String report: results){
                writer.write(report);
            }
        } catch (IOException e) {
            System.out.println("Failed to write to file");
            e.printStackTrace();
        }
    }
}
