package csce.pkg405.pa1;

public class Node implements Comparable<Node>
{
    private int valG, valH, valF = 0;
    PuzzleState nodeState;
    
    public Node(PuzzleState newPuzzle)
    {
        nodeState = newPuzzle;
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
    
    public void setG(int g)
    {
        valG = g;
        calcF();
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
}

