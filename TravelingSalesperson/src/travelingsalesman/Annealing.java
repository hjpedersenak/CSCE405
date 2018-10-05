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

    public void simulatedAnnealing(ArrayList<Integer> currentSolution) {
//        snGraph.getRandomStart();
//        currentSolution = snGraph.makeTour(snGraph);
        
        ArrayList<Integer> best = new ArrayList<Integer>();
        best = snGraph.makeTour(snGraph);

        while (temp > 1) {
 
            ArrayList<Integer> newTour = new ArrayList<Integer>();
            newTour = snGraph.makeTour(snGraph);
            newTour = new ArrayList<Integer>(newTour.subList(1, newTour.size() - 1));

            int tourPos1 = (int) (numCities * Math.random());
            int tourPos2 = (int) (numCities * Math.random());

            int citySwap1 = tourPos1;
            int citySwap2 = tourPos2;

            for (int i = 1; i < numCities; i++) {
                for (int j = 1; j < numCities; j++) {
                    if (i != j) {
                        ArrayList<Integer> test = new ArrayList<Integer>(currentSolution);
                        int temp = test.get(tourPos1);
                        test.set(tourPos1, tourPos2);
                        test.set(tourPos2, temp);
                    }
                }
            }

            int currentEnergy = calcCost(currentSolution);
            int neighborEnergy = calcCost(newTour);
            
            // accept neighbor? 
            if (acceptanceProbability(currentEnergy, neighborEnergy, temp) > Math.random()) {
                currentSolution = snGraph.makeTour(snGraph);
//                currentSolution = new ArrayList<Integer>(currentSolution.subList(1, currentSolution.size() - 1));
            }
            
            // keep track of best solution
            if (calcCost(currentSolution) < calcCost(best)) {
                best = snGraph.makeTour(snGraph);
//                best = new ArrayList<Integer>(best.subList(1, best.size() - 1));
            }
            
            temp *= 1-coolingRate;
            
            System.out.println("Temp: " + temp);

        }
        
        System.out.println("Final solution distance: " + calcCost(best));
        System.out.println("Final Tour: " + best);
        
    }

}