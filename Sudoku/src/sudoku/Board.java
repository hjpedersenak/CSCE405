
package sudoku;


public class Board 
{
    private final int[][] fullBoard = new int[9][9];
    
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
    
    public int[] returnColumn(int index) //may be unnecessary but it's here if we need it
    {
        int[] column = new int[9];
        if(index >= 0 && index <= 9)
        {
            for(int i = 0; i < 9; i++)
            {
                column[i] = fullBoard[i][index];
    //            System.out.println(column[i]);
            }
            return column;
        }
        else
        {
            System.out.println("Invalid index. Must be an integer between 0 and 9. Returning blank column.");
            return column;
        }
    }
    
    public int[] returnRow(int index) //may be unnecessary but it's here if we need it
    {
        int[] row = new int[9];
        if(index >= 0 && index <= 9)
        {
            for(int i = 0; i < 9; i++)
            {
                row[i] = fullBoard[index][i];
    //            System.out.print(row[i]);
            }
    //        System.out.println();
            return row;
        }
        else
        {
            System.out.println("Invalid index. Must be an integer between 0 and 9. Returning blank row.");
            return row;
        }
    }
    
//    public int[][] returnBox(int index)
//    {
//        
//    }
}
