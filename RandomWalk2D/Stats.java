package RandomWalk2D;

import jmrm.FileIO;

// This class records and saves the stats we wish to gather.
public class Stats {
  public static String[] general; // Records general simulation data: the winner (Ns or Ms) and the invasion area.
  private static int area; // Tracks the total invasion area over the course of the simulation.
  
  // Initialize our record keeping.
  public static void init() {
    general = new String[2];
    general[0] = "N\tM\tArea";
    area = 0;
  }
  
  // Record stats for this time step.
  public static void record() {
    // Loop over the portion of the array where Moderns start.
    for(int i = Params.START_LENGTH; i < Params.LENGTH; i++) {
      for(int j = 0; j < Params.WIDTH; j++) {
        // If there's a Neanderthal band in one of these positions, add the depth of this invasion to the total area.
        if(RandomWalk.bands[i][j].type == Band.N) {
          area += i + 1 - Params.START_LENGTH;
        }
      }
    }
  }
  
  // Save our recorded stats to files.
  public static void save() {
    // Record the winner.
    general[1] = (Band.N == RandomWalk.bands[0][0].type) ? "1\t0" : "0\t1";
    
    // If the winner was Neanderthals, record the invasion area as 0. Otherwise, record the invasion area.
    general[1] += (Band.N == RandomWalk.bands[0][0].type) ? "\t0" : "\t" + area;
    
    // Get the file path for this simulation, creating a directory for it if one does not already exist.
    String path = FileIO.mkSubdir(Params.WRITE_PATH, RandomWalk.offset * Params.N_RUNS);
    
    // Write our record to the appropriate file.
    FileIO.write(path + "general.tsv", general);
  }
}