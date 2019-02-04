package RandomWalk2D;

// This class instantiates a single band in the 2D array.
public class Band {
  public static final int N = 0; // Neanderthals are represented by 0.
  public static final int M = 1; // Moderns are represented by 0.
  
  public int type; // Band's type (N or M).
  public Band[] neighbors; // Band's neighbors.
  
  // Create a band of the given type.
  public Band(int type) {
    this.type = type;
  }
  
  // Get the band's fitness, based on its type.
  public double getFitness() {
    return (type == M) ? Params.WM : Params.WN;
  }
}