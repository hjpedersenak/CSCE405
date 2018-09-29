
package travelingsalesman;

import java.util.*;


public class HillClimbing 
{
    private CostGraph hcGraph;
    private ArrayList<Integer> cities;
    private int numCities;
    private int numRuns = 5;
    
    
    public HillClimbing(CostGraph cg)
    {
        hcGraph = cg;
        numCities = cg.getArrSize(); //make Tour class later
        cities = new ArrayList(numCities);
    }
    
    public int[] swapTest(ArrayList cities)
    {
        int[] bestSwap = new int[2];
        int bestCost = 0;
        for(int i = 1; i < numCities; i++)
        {
            for(int j = 1; j < numCities; j++)
            {
                if(i != j)
                {
                    ArrayList<Integer> testCase = cities;
                    int temp = testCase.get(i);
                    testCase.set(i, testCase.get(j));
                    testCase.set(j, i);
                    int testCost = calcCost(testCase);
                    if(bestCost == 0 || bestCost > testCost)
                    {
                        bestCost = testCost;
                        bestSwap[0] = i;
                        bestSwap[1] = j;
                    }
                }
            }
        }
        return bestSwap;
    }
    
    public int calcCost(ArrayList testCase)
    {
        int cost = 0;
        for(int i = 0; i < numCities - 1; i++)
        {
            cost = cost + hcGraph.getCost(i, i + 1);
        }
        cost = cost + hcGraph.getCost(numCities - 1, 0);
        return cost;
    }
    
}

