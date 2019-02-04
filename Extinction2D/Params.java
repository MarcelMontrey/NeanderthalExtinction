package Extinction2D;

import jmrm.Parameters;

// This class loads our simulation parameters from params.txt.
public class Params {
  // Integers.
  public static int N_RUNS; // Number of independent runs.
  public static int DEPTH; // World's depth (lambda in previous models).
  public static int START_DEPTH; // Initial border between Ns and Ms (n in previous models).
  public static int WIDTH; // Width of the array (i.e. the size of the new dimension).
  
  // Doubles.
  public static double DEATH_RATE; // Each band's probability of dying.
  public static double WM; // Relative fitness of Moderns (WM + WN = 1).
  public static double WN; // Relative fitness of Neanderthals.
  
  // Strings.
  public static String WRITE_PATH; // The directory to which data is written.
  
  // Set each parameter by loading its current value from the Parameters class.
  public static void set() {
    // Set all the ints.
    N_RUNS = Parameters.getInt("N_RUNS");
    DEPTH = Parameters.getInt("DEPTH");
    START_DEPTH = Parameters.getInt("START_DEPTH");
    WIDTH = Parameters.getInt("WIDTH");
    
    // Set all the doubles.
    DEATH_RATE = Parameters.getDouble("DEATH_RATE");
    WM = Parameters.getDouble("WM");
    WN = 1.0 - WM;
    
    // Set all the strings.
    WRITE_PATH = Parameters.getStr("WRITE_PATH");
  }
}