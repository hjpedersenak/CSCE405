package owari;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String mode;
        int whoMoveFirst;
        
        System.out.println("Welcome to Owari!");
        // play against AI minimax
        System.out.println("Enter 1 if you want to play first, 2 if you want to play second"); // 1 is north, 2 south // i think i have a logic error here.
        whoMoveFirst = keyboard.nextInt();
        keyboard.nextLine();
        Owari owari = new Owari(whoMoveFirst);
        HumanPlayer human = new HumanPlayer(whoMoveFirst);
        owari.printBoard();

        while (!owari.endGame()) {
            System.out.print("Player " + (owari.getCurrentPlayer()+1) + ", enter your selected pitNumber (0-5): ");
            int pitNumber = human.humanTurn(owari);
            owari.moveStones(owari.getCurrentPlayer(), pitNumber);
            owari.switchTurn();
            owari.printBoard();
        }
    }

}
