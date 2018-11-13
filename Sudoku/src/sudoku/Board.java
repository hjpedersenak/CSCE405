
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
            }
            System.out.println();
        }
    }
}
