package RandomWalk2D;

import jmrm.FileIO;

// This class records and saves the stats we wish to gather.
public class Stats {
  public static String[] general; // Records general simulation data: the winner (Ns or Ms) and the incursion index.
  private static int incursions; // Tracks the incursion index over the course of the simulation.
  
  // Initialize our record keeping.
  public static void init() {
    general = new String[2];
    general[0] = "N\tM\tCycles\tIncursion Index";
    incursions = 0;
  }
  
  // Record stats for this cycle.
  public static void record() {
    // Loop over the portion of the array where Moderns start.
    for(int i = Params.START_DEPTH; i < Params.DEPTH; i++) {
      for(int j = 0; j < Params.WIDTH; j++) {
        // If there's a Neanderthal band in one of these positions, add to the incursion index.
        if(RandomWalk.bands[i][j].type == Band.N) {
          incursions++;
        }
      }
    }
  }
  
  // Save our recorded stats to files.
  public static void save() {
    // Record the winner.
    general[1] = (Band.N == RandomWalk.bands[0][0].type) ? "1\t0" : "0\t1";
    
    // Record the number of cycles.
    general[1] += "\t" + RandomWalk.cycles;
    
    // Rescale the incursion index by the array's depth.
    incursions /= Params.DEPTH;
    
    // If the winner was Neanderthals, record the incursion index as 0. Otherwise, record the incursion index.
    general[1] += (Band.N == RandomWalk.bands[0][0].type) ? "\t0" : "\t" + incursions;
    
    // Get the file path for this simulation, creating a directory for it if one does not already exist.
    String path = FileIO.mkSubdir(Params.WRITE_PATH, RandomWalk.offset * Params.N_RUNS);
    
    // Write our record to the appropriate file.
    FileIO.write(path + "general.tsv", general);
  }
}