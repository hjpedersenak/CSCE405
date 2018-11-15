
package sudoku;


public class Solver 
{
    String input;
    
//     Board board = new Board(input);
    
    public Board solveBoard(Board board) {
        
        return board;
    }
    
    // check if row, column and 3X3 are valid 
    public boolean isValid(Board board) 
    {
        return board.isValid();
    }
    
    public boolean rowConstraint(Board board, int row) {
        
    }
    
    public boolean columnConstraint(Board board, int column) {
        
    }   
    
    public boolean boxConstraint(Board board, int row, int column) {
        
    }
    
    public boolean checkConstraint() {
        
    }
}
