import java.util.Random;
import java.util.Scanner;

public class RPSGame {

    public static void main(String[] args) {
        String[] rps = {"r","p","s"};
        Scanner scanner = new Scanner(System.in);

        while(true){

            String CPUMove = rps[new Random().nextInt(rps.length)];
            String playerMove;

            while(true) {
                System.out.println("Make a move (Rock, Paper, Scissors)");
                playerMove = scanner.nextLine();
                if(playerMove.equals("r") || playerMove.equals("p") || playerMove.equals("s")){
                    break;
                }
                System.out.println(playerMove + " is not a valid move.");
            }

            System.out.println("CPU played: " + CPUMove);

            if(playerMove.equals(CPUMove)) {
                System.out.println("Tie");
            }else if (playerMove.equals("r")){
                if (CPUMove.equals("p")){
                    System.out.println("You Lose");
                }else if (CPUMove.equals("s")){
                    System.out.println("You Win");
                }
            }else if (playerMove.equals("p")){
                if (CPUMove.equals("s")){
                    System.out.println("You Lose");
                }else if (CPUMove.equals("r")){
                    System.out.println("You Win");
                }
            }else {
                if (CPUMove.equals("r")){
                    System.out.println("You Lose");
                }else if (CPUMove.equals("p")){
                    System.out.println("You Win");
                }
            }
            System.out.println("Play Again? (y/n)");
            String playAgain = scanner.nextLine();

            if (playAgain.equals("n")) {
                break;
            }
        }
    }
}
