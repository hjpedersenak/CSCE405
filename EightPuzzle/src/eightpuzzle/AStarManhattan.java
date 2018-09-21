
package eightpuzzle;

import java.util.*;

public class AStarManhattan {
    
    private Node start, goal;
    private PriorityQueue<Node> pq;
    private boolean pathFound = false;
    private int totalNodes;
    private int maxPos = 8;
    private int minPos = 0;
    private int blank = 0;
    
    public AStarManhattan(Node start, Node goal)
    {
        this.start = start;
        this.goal = goal;
        pq = new PriorityQueue<Node>(); 
        aStarManhattanSearch();
    }
    
    public void printPQ()
    {
        for (Node node:pq)
        {
            System.out.println("F value: " + node.readF());
            node.getPuzzleState().printPuzzle();
        }
    }
    
    public void aStarManhattanSearch()
    {
        Node current = new Node(start.getPuzzleState());
        addToQueue(current, -1);
        
        while(!pathFound)
        {
            Node lastNode = current;
            current = pq.remove();
            System.out.println("\nF(n) = " + current.readF());
            int blankPos = current.getPuzzleState().findNumber(blank);
            for(int i =0; i < 4; i++)
            {
                Node temp = new Node(new PuzzleState(current.getPuzzleState()), current.getSteps());
                temp.setH(calcManhattanH(temp, goal.getPuzzleState()));
                int lastG = current.readG();
                switch(i){
                    case 0:
                        moveBlankUp(blankPos, temp, lastG);
                        break;
                    case 1:
                        moveBlankDown(blankPos, temp, lastG);
                        break;
                    case 2:
                        moveBlankLeft(blankPos, temp, lastG);
                        break;
                    case 3:
                        moveBlankRight(blankPos, temp, lastG);
                        break;
                }
                if (pathFound)
                {
                    lastNode =  pq.element();
                    break;
                }
                        
            }
            if (pathFound)
            {
                System.out.println("FOUND PATH");
                lastNode.printSteps();
                System.out.println("Total nodes created: " + totalNodes);
                break;
            }
        }
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
    
    public void moveBlank(int pos, int newPos, Node node, int lastG) // forgot to add to steps in nodes with every move.
    {
        node.getPuzzleState().swap(pos, newPos);
        node.getPuzzleState().printPuzzle();
        System.out.println("Total nodes: " + totalNodes + " Queue size: " + pq.size());
        if (!node.checkPuzzleDuplicates(node.getPuzzleState()))
            addToQueue(new Node(node.getPuzzleState(), node.getSteps()), lastG);
    }
    
    public boolean moveBlankUp(int pos, Node node, int lastG)
    {
        if (pos - 3 >= minPos){
            moveBlank(pos, pos - 3, node, lastG);
            return true;
        }
        return false;
    }
    
    public boolean moveBlankDown(int pos, Node node, int lastG)
    {
        if (pos + 3 <= maxPos){
            moveBlank(pos, pos + 3, node, lastG);
            return true;
        }
        return false;
    }
    
    public boolean moveBlankLeft(int pos, Node node, int lastG)
    {
        int tempPos = pos - 1;
        if (tempPos >= minPos && tempPos != 2 && tempPos != 5) // because pos 0, 3 and 6 can't move left
        {            
            moveBlank(pos, tempPos, node, lastG);
            return true;
        }
        return false;
    }
    
    public boolean moveBlankRight(int pos, Node node, int lastG)
    {
        int tempPos = pos + 1;
        if (tempPos <= maxPos && tempPos != 3 && tempPos != 6) // pos 2, 5 and 8 cant move right
        {
            moveBlank(pos, tempPos, node, lastG);
            return true;
        }
        return false;
    }
    
    public void addToQueue(Node node, int lastG)
    {
        node.addStep(new PuzzleState(node.getPuzzleState()));
        node.setH(calcManhattanH(node, goal.getPuzzleState()));
        node.setG(lastG + 1);
        pq.add(node);
        pathFound = checkCurrentWithGoal(node, goal);
        totalNodes++;
    }
    
}
