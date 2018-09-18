/*
 * Hanna, Nic, and Olivia
 * CSCE A405 Programming Assignment One
 * September 16th, 2018
 *
 * The purpose of this program is to solve an eight puzzle from start to goal state using
 * Breadth-First Search, Greedy Best-First Search using the Manhattan Distance,
 * A* Search using the Misplaced Tiles heuristic, and A* Search using the Manhattan
 * Distance heuristic. 
 */
package eightpuzzle;

import java.util.Scanner;

public class EightPuzzle {
    
    // function to determine manhattan distance
    public void getMDistance() {
        
    }
    
//    public void 
            
    public static void main(String[] args) {
        System.out.println("Welcome to the eight puzzle game, please enter your start state.\n"
                + "(note: the empty block should be denoted as a 0.");
        // main function contains user initialization of start and goal states
        // help from https://stackoverflow.com/questions/27654491/trying-to-fill-a-2d-array-by-user-input-how-to-do-it
        int row = 3;
        int col = 3;
        int num = 1;
        int num2 = 1;
        char[][] startState = new char[row][col];
        char[][] goalState = new char[row][col];
        Scanner in = new Scanner(System.in);

        for (row = 0; row < startState.length; row++) {
            for (col = 0; col < startState[row].length; col++) {
                System.out.println("Enter element " + num + " for the puzzle");
                startState[row][col] = in.nextLine().charAt(0);
                num++;
            }
        }
        
        System.out.println("Please enter your goal state.\n"
                + "(note: the empty block should be denoted as a 0.");

        for (row = 0; row < goalState.length; row++) {
            for (col = 0; col < goalState[row].length; col++) {
                System.out.println("Enter element " + num2 + " for the puzzle");
                goalState[row][col] = in.nextLine().charAt(0);
                num2++;
            }
        }

        System.out.println("Your start state is: \n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(startState[i][j]);
            }
            System.out.println();
        }

    }
}