/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owari;



/**
 *
 * @author Nicolas Wibisono
 */
public class Owari {
    
    private int [][] board;
    private int boardLength = 7;
    private int currentPlayer;
    private int NORTH = 0;
    private int SOUTH = 1;
    private int totalPlayers = 2;
    private int goalPit = 6;
    
    public Owari(int playerTurn)
    {
        currentPlayer = playerTurn;
        board = new int [totalPlayers][boardLength];
        for(int i = 0; i < totalPlayers; i++)
        {
            for(int j = 0; j < boardLength; j++)
            {
                if(j == (boardLength - 1))
                    board[i][j] = 0;
                else
                    board[i][j] = 3;
            }
        }
    }
    
    public Owari(int[][] newBoard)
    {
        board = new int [totalPlayers][boardLength];
        for(int i = 0; i < totalPlayers; i++)
        {
            for(int j = 0; j < boardLength; j++)
            {
                board[i][j] = newBoard[i][j];
            }
        }
    }
    
    public void printBoard()
    {
        // NORTH is reversed, so pit number 1 is the most right pit
        System.out.print(" ");
        for(int i = boardLength - 2; i >= 0; i--)
        {
            System.out.print(board[NORTH][i]);
        }
        System.out.println("\n" + board[NORTH][boardLength-1] + "      " + board[SOUTH][boardLength-1]);
        System.out.print(" ");
        for(int i = 0; i < boardLength - 1; i++)
        {
            System.out.print(board[SOUTH][i]);
        }
        System.out.println();
    }
    
    public int getBoard(int side, int pitNumber) {
        return board[side][pitNumber];
    }
    
    public int[][] getBoard()
    {
        return board;
    }

    public void setBoard(int side, int pitNumber, int value) {
        this.board[side][pitNumber] = value;
    }
    
    public int getOpposite(int side)
    {
        if(side == NORTH)
            return SOUTH;
        return NORTH;
    }
    
    public void moveStones(int side, int pitNumber)
    {
        int stoneInHand = board[side][pitNumber];
        board[side][pitNumber] = 0;
        int currentPit = pitNumber;
        int currentSide = side;
        for(;stoneInHand > 0; stoneInHand--)
        {
            currentPit++; // move to next pit (counter clockwise)
            if (checkPit(currentSide,currentPit))
            {
               currentSide = getOpposite(currentSide);
               currentPit = 0;
            }
            board[currentSide][currentPit]++;
        }
        checkCapture(currentPit, currentSide);
    }
    
    public boolean checkCapture(int lastPit, int currentSide)
    {
        int oppositePit = getOppositePit(lastPit);
        if(currentSide == currentPlayer && lastPit != goalPit && getBoard(currentPlayer, lastPit) == 1 && getBoard(getOpposite(currentPlayer), oppositePit) > 0)
        {
            board[currentPlayer][goalPit]+= getBoard(getOpposite(currentPlayer), oppositePit);
            setBoard(getOpposite(currentPlayer), oppositePit, 0);
            return true;
        }
        return false;
    }
    
    private int getOppositePit(int pitNumber) // I am too stupid to do it any other way. I can't do math.
    {
        switch(pitNumber)
        {
            case 0:
                return 5;
            case 1:
                return 4;
            case 2:
                return 3;
            case 3:
                return 2;
            case 4:
                return 1;
        }
        return 0;
    }
    
    public boolean checkPit(int side, int pitNumber)
    {
        if(side != currentPlayer && pitNumber == goalPit) // if the current player not on their own side, do not put stone in other player goalPit
            return true;
        if(pitNumber > goalPit) // prevent out of bound
            return true;
        return false;
    }
    
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    public int switchTurn()
    {
        if(currentPlayer == NORTH)
            currentPlayer = SOUTH;
        else 
            currentPlayer = NORTH;
        return currentPlayer;
    }
    
    public String getSideString(int side)
    {
        if(side == NORTH)
            return "NORTH";
        return "SOUTH";
    }
    
    public boolean endGame()
    {
        // check if either side is empty
        int total1 = 0;
        int total2 = 0;
        for(int i = 0; i < goalPit; i++)
        {
            total1 += getBoard(NORTH, i);
            total2 += getBoard(SOUTH, i);
        }

        if(total1 == 0)
        {
            for(int i = 0; i < goalPit; i++) // if one side is empty then put the rest of the stone to the other side's goalpit
            {
                board[SOUTH][goalPit] += board[SOUTH][i];
                board[SOUTH][i] = 0;
            }
            return true;
        }
        else if(total2 == 0)
        {
            for(int i = 0; i < goalPit; i++) 
            {
                board[NORTH][goalPit] += board[NORTH][i];
                board[NORTH][i] = 0;
            }
            return true;
        }
        return false;
    }
    
    public boolean tie()
    {
        if (board[SOUTH][goalPit] == board[NORTH][goalPit])
            return true;
        return false;
    }
    
    public String getWinner()
    {
        if(board[SOUTH][goalPit] > board[NORTH][goalPit])
            return "South";
        return "North";
    }
}
