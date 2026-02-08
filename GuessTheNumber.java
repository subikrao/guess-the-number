import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {
        displayWelcomeMessage();
        
        boolean playAgain = true;
        
        while (playAgain) {
            playGame();
            playAgain = askPlayAgain();
        }
        
        System.out.println("\nThank you for playing! Goodbye!");
        scanner.close();
    }
    
    private static void displayWelcomeMessage() {
        System.out.println("========================================");
        System.out.println("  Welcome to the Number Guessing Game!");
        System.out.println("========================================");
        System.out.println("I'm thinking of a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ".");
        System.out.println();
    }
    
    private static void playGame() {
        int targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int maxAttempts = selectDifficulty();
        int attempts = 0;
        boolean hasWon = false;
        
        System.out.println("\nGreat! You have selected the difficulty level.");
        System.out.println("Let's start the game!\n");
        
        while (attempts < maxAttempts && !hasWon) {
            int guess = getUserGuess(attempts + 1, maxAttempts);
            attempts++;
            
            if (guess == targetNumber) {
                hasWon = true;
                displayVictoryMessage(attempts);
            } else if (guess < targetNumber) {
                System.out.println("Incorrect! The number is greater than " + guess + ".\n");
            } else {
                System.out.println("Incorrect! The number is less than " + guess + ".\n");
            }
        }
        
        if (!hasWon) {
            System.out.println("Game Over! You've run out of chances.");
            System.out.println("The correct number was: " + targetNumber + "\n");
        }
    }
    
    private static int selectDifficulty() {
        System.out.println("Please select the difficulty level:");
        System.out.println("1. Easy (10 chances)");
        System.out.println("2. Medium (5 chances)");
        System.out.println("3. Hard (3 chances)");
        System.out.print("\nEnter your choice: ");
        
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        return 10;
                    case 2:
                        return 5;
                    case 3:
                        return 3;
                    default:
                        System.out.print("Invalid choice! Please enter 1, 2, or 3: ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a number (1, 2, or 3): ");
            }
        }
    }
    
    private static int getUserGuess(int currentAttempt, int maxAttempts) {
        System.out.print("Enter your guess (Attempt " + currentAttempt + "/" + maxAttempts + "): ");
        
        while (true) {
            try {
                int guess = Integer.parseInt(scanner.nextLine());
                
                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    System.out.print("Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER + ": ");
                } else {
                    return guess;
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a valid number: ");
            }
        }
    }
    
    private static void displayVictoryMessage(int attempts) {
        System.out.println("\nCongratulations! You guessed the correct number in " + attempts + 
                         " attempt" + (attempts == 1 ? "" : "s") + "!\n");
    }
    
    private static boolean askPlayAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        
        while (true) {
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes") || response.equals("y")) {
                System.out.println();
                return true;
            } else if (response.equals("no") || response.equals("n")) {
                return false;
            } else {
                System.out.print("Invalid input! Please enter 'yes' or 'no': ");
            }
        }
    }
}