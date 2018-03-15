package RandomWalk2D;

import jmrm.Parameters;

// This class loads our simulation parameters from params.txt.
public class Params {
  // Integers.
  public static int N_RUNS; // Number of runs.
  public static int LENGTH; // World's length (lambda in previous models).
  public static int START_LENGTH; // Initial border between Ns and Ms (n in previous models).
  public static int WIDTH; // Width of the array (i.e. the size of the new dimension).
  
  // Doubles.
  public static double WN; // Relative fitness of Neanderthals (fitness of Moderns is 1 - this).
  
  // Strings.
  public static String WRITE_PATH; // The directory to which data is written.
  
  // Set each parameter by loading its current value from the Parameters class.
  public static void set() {
    // Set all the ints.
    N_RUNS = Parameters.getInt("N_RUNS");
    LENGTH = Parameters.getInt("LENGTH");
    START_LENGTH = Parameters.getInt("START_LENGTH");
    WIDTH = Parameters.getInt("WIDTH");
    
    // Set all the doubles.
    WN = Parameters.getDouble("WN");
    
    // Set all the strings.
    WRITE_PATH = Parameters.getStr("WRITE_PATH");
  }
}