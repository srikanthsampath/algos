package algos.DP.MatrixSum;



import java.util.Arrays;




public class MatrixSum {
    private static final int ROWS = 4;
    private static final int COLS = 5;

    private int maxMax = 0;
    private int c1 = 0, c2 = 0, r1 = 0, r2 = 0; // the bounding rectangle

    private int[][]matrix = new int[ROWS][COLS]; // simplify with a concrete example

    public MatrixSum(int[][] input) {
        for (int i = 0; i < ROWS; i ++) {
             for (int j = 0; j < COLS; j ++) {
                 matrix[i][j] = input[i][j];     
             } 
        }
    }

    // Sum for a vector
    public static class SingleSum {
        private int[] values = new int[COLS];
        private int from = 0;
        private int to = 0;
        public SingleSum(int[] input) {
            values = Arrays.copyOf(input, input.length);
        }


        public int findMaxSum() {
            int maxSum = values[0];
            int sum = values[0];
            for (int i = 1; i < values.length; i ++) {
                // if we are negative so far and encounter a positive number
                // reset
                if (sum < 0 && values[i] > 0) {
                    maxSum = values[i];
                    sum = values[i];
                    from = i;
                    to = i;
                    continue; 
                } 
                sum = sum + values[i];
                if (sum > maxSum) {
                   maxSum = sum;
                   to = i;
                }
            }

            return maxSum;
        }

        // Get the boundaries
        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }
    }


    // Find the Marix Sum
    public void findMatrixSum() {

        // Key is Sum up the columns i = 1.. COLS.  j = i+1... COLS
        // This will fix the left and right columns
        // This reduces it to Matrix Sum for a vector.
        for (int i = 0; i < COLS; i ++) {
            for (int j = i+1; j < COLS; j ++) {
                int[] temp = new int[ROWS];
                for (int k = 0; k < ROWS; k++) {
                    for (int l = i; l <= j; l++) {
                        temp[k] = temp[k] + matrix[k][l];
                    }
                }
                for (int m = 0; m < ROWS; m++)
                    System.out.print(temp[m] + " " );
                System.out.println(); 
   
                SingleSum single = new SingleSum(temp);
                int max = single.findMaxSum();
                System.out.println("To consider Max:" + max); 
                // Track the max
                if (max > maxMax) {
                    c1 = i;
                    c2 = j;
                    maxMax = max;
                    r1 = single.getFrom();
                    r2 = single.getTo();  

                    System.out.println("Max: " + maxMax);
                }
      
 
            }
        }

    }

    public int getMaxMax() {
        return maxMax;
    }

    public int getC1() { return c1;}
    public int getC2() { return c2;}
    public int getR1() { return r1;}
    public int getR2() { return r2;}


    public static void main(String[] args) {

        // Motivating Example
        int[][] input = new int[][] {{1,2,-1,-4,20},
                                     {-8,-3,4,2,1},
                                     {3,8,10,1,3},
                                     {-4,-1,1,7,-6}};

        MatrixSum mSum = new MatrixSum(input);
        mSum.findMatrixSum();
        System.out.println("MAX MAX: " + mSum.getMaxMax());
        int r1 = mSum.getR1();
        int r2 = mSum.getR2();
        int c1 = mSum.getC1();
        int c2 = mSum.getC2();
        System.out.println("R1: " + r1 + " R2: " + r2 + " C1: " + c1 + " C2: " + c2);
        System.out.println("Boundaries: " + input[r1][c1] + " " + input[r1][c2] + " " + input[r2][c1] + " " + input[r2][c2]); 
    }
}
