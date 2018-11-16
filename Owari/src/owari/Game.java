package owari;

import java.util.Scanner;

public class Game {
    
    public static int human = 0;
    public static int AI = 1;
    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        String mode;
        System.out.println("Welcome to Owari!");
        // play against AI minimax
        Game game = new Game();
        int currentPlayer = game.getWhoMoveFirst();
        Owari owari = new Owari(currentPlayer);
        HumanPlayer human = new HumanPlayer();
        System.out.println("Depth level for minimax: ");
        int depth = keyboard.nextInt();
        keyboard.nextLine();
        owari.printBoard();

        while (!owari.endGame()) {
            int pitNumber = -1;
            System.out.println("Player " + game.getWhoseTurn(currentPlayer).toUpperCase() + ", enter your selected pitNumber (0-5): ");
            if (currentPlayer == AI)
            {
                MiniMax turn = new MiniMax(owari, depth);
                pitNumber = turn.minimaxWrapper();
                System.out.println(pitNumber);
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
        
        System.out.println("GAME OVER!");
        owari.printBoard();
        if(owari.tie())
            System.out.println("It's a tie!");
        else{
            System.out.println("The winner is " + owari.getWinner() + "!");
            System.out.println("CONGRATULATIONS!");
        }
        
        
        System.out.println("===================================================");
    }
    
    public int getWhoMoveFirst()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter 1 for AI to go first, 2 for human to go first:");
        int whoMoveFirst = keyboard.nextInt();
        keyboard.nextLine();
        if (whoMoveFirst == 1)
            return AI;
        return human;
    }
    
    public String getWhoseTurn(int currentPlayer)
    {
        if(currentPlayer == human)
        {
            return "North";
        }
        return "South";
    }
}

