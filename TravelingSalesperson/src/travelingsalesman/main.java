package travelingsalesman;

import java.util.*;

public class main 
{

    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        int numCities = -1;
        char repeat = 'y';
        final int maxRuns = 5;
        String results[][] = new String[6][4];
        results[0][0] = "     ";
        results[1][0] = "Run 1";
        results[2][0] = "Run 2";
        results[3][0] = "Run 3";
        results[4][0] = "Run 4";
        results[5][0] = "Run 5";
        results[0][1] = "Hill";
        results[0][2] = "SimA";
        results[0][3] = "Start";

        System.out.println("Hello, Traveling Salesperson.");
        do
        {
            System.out.println("How many cities do you need to visit?");
            numCities = keyboard.nextInt();
            keyboard.nextLine();
            if(numCities <= 2)
                System.out.println("Invalid number of cities. Must be greater than 2.");
            else
            {
                CostGraph cg = new CostGraph(numCities);
                HillClimbing hc = new HillClimbing(cg);
                Annealing sn = new Annealing(cg);
                for(int i = 1; i <= maxRuns; i++ )
                {
                    ArrayList<Integer> tour = new ArrayList<>(cg.makeTour(cg));
                    results[i][1] = Integer.toString(hc.runTest(tour));
                    results[i][2] = Integer.toString(sn.simulatedAnnealing(tour));
                    results[i][3] = Integer.toString(hc.calcCost(tour));
                }
                print(results);
            }
            System.out.println("Do you need to make another trip? Please answer yes or no.");
            repeat = keyboard.nextLine().toLowerCase().charAt(0);      
        }
        while(repeat == 'y');
    }
    
    public static void print(String[][] results)
    {
        for(int row = 0; row < results.length; row++)
        {
            for(int column = 0; column < results[0].length; column++)
            {
                System.out.print(results[row][column] + " ");
            }
            System.out.println("");
        }
    }
}
