package travelingsalesman;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Random;

public class Annealing {

    private double temp = 10000;
    private double coolingRate = 0.003;
    private final CostGraph snGraph;
    private ArrayList<Integer> tour;
    private final int numCities;

    public Annealing(CostGraph costgraph) {
        snGraph = costgraph;
        numCities = costgraph.getArrSize();
    }

    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }

    public int calcCost(ArrayList<Integer> testCase) {
        int cost = 0;
        for (int i = 0; i < testCase.size() - 1; i++) {
            cost = cost + snGraph.getCost(testCase.get(i), testCase.get(i + 1)); //i should be y/From, i+1 should be x/To
        }
        return cost;
    }

    public int simulatedAnnealing(ArrayList<Integer> currentSolution) {
        
        System.out.println("\n\n\n\n\n\n\n");
    
        System.out.println("\nStart Cost: " + calcCost(currentSolution));
        System.out.println("\nStart tour: " + currentSolution);
        
        ArrayList<Integer> best = new ArrayList<Integer>(currentSolution);
//        int startIndex = 1;
//        int endIndex = snGraph.getArrSize() - 1;
//        Collections.shuffle(best.subList(1, snGraph.getArrSize() - 1));
       
        while (temp > 1) {
            
            Random rand = new Random();
 
            ArrayList<Integer> newSolution = new ArrayList<Integer>(currentSolution);
            
//            Collections.shuffle(newSolution.subList(1, snGraph.getArrSize() - 1));
            if (temp < 1.5)
                System.out.println("New solution: " + newSolution);

            int tourPos1 = rand.nextInt(newSolution.size() - 2) + 1;
            int tourPos2 = rand.nextInt(newSolution.size() -2) + 1;
           
//            for (int i = 1; i < newSolution.size(); i++) {
//                for (int j = 1; j < newSolution.size(); j++) {

                            int tem = newSolution.get(tourPos1);
                            newSolution.set(tourPos1, newSolution.get(tourPos2));
                            newSolution.set(tourPos2, tem);
//                }
//            }
            
            if (temp < 1.5)                
                System.out.println("New solution after swap: " + newSolution);

            int currentEnergy = calcCost(currentSolution);
            int neighborEnergy = calcCost(newSolution);
            
            // accept neighbor? 
            if (acceptanceProbability(currentEnergy, neighborEnergy, temp) > Math.random()) {
                currentSolution = newSolution;
            }
            
            // keep track of best solution
            if (calcCost(currentSolution) < calcCost(best)) {
                best = new ArrayList<Integer>(currentSolution);
            }
            
            if (temp < 1.5) 
                System.out.println("Temperature: " + temp);

            temp *= 1-coolingRate;
        }
      
        System.out.println("FINAL COST: " + calcCost(best) + "\n");
//        System.out.println("Final Tour: " + best +"\n");
        
        return calcCost(best);
    }

}