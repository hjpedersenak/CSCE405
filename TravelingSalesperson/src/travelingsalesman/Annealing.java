package travelingsalesman;

import java.util.ArrayList;
import java.util.Collections;

import java.util.Random;

public class Annealing {

    // resource: http://www.theprojectspot.com/tutorial-post/simulated-annealing-algorithm-for-beginners/6
    private double temp = 10000;
    private double coolingRate = 0.00003;
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

        temp = 10000;

        System.out.println("\n\n-------------SIMULATED ANNEALING-------------");
        System.out.println("\nSTART COST: " + calcCost(currentSolution));
        System.out.println("START TOUR: " + currentSolution);
//        System.out.println("\n\nShowing results for temp < 1.1:\n");

        ArrayList<Integer> best = new ArrayList<Integer>(currentSolution);

        while (temp > 1) {

            Random rand = new Random();

            ArrayList<Integer> newSolution = new ArrayList<Integer>(currentSolution);

            int tourPos1 = rand.nextInt(newSolution.size() - 2) + 1;
            int tourPos2 = rand.nextInt(newSolution.size() - 2) + 1;

            int tem = newSolution.get(tourPos1);
            newSolution.set(tourPos1, newSolution.get(tourPos2));
            newSolution.set(tourPos2, tem);

//            if (temp < 1.1)                
//                System.out.println("New solution after swap: " + newSolution);
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

//            if (temp < 1.1) 
//                System.out.println("Temperature: " + temp);
            temp *= 1 - coolingRate;
        }

        System.out.println("FINAL COST: " + calcCost(best));
        System.out.println("FINAL TOUR: " + best + "\n");
        System.out.println("-------------END SIMULATED ANNEALING-------------\n\n");

        return calcCost(best);
    }

}
