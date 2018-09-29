
package travelingsalesman;


public class Annealing {
    
    private double temp = 10000;
    private double coolingRate = 0.003;
    
    public Annealing(CostGraph costgraph) {
        
    }
    
    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }
    
    
    
    
}
