/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package owari;
import java.util.*;
/**
 *
 * @author Nicolas Wibisono
 */
public class HumanPlayer {
    
    private int turn;
    private Owari owari;
    
    public HumanPlayer(int turn)
    {
       this.turn = turn;
    }
    
    public int humanTurn(Owari owari)
    {
        Scanner keyboard = new Scanner(System.in);
        int pitNumber = keyboard.nextInt();
        keyboard.nextLine();
        while (pitNumber > 5 || pitNumber < 0 || owari.getBoard(owari.getCurrentPlayer(), pitNumber) == 0) { // check for out of bound and empty pits
            System.out.println("INVALID MOVE, enter your selected pitNumber (0-5)");
            pitNumber = keyboard.nextInt();
            keyboard.nextLine();
        }
        return pitNumber;
    }
}
