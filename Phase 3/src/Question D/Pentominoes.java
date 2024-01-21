

import java.util.ArrayList;

public class Pentominoes {

    public int value;
    public int num;
    public int[][][] array;
    public ArrayList<int[][][]> rotateT = new ArrayList<>();
    public ArrayList<int[][][]> rotateP = new ArrayList<>();
    public ArrayList<int[][][]> rotateL = new ArrayList<>();
    public ArrayList<int[][][]> rotate = new ArrayList<>();

    public Pentominoes(char c) {
        if (c == 'T') {
            this.value = 5;
            this.num = 1;
            this.initRotations(this.rotateT, PentTRot0);
            this.rotate = this.rotateT;
        }
        if (c == 'P') {
            this.value = 4;
            this.num = 2;
            this.initRotations(this.rotateP, PentPRot0);
            this.rotate = this.rotateP;
        }
        if (c == 'L') {
            this.value = 3;
            this.num = 3;
            this.initRotations(this.rotateL, PentLRot0);
            this.rotate = this.rotateL;
        }
    }

    public int getValue(){
        return this.value;
    }

    public int getNum(){
        return this.num;
    }

    private void initRotations(ArrayList<int[][][]> list, int[][][] pent) {
        // Rotations over X axis
        list.add(rotationXAxis(pent));
        list.add(rotationXAxis(list.get(0)));
        list.add(rotationXAxis(list.get(1)));
        list.add(rotationXAxis(list.get(2)));

        // Rotations over Y axis
        list.add(rotationYAxis(pent));
        list.add(rotationYAxis(list.get(4)));
        list.add(rotationYAxis(list.get(5)));
        list.add(rotationYAxis(list.get(6)));
        list.add(rotationYAxis(list.get(7)));
        list.add(rotationYAxis(list.get(8)));

        // Rotation over Z axis
        list.add(rotationZAxis(pent));
    }
    
    public static int[][][] PentTRot0 = {
        {{1, 1, 1},
        {0, 1, 0},
        {0, 1, 0},},
    };

    public static int[][][] PentLRot0 = {
        {{1, 0},
        {1, 0},
        {1, 0},
        {1, 1},},
    };

    public static int[][][] PentPRot0 = {
        {{1, 1},
        {1, 1},
        {1, 0},},
    };

    public static int[][][] rotationXAxis(int[][][] array) {
        int depth = array.length;
        int rows = array[0].length;
        int cols = array[0][0].length;

        int[][][] rotatedArray = new int[depth][cols][rows];

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    rotatedArray[i][k][rows - 1 - j] = array[i][j][k];
                }
            }
        }

        return rotatedArray;
    }

    public static int[][][] rotationYAxis(int[][][] array) {
        int depth = array.length;
        int rows = array[0].length;
        int cols = array[0][0].length;

        int[][][] rotatedArray = new int[cols][depth][rows];

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    rotatedArray[k][i][rows - 1 - j] = array[i][j][k];
                }
            }
        }

        return rotatedArray;
    }

    public static int[][][] rotationZAxis(int[][][] array) {
        int depth = array.length;
        int rows = array[0].length;
        int cols = array[0][0].length;

        int[][][] rotatedArray = new int[rows][depth][cols];

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < rows; j++) {
                for (int k = 0; k < cols; k++) {
                    rotatedArray[j][i][cols - 1 - k] = array[i][j][k];
                }
            }
        }

        return rotatedArray;
    }

}