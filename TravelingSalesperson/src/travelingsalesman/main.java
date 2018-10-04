
package travelingsalesman;

import java.util.*;

public class main 
{

    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        int numCities = -1;
        char repeat = 'y';

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
                ArrayList<Integer> tour = cg.makeTour(cg);
                Annealing an = new Annealing(cg);
                an.simulatedAnnealing(tour);
                System.out.println("Your start cost was: " + an.calcCost(tour));
                System.out.println("Your start tour was: " + tour);
                cg.makeTour(cg);
            }
            System.out.println("Do you need to make another trip? Please answer yes or no.");
            repeat = keyboard.nextLine().toLowerCase().charAt(0);      
        }
        while(repeat == 'y');
    }
}
