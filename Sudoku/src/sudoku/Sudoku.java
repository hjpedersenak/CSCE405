
package sudoku;

import java.io.*;
import java.util.*;

public class Sudoku 
{

    public static void main(String[] args) 
    {
        String filename;
        String rawInput = "";
        Scanner keyboard = new Scanner(System.in);
        Scanner file;
        
        System.out.println("Welcome to Sudoku Solver.\nWhat is the filename to read from?");
        filename = keyboard.nextLine();
        try
        {
            file = new Scanner(new File(filename));
            while(file.hasNext())
            {
                rawInput = rawInput + file.next() + ",";
            }
            file.close();
            rawInput = rawInput.replace(",", "");
            
            Board board = new Board(rawInput);
            board.printBoard();
            
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File '" + filename + "' not found. Exiting.");
        }
        keyboard.close();
    }
    
}
