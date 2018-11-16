package sudoku;

import java.util.ArrayList;
import java.util.Queue;

public class Board {

    private int[][] fullBoard = new int[9][9];
    private ArrayList<Integer>[][] moveList = new ArrayList[9][9];
    private Queue<int[]> toProcess;

    public Board(String input) {
        int counter = 0;
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                fullBoard[column][row] = input.charAt(counter) - 48;
                counter++;
            }
        }
    }

    // check for number in row
    public boolean inRow(int row, int num) {
        for (int i = 0; i < 9; i++) {
            if (fullBoard[row][i] == num) {
                return true;
            }
        }
        return false;
    }

    // check for number in column
    public boolean inCol(int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (fullBoard[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    // check for number in box
    public boolean in3X3(int row, int col, int num) {
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (fullBoard[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }
    
//    public boolean checkNakedTriple(int row, int col, int num) {
//        int r = row - row % 3;
//        int c = col - col % 3;
//
//        for (int i = r; i < r + 3; i++) {
//            for (int j = c; j < c + 3; j++) {
//                if (fullBoard[i][j] == num) {
//                    return true;
//                }
//                
//            }
//        }
//        return false;
//    }

    // combination of all checks
    public boolean isValid(int row, int col, int num) {
        return !inRow(row, num) && !inCol(col, num) && !in3X3(row, col, num);
    }
    
    public boolean constraintSolve()
    {
        initializeMoves();
        checkSingles();
        
        return true;
    }
    
    public void initializeMoves() //this monstrosity initializes all available moves for every blank cell
    {
        for(int num = 1; num <= 9; num++)
        {
            for(int col = 0; col < 9; col++)
            {
                for(int row = 0; row < 9; row++)
                {
                    if(fullBoard[col][row] == 0)
                    {
                        if(isValid(col, row, num))
                        {
                            moveList[col][row].add(num);
                        }
                    }
                }
            }
        }
    }
    
    public void checkSingles() //any cell with only one available move is solved, added to a queue to remove num from neighbors
    {
        for(int col = 0; col < 9; col++)
        {
            for(int row = 0; row < 9; row++)
            {
                if(fullBoard[col][row] == 0 && moveList[col][row].size() == 1)
                {
                    addToQueue(col, row);
                }
            }
        }
    }
    
    public void addToQueue(int col, int row)
    {
        fullBoard[col][row] = moveList[col][row].remove(0);
        moveList[col][row].clear();
        int[] solved = new int[3];
        solved[0] = col;
        solved[1] = row;
        solved[2] = fullBoard[col][row];
        toProcess.add(solved);
    }
    
    public void removeOptions()
    {
        int[] process = toProcess.poll();
        int col = process[0];
        int row = process[1];
        int num = process[2];
        int index;
        for(int i = 0; i < 9; i++)
        {
            index = moveList[col][i].indexOf(num); //remove solved num from rows
            if(index != -1)
            {
                fullBoard[col][i] = moveList[col][i].remove(index);
                moveList[col][i].trimToSize();
                if(moveList[col][i].size() == 1)
                {
                    addToQueue(col, i);
                }
            }
            
            index = moveList[i][row].indexOf(num); //remove solved num from cols
            if(index != -1)
            {
                fullBoard[i][row] = moveList[i][row].remove(index);
                moveList[i][row].trimToSize();
                if(moveList[i][row].size() == 1)
                {
                    addToQueue(i, row);
                }
            }
        }
    }

    // solve using is valid method 
    public boolean solve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (fullBoard[row][col] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (isValid(row, col, number)) {
                            fullBoard[row][col] = number;
                            if (solve()) {
                                return true;
                            } else {
                                fullBoard[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

//    public boolean isValid() //currently only tests rows and columns, not boxes
//    {
//        HashSet<Integer> testerCol = new HashSet<>();
//        HashSet<Integer> testerRow = new HashSet<>();
//        
//        for (int i = 0; i < 9; i++) {
//            testerCol.clear();
//            testerRow.clear();
//            for (int j = 0; j < 9; j++) {
//                if (testerCol.add(fullBoard[i][j]) == false) //since sets can't have duplicates, this returns false if a value appears more than once
//                {
//                    return false;
//                }
//                if (testerRow.add(fullBoard[j][i]) == false) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }
    
//    public boolean solvePuzzle() {
//        for (int row = 0; row < 9; row++) {
//            for (int col = 0; col < 9; col++) {
//                if (fullBoard[row][col] == 0) {
//                    for (int number = 1; number <= 9; number++) {
//                        if (isValid()) {
//                            fullBoard[row][col] = number;
//                            
//                            if (solvePuzzle()) {
//                                return true;
//                            } else {
//                                fullBoard[row][col] = 0;
//                            }
//                        }
//                    }
//                    
//                    return false;
//                }
//            }
//        }
//        
//        return true;
//    }

    public void printBoard() {
        for (int column = 0; column < 9; column++) {
            for (int row = 0; row < 9; row++) {
                System.out.print(fullBoard[column][row] + " ");
                if (row == 2 || row == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (column == 2 || column == 5) {
                System.out.print("* * * * * * * * * * *\n");
            }
        }
    }
}

// reference: https://www.baeldung.com/java-sudoku
// https://medium.com/@ssaurel/build-a-sudoku-solver-in-java-part-1-c308bd511481

// /Users/Hanna/Desktop/LS/AI/CSCE405/Sudoku/src/sudoku/17-1.txt
