package eightpuzzle;

import java.util.*;
public class Node implements Comparable<Node>
{
    private int valG, valH, valF = 0;
    PuzzleState nodeState;
    private ArrayList<PuzzleState> steps; // This variable is used to record previous steps - Nico
    
    public Node(PuzzleState newPuzzle)
    {
        nodeState = newPuzzle;
        steps = new ArrayList<PuzzleState>();
    }
    
    public Node(PuzzleState newPuzzle, ArrayList<PuzzleState> steps)
    {
        nodeState = newPuzzle;
        this.steps = new ArrayList<PuzzleState>(steps);
    }
    
    public void addStep(PuzzleState oldState) // add previous step (parent)
    {
        steps.add(oldState);
    }
    
    public ArrayList<PuzzleState> getSteps()
    {
        return steps;
    }
    
    public void printSteps()
    {
        for(int i = 0; i < steps.size(); i++)
        {
            steps.get(i).printPuzzle();
        }
    }
    
    public PuzzleState getPuzzleState()
    {
        return nodeState;
    }
    
    public int readPosValue(int pos)
    {
        return nodeState.readPosValue(pos);
    }
    
    public int compareTo(Node node)
    {
        if(this.readF() < node.readF())
        {
            return -1;
        }
        else if(this.readF() == node.readF())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    
    public int[] searchBoard(int target)
    {
        return nodeState.searchBoard(target);
    }
    
    public void setG(int g)
    {
        valG = g;
        calcF();
    }
    
    public int readG()
    {
        return valG;
    }
    
    public void setH(int h)
    {
        valH = h;
        calcF();
    }
    
    public void calcF()
    {
        valF = valG + valH;
    }
    
    public int readF()
    {
        return valF;
    }
    
    //referenced https://stackoverflow.com/questions/8224470/calculating-manhattan-distance
    public int calcManhattanH(Node goal)
    {
        int hVal = 0;
        int[] currentPos = new int[2];
        int[] goalPos = new int[2];
        
        for(int i = 1; i < 9; i++) //searching for each digit in turn, skipping blank
        {
            currentPos = this.searchBoard(i);
            goalPos = goal.searchBoard(i);
            hVal = hVal + (Math.abs(currentPos[0]-goalPos[0]) + Math.abs(currentPos[1]-goalPos[1]));
        }
        return hVal;
    }
}

