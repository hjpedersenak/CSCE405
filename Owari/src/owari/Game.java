package owari;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String mode;
        int whoMoveFirst;
        System.out.println("Welcome to Owari!");
        // play against AI minimax
        System.out.println("Enter 1 if you want to play first, 2 if you want to play second"); //
        whoMoveFirst = keyboard.nextInt();
        keyboard.nextLine();
        Owari owari = new Owari(whoMoveFirst);
        owari.printBoard();

        while (!owari.endGame()) {
            for (int i = 0; i < 3; i++) {
                System.out.print("Player " + owari.getCurrentPlayer() + ", enter your selected pitNumber (0-5): ");
                int pitNumber = keyboard.nextInt();
                keyboard.nextLine();
                while (pitNumber > 5 || pitNumber < 0 || owari.getBoard(owari.getCurrentPlayer(), pitNumber) == 0) { // check for out of bound and empty pits
                    System.out.println("INVALID MOVE, enter your selected pitNumber (0-5)");
                    pitNumber = keyboard.nextInt();
                    keyboard.nextLine();
                }

                owari.moveStones(owari.getCurrentPlayer(), pitNumber);
                owari.switchTurn();
                owari.printBoard();
            }
        }
    }

}
