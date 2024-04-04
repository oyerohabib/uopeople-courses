import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {
        // Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // variables to track the correct answers and total questions
        int correctAnswers = 0;
        int totalQuestions = 5;

        // Display the questions and get user input
        for (int questionNumber = 1; questionNumber <= totalQuestions; questionNumber++) {
            System.out.println("Question " + questionNumber + ":");
            displayQuestion(questionNumber); // Display the question and options
            char userAnswer = getUserAnswer(scanner); // Get user's answer
            char correctAnswer = getCorrectAnswer(questionNumber); // Get correct answer

            // Check if the user's answer is correct and update the score
            if (userAnswer == correctAnswer) {
                System.out.println("Correct!\n");
                correctAnswers++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswer + ".\n");
            }
        }

        // Calculate and display the final score
        double score = (double) correctAnswers / totalQuestions * 100;
        System.out.println("Your final score: " + score + "%");

        // Close the scanner
        scanner.close();
    }

    // Method to display the question and options
    private static void displayQuestion(int questionNumber) {
        switch (questionNumber) {
            case 1:
                System.out.println("What is the capital of France?");
                System.out.println("A. Berlin");
                System.out.println("B. Madrid");
                System.out.println("C. Paris");
                System.out.println("D. Rome");
                break;
            case 2:
                System.out.println("Which planet is known as the Red Planet?");
                System.out.println("A. Venus");
                System.out.println("B. Mars");
                System.out.println("C. Jupiter");
                System.out.println("D. Saturn");
                break;
            case 3:
                System.out.println("Is UoPeople Tuition Free");
                System.out.println("A. Yes");
                System.out.println("B. No");
                System.out.println("C. Maybe");
                System.out.println("D. I don't know");
                break;
            case 4:
                System.out.println("What is the product of 10 * 10");
                System.out.println("A. 50");
                System.out.println("B. 80");
                System.out.println("C. 100");
                System.out.println("D. 10");
                break;
            case 5:
                System.out.println("What is the longest bone in the Human Body");
                System.out.println("A. Phallanges");
                System.out.println("B. Femur");
                System.out.println("C. Cranium");
                System.out.println("D. Hypothallamus");
                break;

            default:
                System.out.println("Invalid question number.");
        }
    }

    // Method to get user's answer
    private static char getUserAnswer(Scanner scanner) {
        char userAnswer;
        do {
            System.out.print("Your answer (A, B, C, or D): ");
            String input = scanner.next().toUpperCase();
            if (input.length() == 1 && "ABCD".contains(input)) {
                userAnswer = input.charAt(0);
                break;
            } else {
                System.out.println("Invalid input. Please enter A, B, C, or D.");
            }
        } while (true);
        return userAnswer;
    }

    // Method to get correct answer
    private static char getCorrectAnswer(int questionNumber) {
        switch (questionNumber) {
            case 1:
                return 'C';
            case 2:
                return 'B';
            case 3:
                return 'A';
            case 4:
                return 'C';
            case 5:
                return 'B';
            default:
                throw new IllegalArgumentException("Invalid question number.");
        }
    }
}
