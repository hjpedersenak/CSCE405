/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzle;
import java.util.*;
/**
 *
 * @author Nicolas Wibisono
 */
public class GreedyBestFirst {
    
    private Node start, goal;
    private PriorityQueue<Node> pq;
    
    public GreedyBestFirst(Node start, Node goal)
    {
        this.start = start;
        this.goal = goal;
        pq = new PriorityQueue<Node>(); 
    }
        
    public int calcManhattanH(Node current, PuzzleState goal)
    {
        int hVal = 0;
        int[] currentPos = new int[2];
        int[] goalPos = new int[2];
        
        for(int i = 0; i < 9; i++) //searching for each digit in turn, skipping blank
        {
            currentPos = current.searchBoard(i);
            goalPos = goal.searchBoard(i);
            hVal = hVal + (Math.abs(currentPos[0]-goalPos[0]) + Math.abs(currentPos[1]-goalPos[1]));
        }
        return hVal;
    }
    
}
