import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    public static void main(String[] args) {

        Random RNG = new Random();
        Scanner scanner = new Scanner(System.in);

        int randomNumber = RNG.nextInt(100);
        int tries = 0;

        System.out.println("Enter guess (1 - 100)");

        while (true) {
            int playerGuess = scanner.nextInt();
            tries++;

            if (playerGuess > 100 || playerGuess < 1) {
                System.out.println("Invalid input!");
            } else if (randomNumber > playerGuess) {
                System.out.println("Higher /\\");
            } else if (randomNumber < playerGuess){
                System.out.println("Lower \\/");
            }else {
                System.out.println("Correct! You Win!");
                System.out.println("It took you " + tries + " tries.");
                break;
            }
        }

    }
}
