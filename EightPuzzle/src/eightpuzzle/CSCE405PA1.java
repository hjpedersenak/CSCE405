package csce.pkg405.pa1;

import java.util.*;

public class CSCE405PA1 
{

    public static void main(String[] args) 
    {
        Scanner keyboard = new Scanner(System.in);
        char again = 'y';
        char searchSelection = 'e';
        char correctPuz = 'n';
        String stringStart, stringGoal;
        
        System.out.println("Welcome to the 8-Puzzle solver.");
        
        do
        {
            
            do
            {
                System.out.println("Please enter your puzzle's START state in the form\n"
                    + "'#########', entering numbers from left to right, then top to bottom.\n"
                        + "Please indicate the BLANK with a 0.");
                stringStart = keyboard.nextLine();
                PuzzleState startState = new PuzzleState(stringStart);
                System.out.println("Please enter your puzzle's GOAL state in the form\n"
                    + "'#########', entering numbers from left to right, then top to bottom.\n"
                        + "Please indicate the BLANK with a 0.");
                stringGoal = keyboard.nextLine();
                PuzzleState goalState = new PuzzleState(stringGoal);
                System.out.println("You entered this START state:");
                startState.printPuzzle();
                System.out.println("You entered this GOAL state:");
                goalState.printPuzzle();
                System.out.println("Is that correct? Please answer yes or no.");
                correctPuz = keyboard.nextLine().toLowerCase().charAt(0);
            }
            while(correctPuz == 'n');
            
            System.out.println("How do you want to solve the search?\n"
                    + "Please choose an option by its letter:\n"
                    + "A: Breadth-first search\n"
                    + "B: Greedy best-first search\n"
                    + "C: A* search with Misplaced-Tiles heuristic\n"
                    + "D: A* search with Manhattan-Distance heuristic");
            searchSelection = keyboard.nextLine().toLowerCase().charAt(0);
            switch(searchSelection)
            {
                case 'a': 
                    System.out.println("You chose A.");
                    break;
                case 'b': 
                    System.out.println("You chose B. Not implemented.");
                    break;
                case 'c':
                    System.out.println("You chose C. Not implemented.");
                    break;
                case 'd':
                    System.out.println("You chose D. Not implemented.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }
            
            
            System.out.println("Would you like to solve another 8-Puzzle? Please answer yes or no.");
            again = keyboard.nextLine().toLowerCase().charAt(0);
        }
        while(again == 'y');
        System.out.println("Goodbye!");
        keyboard.close();
    }
    
    public void checkParity(PuzzleState start, PuzzleState goal)
    {
        
    }
    
    
    
}
