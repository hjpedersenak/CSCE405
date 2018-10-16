
package owari;
import java.util.Scanner;

public class Game 
{
    public static void main(String[] args) 
    {
       Scanner keyboard = new Scanner(System.in); 
       String mode, whoMoveFirst;
       System.out.println("Welcome to Owari! \nEnter 1 for single player, enter 2 for 2 players mode.");
       mode = keyboard.nextLine();
       System.out.println("Enter 1 if you want to play first, 2 if you want to play second"); //
       whoMoveFirst = keyboard.nextLine();
       if(mode.equals("1"))
       {
           // play against AI minimax
       }
       else
       {
           // play against other player (local)
       }
       
    }
    
}