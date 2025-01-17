import java.util.Random;

/**
 * This class implements a Greedy Algorithm to place pentominoes in a 3D space (truck).
 */
public class GreedyPentominoes {

    // Create parcel objects
    Pentominoes T = new Pentominoes('T');
    Pentominoes P = new Pentominoes('P');
    Pentominoes L = new Pentominoes('L');

    public final int WIDTH = 8;
    public final int LENGTH = 33;
    public final int HEIGHT = 5;
    public int[][][] truck = new int[this.WIDTH][this.LENGTH][this.HEIGHT];
    public int score = 0;
    public int added = 0;
    private Random rand = new Random();

    /**
     * Starts the Greedy Algorithm and calls the necessary methods to fill the truck.
     */
    public void fillTruck() {
        Pentominoes[] pentominoes = {this.T, this.P, this.L};

        for (Pentominoes pentomino : pentominoes) {
            for (int rotation = this.rand.nextInt(10); rotation < 10; rotation++) {
                while(this.placementAttempt(pentomino, pentomino.rotate.get(rotation))) {
                    this.added++;
                }
            }
        }
    }

    /**
     * Tries to place a pentomino at an available position.
     * 
     * @param pentominoObj Pentomino obejct to be consider.
     * @param pentomino Pentomino array to be considered.
     * @return True if the pentomino can be placed, false if not.
     */
    public boolean placementAttempt(Pentominoes pentominoObj, int[][][] pentomino) {
        for (int x = 0; x < this.truck.length; x++) {
            for (int y = 0; y < this.truck[0].length; y++) {
                for (int z = 0; z < this.truck[0][0].length; z++) {
                    if (this.canAdd(pentominoObj, pentomino, x, y, z)) {
                        this.addParcel(pentominoObj, pentomino, x, y, z);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Evaluates overlapping pieces, to check if the parcel can be added to that position.
     * 
     * @param pentominoObj Pentomino object to be considered.
     * @param pentomino Pentomino array to be considered.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     * @return True if the pentomino can be placed, false otherwise.
     */
    public boolean canAdd(Pentominoes pentominoObj, int[][][] pentomino, int x, int y, int z) {

        if(x + pentomino.length > this.truck.length ||
         y + pentomino[0].length > this.truck[0].length ||
          z + pentomino[0][0].length > this.truck[0][0].length) return false;

        for (int i = x; i < x + pentomino.length; i++) {
            for (int j = y; j < y + pentomino[0].length; j++) {
                for (int k = z; k < z + pentomino[0][0].length; k++) {
                    if (this.truck[i][j][k] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Adds the pentomino to the truck's array.
     * 
     * @param pentominoObj Pentomino object to be considered.
     * @param pentomino Pentomino array to be considered.
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param z Z coordinate.
     * @return Score.
     */
    public int addParcel(Pentominoes pentominoObj, int[][][] pentomino, int x, int y, int z) {
        for (int i = x; i < x + pentomino.length; i++) {
            for (int j = y; j < y + pentomino[0].length; j++) {
                for (int k = z; k < z + pentomino[0][0].length; k++) {
                    if(pentomino[i-x][j-y][k-z]!=0)
                    this.truck[i][j][k] = pentominoObj.getNum();
                }
            }
        }
        this.score += pentominoObj.getValue();
        return this.score;
    }

    /**
     * Prints the truck's matrix.
     */
    public void printMatrix() {
        System.out.println("Final Truck Matrix:");
        for (int i = 0; i < this.truck.length; i++) {
            for (int j = 0; j < this.truck[0].length; j++) {
                for (int k = 0; k < this.truck[0][0].length; k++) {
                    System.out.print(this.truck[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Prints the score.
     */
    public void printScore(){
        System.out.println("Question D Total score: " + this.score);
    }

    /**
     * Serves as the entry point for executing the algorithm.
     *
     * @param args The command-line arguments (unused in this context).
     */
    public static void main(String[] args) {
        GreedyPentominoes alg = new GreedyPentominoes();
        alg.fillTruck();
        alg.printMatrix();
    }
}

