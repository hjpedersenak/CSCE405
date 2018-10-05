package travelingsalesman;

import java.util.*;


public class HillClimbing 
{
    private final CostGraph hcGraph;
    private ArrayList<Integer> tour;
    private final int numCities;
    
    
    public HillClimbing(CostGraph cg)
    {
        hcGraph = cg;
        numCities = cg.getArrSize(); 
    }
    
    public int runTest(ArrayList newTour)
    {
        int newCost = 0;
        this.tour = new ArrayList<Integer>(newTour);
        boolean improved;
        
        do
        {
            improved = completeSwap(swapTest(tour));
            newCost = calcCost(tour);
        }
        while(improved);
        System.out.println("Final tour: " + tour + "\nFinal cost: " + newCost);
        return newCost;
    }
    
    public int[] swapTest(ArrayList<Integer> cities)
    {
        int[] bestSwap = new int[2];
        bestSwap[0] = 0;
        bestSwap[1] = 0;
        int bestCost = calcCost(cities);
        for(int i = 1; i < numCities; i++)
        {
            for(int j = 1; j < numCities; j++)
            {
                if(i != j)
                {
                    ArrayList<Integer> testCase = new ArrayList<Integer>(cities);
                    int temp = testCase.get(i);
                    testCase.set(i, testCase.get(j));
                    testCase.set(j, temp);
                    int testCost = calcCost(testCase);
                    if(testCost < bestCost)
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
    
    public boolean completeSwap(int[] bestSwap)
    {
        if(bestSwap[0] != bestSwap[1])
        {
            System.out.println("-------------HILL CLIMBING-------------\n\n");
            System.out.println("Swapping " + tour.get(bestSwap[0]) + " and " + tour.get(bestSwap[1]));
            int oldCost = calcCost(tour);
            System.out.println("Old cost: " + oldCost);
            int temp = tour.get(bestSwap[0]);
            tour.set(bestSwap[0], tour.get(bestSwap[1]));
            tour.set(bestSwap[1], temp);
            int newCost = calcCost(tour);
            System.out.println("Completed swap, new tour: " + tour);
            System.out.println("New cost: " + newCost);
            return true;
        }
        else
            return false;
        
    }
    
    public int calcCost(ArrayList<Integer> testCase)
    {
        int cost = 0;
        for(int i = 0; i < testCase.size() - 1; i++)
        {
            cost = cost + hcGraph.getCost(testCase.get(i), testCase.get(i + 1)); //i should be y/From, i+1 should be x/To
        }
        return cost;
    }
}