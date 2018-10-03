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
    private int[][] cities;
    private int lowestCost = 100;
    private int highestCost = 2500;

    public CostGraph(int size) {
        arrSize = size;
        cities = new int[size][size];
        Random rand = new Random();
        for (int i = 0; i < arrSize; i++) {
            for (int j = 0; j < arrSize; j++) {
                if (i == j) {
                    cities[i][j] = 0;
                } else {
                    cities[j][i] = rand.nextInt(2401) + 100;
                }
            }
        }

        printGraph();
    }

    public void printGraph() {
        System.out.print("   ");
        for (int i = 0; i < arrSize; i++) {
            if (i < 10) {
                System.out.print("  " + i + "   ");
            } else {
                System.out.print("  " + i + "  ");
            }
        }
        System.out.println();
        for (int i = 0; i < arrSize; i++) {
            System.out.print("  " + i + "  ");
            for (int j = 0; j < arrSize; j++) {
                if (cities[j][i] == 0) {
                    System.out.print("   " + cities[j][i] + "  ");
                } else if (cities[j][i] < 1000) {
                    System.out.print(" " + cities[j][i] + "  ");
                } else {
                    System.out.print(cities[j][i] + "  ");
                }
            }
            System.out.println();
        }
    }

    public int getRandomStart() {
        Random rand = new Random();
        return rand.nextInt(arrSize);
    }

    public int getCost(int from, int to) {
        return cities[to][from];
    }

    public ArrayList makeTour(CostGraph costgraph) {

        ArrayList startTour = new ArrayList<Integer>();

        int startCity = costgraph.getRandomStart();
        startTour.add(startCity);

        int destinationCity = startCity;

        System.out.println("Your start city is: " + startCity);

        for (int cityIndex = 0; cityIndex < costgraph.getArrSize(); cityIndex++) {
            if (cityIndex != startCity) {
                startTour.add(cityIndex);
            }
        }

        int startIndex = 1;
        int endIndex = costgraph.getArrSize() - 1;
        Collections.shuffle(startTour.subList(startIndex, endIndex));
        startTour.add(destinationCity);
        System.out.println("Random city tour:\n" + startTour);
        
        //        int cost = 0;
//        
//        for (int cityIndex = 0; cityIndex < costgraph.getArrSize()-1; cityIndex++) {
//            cost += getCost(startTour.indexOf(cityIndex), startTour.indexOf(cityIndex + 1));
//            System.out.println("Cost: " + cost + " from city " + cityIndex + " to city " + (cityIndex+1));
//        }
////        cost += getCost(startTour.indexOf(startTour.size()-2), startTour.indexOf(startTour.size()-1));
//        System.out.println("Start tour cost: " + cost);

        return startTour;
    }

}
