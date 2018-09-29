
package travelingsalesman;

import java.util.*;

public class CostGraph {

    public int getArrSize() {
        return arrSize;
    }

    public void setArrSize(int arrSize) {
        this.arrSize = arrSize;
    }

    public int[][] getCities() {
        return cities;
    }

    public void setCities(int[][] cities) {
        this.cities = cities;
    }

    public int getLowestCost() {
        return lowestCost;
    }

    public void setLowestCost(int lowestCost) {
        this.lowestCost = lowestCost;
    }

    public int getHighestCost() {
        return highestCost;
    }

    public void setHighestCost(int highestCost) {
        this.highestCost = highestCost;
    }
    
    private int arrSize;
    private int [][]cities;
    private int lowestCost = 100;
    private int highestCost = 2500;
    public CostGraph(int size)
    {
        arrSize = size;
        cities = new int[size][size];
        Random rand = new Random();
        for(int i = 0; i < arrSize; i++)
        {
            for(int j = 0; j < arrSize; j++)
            {
                cities[j][i] = rand.nextInt(2401) + 100;
            }
        }
        
        printGraph();
    }
    
    public void printGraph()
    {
        System.out.print("     ");
        for(int i =0; i < arrSize; i++)
        {
            System.out.print(i + "     ");
        }
        System.out.println();
        for(int i = 0; i < arrSize; i++)
        {
            System.out.print(i + "    ");
            for(int j = 0; j < arrSize; j++)
            {
                if (cities[j][i] < 1000)
                     System.out.print( cities[j][i] + "   ");
                else
                    System.out.print( cities[j][i] + "  ");
            }
            System.out.println();
        }
    }
    
}
