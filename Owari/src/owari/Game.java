package owari;

import java.util.Scanner;

public class Game {
    
    public static int human = 0;
    public static int AI = 1;
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String mode;
        System.out.println("Welcome to Owari!");
        // play against AI minimax
        Game game = new Game();
        int currentPlayer = game.getWhoMoveFirst();
        Owari owari = new Owari(currentPlayer);
        HumanPlayer human = new HumanPlayer();
        owari.printBoard();

        while (!owari.endGame()) {
            int pitNumber = -1;
            System.out.print("Player " + game.getWhoseTurn(currentPlayer) + ", enter your selected pitNumber (0-5): ");
            if (currentPlayer == AI)
            {
                pitNumber = human.humanTurn(owari); // call AI later
            }
            else
            {
                pitNumber = human.humanTurn(owari);
            }
            owari.moveStones(owari.getCurrentPlayer(), pitNumber);
            owari.switchTurn();
            currentPlayer = owari.getCurrentPlayer();
            owari.printBoard();
        }
    }
    
    public int getWhoMoveFirst()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter 1 if you want to play first, 2 if you want to play second"); // 1 is north, 2 south // i think i have a logic error here.
        int whoMoveFirst = keyboard.nextInt();
        keyboard.nextLine();
        if (whoMoveFirst == 1)
            return AI;
        return human;
    }
    
//    public String getWhoseTurn(String currentPlayer)
//    {
//        if(currentPlayer.equals("AI"))
//        {
//            return "Human";
//        }
//        return "AI";
//    }
//    
    public String getWhoseTurn(int currentPlayer)
    {
        if(currentPlayer == human)
        {
            return "Human";
        }
        return "AI";
    }
    

}
