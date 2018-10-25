/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owari;

import java.util.ArrayList;

/**
 *
 * @author ojput
 */
public class MiniMax 
{
    private int[][] board;
    private final int PIT = 0;
    private final int VALUE = 1;
    private final int US = 0;
    private final int THEM = 1;
    private final int maxLevel;
    
    
    
    public MiniMax(int[][] boardState, int max)
    {
        this.board = copyFrom(boardState);
        this.maxLevel = max;
    }
    
    public int[][] copyFrom(int[][] fromBoard)
    {
        int[][] toBoard = new int[fromBoard.length][];
        for(int i = 0; i < fromBoard.length; i++)
        {
            toBoard[i] = fromBoard[i].clone();
        }
        return toBoard;
    }
    
    public int minimaxWrapper()
    {
        int[] result;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        result = minimax(maxLevel, alpha, beta, copyFrom(board));
        return result[PIT];
    }
    
    public int[] minimax(int level, int alpha, int beta, int[][] boardState)
    {
        
        int[] result = new int[2];
        if(level == 0)
        {
            return result;
        }
        else
        {
            if(level % 2 == US) //maximize, raise alpha
            {
                for(int i = 0; i < 6; i++)
                {
                    if(boardState[1][i] != 0)
                    {
                        int test[] = minimax(level - 1, alpha, beta, copyFrom(boardState));
                        if(test[VALUE] > alpha)
                        {
                            alpha = test[VALUE];
                            result = test;
                        }
                        if(alpha >= beta)
                            break;
                    }
                }
                return result;
            }
            else //minimize, lower beta
            {
                for(int i = 0; i < 6; i++)
                {
                    if(boardState[0][i] != 0)
                    {
                        
                    }
                }
                
                
            }
        }
    }
    
    
}
