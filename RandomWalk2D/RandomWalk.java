package RandomWalk2D;

import java.util.ArrayList; // Used when assigning neighbors to agents.
import java.util.Random; // Randomization.

import jmrm.Utilities; // Used to sample multinomial distributions.
import jmrm.FileIO; // Used to write data to a file.
import jmrm.Parameters; // Manages loading and iterating over parameters.

// This class is the entry point for running the simulation.
public class RandomWalk {
  public static int run; // Which independent run we're on.
  public static int offset; // If we're running more than one simulation at once, offset which directory we're writing to.
  public static int steps; // How many steps we've taken so far in the current simulation.
  
  public static Band[][] bands; // Stores our 2D array of bands.
  
  public static Random rand = new Random(); // Randomization.
  
  // Run the simulation. If passed an argument, it indicates the offset.
  public static void main(String[] args) {
    // Load simulation parameters from file.
    Parameters.load("params.txt");
    
    // Get the offset parameter.
    if(args.length > 0) {
      offset = Integer.parseInt(args[0]);
      System.out.println("[OFFSET " + offset + "]");
    }
    else {
      offset = 0;
    }
    
    // Iterate over a parameter (whichever parameter uses a range, rather than a fixed value).
    for(Parameters.iteration = 0; Parameters.iteration < Parameters.nIterations; Parameters.iteration++) {
      Params.set();
      
      // If iterating over a parameter, write to a subdirectory named after the current parameter setting.
      if(Parameters.nIterations > 1) {
        String strIteration = Parameters.getIterated() + "=" + Parameters.getDouble(Parameters.getIterated());
        Params.WRITE_PATH += "/" + strIteration;
        System.out.print("[" + strIteration + "]");
      }
      
      // Run the simulation with the current parameters.
      for(run = 0; run < Params.N_RUNS; run++) {
        System.out.print(" " + (run + 1));
        run();
      }
      System.out.println();
    }
  }
  
    // Run the simulation once.
  private static void run() {
    // Initialize record keeping.
    Stats.init();
    
    // Reset to the first step.
    steps = 0;
    resetBands();
    resetNeighbors();
    
    // Iterate until one type goes extinct.
    for(steps = 1; !extinct(); steps++) {
      deathBirth();
      Stats.record();
    }
    
    // Save our stats once we're done.
    Stats.save();
  }
  
  // Create our 2D array of bands.
  public static void resetBands() {
    bands = new Band[Params.LENGTH][Params.WIDTH];
    
    // Create a band in every position in the array.
    for(int i = 0; i < Params.LENGTH; i++) {
      for(int j = 0; j < Params.WIDTH; j++) {
        // Create Neanderthal bands to the left of the border's starting position, and Modern bands to the right.
        if(i < Params.START_LENGTH) {
          bands[i][j] = new Band(Band.N);
        }
        else {
          bands[i][j] = new Band(Band.M);
        }
      }
    }
  }
  
  // Assign each band the correct neighbor(s).
  public static void resetNeighbors() {
    // Assign neighbors to each band.
    for(int i = 0; i < Params.LENGTH; i++) {
      for(int j = 0; j < Params.WIDTH; j++) {
        ArrayList<Band> neighbors = new ArrayList<Band>(4); // Build up a list of the current band's neighbors.
        
        // Left neighbor.
        if(i > 0) {
         neighbors.add(bands[i - 1][j]);
        }
        
        // Right neighbor.
        if(i < Params.LENGTH - 1) {
          neighbors.add(bands[i + 1][j]);
        }
        
        // Top neighbor.
        if(j > 0) {
          neighbors.add(bands[i][j - 1]);
        }
        
        // Bottom neighbor.
        if(j < Params.WIDTH - 1) {
          neighbors.add(bands[i][j + 1]);
        }
        
        // Assign the band's neighbors to the band.
        bands[i][j].neighbors = neighbors.toArray(new Band[neighbors.size()]);
      }
    }
  }
  
  // Kill a band and replace it.
  public static void deathBirth() {
    // Select a random band for death.
    int x = rand.nextInt(Params.LENGTH);
    int y = rand.nextInt(Params.WIDTH);
    
    // Get the band's neighbors' fitnesses.
    double[] fitnesses = new double[bands[x][y].neighbors.length];
    for(int i = 0; i < fitnesses.length; i++) {
      fitnesses[i] = bands[x][y].neighbors[i].getFitness();
    }
    
    // Pick a winner to reproduce by sampling a multinomial distribution corresponding to the neighbors' fitnesses.
    int winner = Utilities.sampleMultinom(fitnesses);
    
    // Replace the dead band's type with the winner's ype.
    bands[x][y].type = bands[x][y].neighbors[winner].type;
  }
  
  // Is one strategy or the other extinct?
  public static Boolean extinct() {
    // Get the type of an arbitrary band.
    int type = bands[0][0].type;
    
    // If any of the other bands don't match the first one, neither strategy is extinct.
    for(int i = 0; i < Params.LENGTH; i++) {
      for(int j = 0; j < Params.WIDTH; j++) {
        if(bands[i][j].type != type) {
          return false;
        }
      }
    }
    
    // If all bands were of the same type, the other strategy is extinct.
    return true;
  }
}