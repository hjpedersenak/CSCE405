
package eightpuzzle;

import java.lang.Character;

public class PuzzleState 
{
    int[] state = new int[9];
    
    public PuzzleState(String input)
    {
        for(int i = 0; i < 9; i++)
        {   
            state[i] = Character.getNumericValue(input.charAt(i));
        }
    }
    
    public PuzzleState(PuzzleState oldState)
    {
        for (int i = 0; i < 9; i++)
        {
            state[i] = oldState.getState()[i];
        }
    }
    
    public int[] getState()
    {
        return state;
    }
    
    public int readPosValue(int pos)
    {
        return state[pos];
    }
    
    public int findNumber(int target)
    {
        int position = -1;
        for(int i = 0; i < 9; i++)
        {
            if(state[i] == target)
                position = i;
        }
        return position;
    }
    
    public int[] searchBoard(int target) //should return the x pos in result[0] and the y pos in result[1]
    {
        int[] result = new int[2];
        int position = findNumber(target);
        switch(position)
        {
            case 0:
                result[0] = 0;
                result[1] = 0;
                break;
            case 1:
                result[0] = 0;
                result[1] = 1;
                break;
            case 2:
                result[0] = 0;
                result[1] = 2;
                break;
            case 3:
                result[0] = 1;
                result[1] = 0;
                break;
            case 4:
                result[0] = 1;
                result[1] = 1;
                break;
            case 5:
                result[0] = 1;
                result[1] = 2;
                break;
            case 6:
                result[0] = 2;
                result[1] = 0;
                break;
            case 7:
                result[0] = 2;
                result[1] = 1;
                break;
            case 8:
                result[0] = 2;
                result[1] = 2;
                break;
            default:
                System.out.println("Error in searchBoard method.");
                break;
        }
        return result;
    }
    
    public void printPuzzle()
    {
        for (int i = 0; i < 9; i++)
        {
            if (state[i] == 0)
                System.out.print("_ ");
            else
                System.out.print(state[i] + " ");
            if(i%3 == 2)
                System.out.println();
        }
        System.out.println();
//        System.out.println(state[0] + " " + state [1] + " " + state[2] + "\n" 
//        + state[3] + " " + state[4] + " " + state[5] + "\n"
//        + state[6] + " " + state[7] + " " + state[8]);
    }
    
    public int getParity()
    {
        int counter = 0;
        for(int i = 1; i < 9; i++)
        {
            for(int j = 0; j < i; j++)
            {
                if(state[i] != 0 && state[j] != 0 && state[i] < state[j])
                {
                    counter++;
                }
            }
        }
        return counter;
    }
    
    public void swap(int positionBlank, int positionNew)
    {
        int temp;
        switch(positionBlank)
        {
            case 0:
                if(positionNew == 1 || positionNew == 3)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 1:
                if(positionNew == 0 || positionNew == 2 || positionNew == 4)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 2:
                if(positionNew == 1 || positionNew == 5)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 3:
                if(positionNew == 0 || positionNew == 4 || positionNew == 6)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 4:
                if(positionNew == 1 || positionNew == 3 || 
                        positionNew == 5 || positionNew == 7)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 5:
                if(positionNew == 2 || positionNew == 4 || positionNew == 8)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 6:
                if(positionNew == 3 || positionNew == 7)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 7:
                if(positionNew == 6 || positionNew == 4 || positionNew == 8)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            case 8:
                if(positionNew == 7 || positionNew == 5)
                {
                    temp = state[positionBlank];
                    state[positionBlank] = state[positionNew];
                    state[positionNew] = temp;
                }
                else
                {
                    System.out.println("Invalid swap from " + positionBlank + 
                            " to " + positionNew);
                }
                break;
            default:
                System.out.println("Position swap markers must be between 0 and 8.");
                break;
                
        }
    }
}
