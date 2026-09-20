import java.io.*;
import java.util.ArrayList;

public class GradeAnalyzer {

    static int invalidLines = 0;
    public static void main(String[] args) {

        ArrayList<Integer> scores = readScores("scores.txt");

        double avg = calculateAverage(scores);

        int high = Integer.MIN_VALUE;
        int low = Integer.MAX_VALUE;

        for (int s : scores) {
            if (s > high) high = s;
            if (s < low) low = s;
        }

        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;

        for (int s : scores) {
            if (s >= 90) countA++;
            else if (s >= 80) countB++;
            else if (s >= 70) countC++;
            else if (s >= 60) countD++;
            else countF++;
        }

        writeReport(scores, avg, high, low, "report.txt",
                    countA, countB, countC, countD, countF);
    }

    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) continue;

                try {
                    int score = Integer.parseInt(line);
                    list.add(score);
                } catch (NumberFormatException e) {
                    invalidLines++;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return list;
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) return 0.0;

        double total = 0;
        for (int s : scores) total += s;

        return total / scores.size();
    }

    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile,
                                   int countA, int countB, int countC,
                                   int countD, int countF) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            writer.write("=== Grade Analysis Report ===\n");
            writer.write(String.format("Total scores processed:  %d%n", scores.size()));
            writer.write(String.format("Invalid lines skipped:    %d%n%n", invalidLines));

            writer.write(String.format("Average score:   %.2f%n", avg));
            writer.write(String.format("Highest score:   %d%n", high));
            writer.write(String.format("Lowest score:     %d%n%n", low));

            writer.write("Grade distribution:\n");
            writer.write(String.format("  A (90-100):   %d%n", countA));
            writer.write(String.format("  B (80-89):    %d%n", countB));
            writer.write(String.format("  C (70-79):    %d%n", countC));
            writer.write(String.format("  D (60-69):    %d%n", countD));
            writer.write(String.format("  F (below 60): %d%n", countF));
        }
        catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
        System.out.println("=== Grade Analysis Report ===");
        System.out.println("Total scores processed:  " + scores.size());
        System.out.println("Invalid lines skipped:    " + invalidLines);
        System.out.println();
        System.out.println(String.format("Average score:   %.2f", avg));
        System.out.println("Highest score:   " + high);
        System.out.println("Lowest score:     " + low);
        System.out.println();
        System.out.println("Grade distribution:");
        System.out.println("  A (90-100):   " + countA);
        System.out.println("  B (80-89):    " + countB);
        System.out.println("  C (70-79):    " + countC);
        System.out.println("  D (60-69):    " + countD);
        System.out.println("  F (below 60): " + countF);
    }
}
