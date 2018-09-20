/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eightpuzzle;

/**
 *
 * @author Nicolas Wibisono
 */

import java.util.*;

public class BreadthFirst {
    
    private ArrayList<Node> queue;
    private int maxPos = 8;
    private int minPos = 0;
    private int blank = 0;
    private int totalNodes = 0;
    private Node goalState;
    private boolean pathFound = false;
    public BreadthFirst(Node startState, Node goalState)
    {
        queue = new ArrayList<Node>();
        this.goalState = goalState;
        breadthFirstSearch(startState, goalState);
    }
    
    public void breadthFirstSearch(Node startState, Node goalState)      
    {
        Node current = new Node(startState.getPuzzleState());
        addToQueue(current);
        
        while (!pathFound)
        {
            current = queue.get(0);
            queue.remove(0); // first out
//            moveBlankUp(current.getPuzzleState().findNumber(blank), new PuzzleState(current.getPuzzleState())); // need to make deep copy
            moveBlankUp(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
//            moveBlankDown(current.getPuzzleState().findNumber(blank), new PuzzleState(current.getPuzzleState()));
            moveBlankDown(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
//            moveBlankLeft(current.getPuzzleState().findNumber(blank), new PuzzleState(current.getPuzzleState()));
            moveBlankLeft(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
//            moveBlankRight(current.getPuzzleState().findNumber(blank), new PuzzleState(current.getPuzzleState()));
            moveBlankRight(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
        }
    }
    
    public void report()
    {
        System.out.println("Path found! Steps: ");
        queue.get(queue.size()-1).printSteps();
        System.out.println("Total node created: " + totalNodes);
    }
    
    public boolean checkCurrentWithGoal(Node currentState, Node goalState)
    {
        int [] currentPuzzle = currentState.getPuzzleState().getState();
        int [] goalPuzzle = goalState.getPuzzleState().getState();
        for(int i = minPos; i <= maxPos; i++)
        {
            if (currentPuzzle[i] != goalPuzzle[i])
                return false;
        }
        return true;
    }
    
//    public void moveBlank(int pos, int newPos, PuzzleState puzzle) // forgot to add to steps in nodes with every move.
//    {
//        puzzle.swap(pos, newPos);
//        puzzle.printPuzzle();
//        System.out.println("Total nodes: " + totalNodes + " Queue size: " + queue.size());
//        addToQueue(new Node(puzzle));
//    }
    
    public void moveBlank(int pos, int newPos, Node node) // forgot to add to steps in nodes with every move.
    {
        node.getPuzzleState().swap(pos, newPos);
        node.getPuzzleState().printPuzzle();
        System.out.println("Total nodes: " + totalNodes + " Queue size: " + queue.size());
        if (!node.checkPuzzleDuplicates(node.getPuzzleState()))
            addToQueue(new Node(node.getPuzzleState(), node.getSteps()));
    }
    
//    public boolean moveBlankUp(int pos, PuzzleState puzzle)
//    {
//        if (pos - 3 >= minPos){
//            moveBlank(pos, pos - 3, puzzle);
//            return true;
//        }
//        return false;
//    }
    
    public boolean moveBlankUp(int pos, Node node)
    {
        if (pos - 3 >= minPos){
            moveBlank(pos, pos - 3, node);
            return true;
        }
        return false;
    }
    
//    public boolean moveBlankDown(int pos, PuzzleState puzzle)
//    {
//        if (pos + 3 <= maxPos){
//            moveBlank(pos, pos + 3, puzzle);
//            return true;
//        }
//        return false;
//    }
    
    public boolean moveBlankDown(int pos, Node node)
    {
        if (pos + 3 <= maxPos){
            moveBlank(pos, pos + 3, node);
            return true;
        }
        return false;
    }
    
//    public boolean moveBlankLeft(int pos, PuzzleState puzzle)
//    {
//        int tempPos = pos - 1;
//        if (tempPos >= minPos && tempPos != 2 && tempPos != 5) // because pos 0, 3 and 6 can't move left
//        {            
//            moveBlank(pos, tempPos, puzzle);
//            return true;
//        }
//        return false;
//    }
    
    public boolean moveBlankLeft(int pos, Node node)
    {
        int tempPos = pos - 1;
        if (tempPos >= minPos && tempPos != 2 && tempPos != 5) // because pos 0, 3 and 6 can't move left
        {            
            moveBlank(pos, tempPos, node);
            return true;
        }
        return false;
    }
    
//    public boolean moveBlankRight(int pos, PuzzleState puzzle)
//    {
//        int tempPos = pos + 1;
//        if (tempPos <= maxPos && tempPos != 3 && tempPos != 6) // pos 2, 5 and 8 cant move right
//        {
//            moveBlank(pos, tempPos, puzzle);
//            return true;
//        }
//        return false;
//    }
    
    public boolean moveBlankRight(int pos, Node node)
    {
        int tempPos = pos + 1;
        if (tempPos <= maxPos && tempPos != 3 && tempPos != 6) // pos 2, 5 and 8 cant move right
        {
            moveBlank(pos, tempPos, node);
            return true;
        }
        return false;
    }
    
    public void addToQueue(Node node)
    {
        node.addStep(new PuzzleState(node.getPuzzleState()));
        queue.add(node);
        pathFound = checkCurrentWithGoal(node, goalState);
        totalNodes++;
    }
    
    
    
    
}
