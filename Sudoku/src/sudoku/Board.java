
package sudoku;


public class Board 
{
    private int[][] fullBoard = new int[9][9];
    
    public Board(String input)
    {
        int counter = 0;
        for(int column = 0; column < 9; column++)
        {
            for(int row = 0; row < 9; row++)
            {
                fullBoard[column][row] = input.charAt(counter) - 48;
                counter++;
            }
        }
    }
    
    public void printBoard()
    {
        for(int column = 0; column < 9; column++)
        {
            for(int row = 0; row < 9; row++)
            {
                System.out.print(fullBoard[column][row] + " ");
                if(row == 2 || row == 5)
                {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if(column == 2 || column == 5)
            {
                //not sure how to print a nice horizontal divider yet
            }
        }
    }
}
