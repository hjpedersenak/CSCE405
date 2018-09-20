
package eightpuzzle;

import java.util.*;


public class AStarManhattan extends BreadthFirst
{
    private PriorityQueue<Node> queue;
    private ArrayList<Node> closed = new ArrayList<Node>();
    private int maxPos = 8;
    private int minPos = 0;
    private int blank = 0;
    private int totalNodes = 0;
    private Node goalState;
    private boolean pathFound = false;
    
    public AStarManhattan(Node startState, Node goalState)
    {
        super(startState, goalState);
        queue = new PriorityQueue<Node>();
        this.goalState = goalState; 
    }
    
    public void aStarManhattanSearch(Node startState, Node goalState)      
    {
        Node current = new Node(startState.getPuzzleState());
        addToQueue(current);
        
        while (!pathFound)
        {
            current = queue.poll(); // removes and returns first in priority queue
            closed.add(current);
            current.getPuzzleState().printPuzzle();
            moveBlankUp(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
            moveBlankDown(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
            moveBlankLeft(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
            moveBlankRight(current.getPuzzleState().findNumber(blank), new Node(new PuzzleState(current.getPuzzleState()), current.getSteps())); // need to make deep copy
            if (pathFound)
            {
                report();
                break;
            }
        }
    }
    
    @Override
    public void moveBlank(int pos, int newPos, Node node) // forgot to add to steps in nodes with every move.
    {
        node.getPuzzleState().swap(pos, newPos);
        int oldG = node.readG();
        node.setG(oldG + 1);
        int newH = node.calcManhattanH(goalState);
        node.setH(newH);
        addToQueue(node);
    }
    
    @Override
    public void addToQueue(Node node)
    {
        if(closed.contains(node) == false)
        {
            node.addStep(new PuzzleState(node.getPuzzleState())); //tracks path, not duplicates
            queue.add(node);
            pathFound = checkCurrentWithGoal(node, goalState);
            totalNodes++;
            System.out.println("Total nodes: " + totalNodes + " Queue size: " + queue.size());
        }
    }
    
    
}