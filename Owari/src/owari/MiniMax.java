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
    private final int maxLevel;
    private int[] result = new int[2];
    
    public MiniMax(Owari gameState, int max)
    {
        this.board = gameState.getBoard();
        this.maxLevel = max;
    }
    
    public Owari copyFrom(Owari fromBoard)
    {
        Owari newState = new Owari(fromBoard.getBoard());
        return newState;
    }
    
    public int minimaxWrapper()
    {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        Owari gamestate = new Owari(board);
        result = minimax(maxLevel, alpha, beta, copyFrom(gamestate));
        return result[PIT];
    }
    
    public int[] minimax(int level, int alpha, int beta, Owari game)
    {
        int[][] boardState = game.getBoard();
        if(level == 0 || game.endGame() == true)
        {
            return result;
        }
        else
        {
            if(level % 2 == US) //maximize, raise alpha
            {
                int side = 1;
                int oldScore = boardState[side][6];
                for(int i = 0; i < 6; i++)
                {
                    if(boardState[side][i] != 0)
                    {
                        Owari testGame = copyFrom(game);
                        testGame.moveStones(side, i);
                        int[][] newState = testGame.getBoard();
                        int newScore = newState[side][6];
                        result[VALUE] = newScore - oldScore;
                        int test[] = minimax(level - 1, alpha, beta, copyFrom(testGame));
                        test[PIT] = i;
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
                int side = 0;
                int oldScore = boardState[side][6];
                for(int i = 0; i < 6; i++)
                {
                    if(boardState[side][i] != 0)
                    {
                        Owari testGame = copyFrom(game);
                        testGame.moveStones(side, i);
                        int[][] newState = testGame.getBoard();
                        int newScore = newState[side][6];
                        result[VALUE] = newScore - oldScore;
                        int test[] = minimax(level - 1, alpha, beta, copyFrom(testGame));
                        test[PIT] = i;
                        if(test[VALUE] < beta)
                        {
                            beta = test[VALUE];
                            result = test;
                        }
                        if(alpha >= beta)
                            break;
                    }
                }
                return result;
            }
        }
    }
    
    public int scoreStones(int stones, int pitNumber)
    {
        
        
        
        return 0;
    }
    
    
    
}
