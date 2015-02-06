
import java.io.BufferedReader;
import java.io.FileReader;



public class Sudoku {

    // Have a grid
    int[][] grid = new int[9][9];

    public Sudoku(String input) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(input));

            String str = null;
            int i = 0;

            try {
                while ((str = br.readLine()) != null) {
                    String[] splits = str.split("\t");
                    int j=0;
                    for (String split : splits) {
                        grid[i][j] = Integer.valueOf(split).intValue(); 
                        j++;
                    }
                    i++;
                }
             } catch (Exception e) {
                e.printStackTrace();
             } finally {
                if (br != null)
                    br.close();
             }
        } catch (Exception e) {
             System.out.println("Bunch of exceptions"); 
        }
    }


    // Print out the grid
    public void printGrid() {

        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(grid[i][j] + "\t");
            }
            System.out.println();
        }
    }


    // For a given value, check if a row already has it
    private boolean checkRow(int row, int column, int value) {
        for (int i = 0; i < 9; i ++) {
            if (grid[row][i] == value)
               return false;
        }
        return true;
    }

    // For a given value, check if a column already has it
    private boolean checkColumn(int row, int column, int value) {
        for (int i = 0; i < 9; i ++) {
            if (grid[i][column] == value)
               return false;
        }
        return true;
    }

    // Check a subgrid.  
    private boolean checkSubGrid(int row, int column, int value) {
       int rowsBegin = (row/3) * 3;
       int rowsEnd = rowsBegin + 3;

       int columnsBegin = (column/3) * 3;
       int columnsEnd = columnsBegin + 3;

       for (int i = rowsBegin; i < rowsEnd; i++) {
           for (int j = columnsBegin; j < columnsEnd; j++) {
               if (grid[i][j] == value)
                   return false;
           }
       }

       return true;
    }

    // Check if a value is safe for a given row and column
    private boolean isSafe(int row, int column, int value) {
        if (checkRow(row, column, value) && checkColumn(row, column, value)
           && checkSubGrid(row, column, value))
           return true;
        else
           return false;
    }



    // The main method
    // Checks if there are slots to be filled
    // For each such slot
    //    * Tries a value between 1 and 9 and checks if it is safe
    //    * If safe, tries and solves sudoku recursively
    //    * If there is a conflict at some time
    //      Unwind - and retry - this is key backtracking
    //     * Else go ahead with the next slot
    public boolean solveSudoku() {

        int row = 0, column = 0;
        boolean emptySlot = false;
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                if (grid[i][j] == 0) {
                    row = i;
                    column = j;
                    emptySlot = true; 
                    break;
                }
            }
        }       
  
        if (!emptySlot) 
           return true;

        for (int i = 1; i <= 9; i ++) {
            if (isSafe(row, column, i)) {
               grid[row][column] = i;
               if (!solveSudoku()) {
                   grid[row][column] = 0; // Backtrack
                   continue;
               } else {
                   return true;
               }
            }
         }

         return false;
    }

    public static void main(String[] argv) {

        String inputFile = argv[0];

        Sudoku sudokuInstance = new Sudoku(inputFile); 

        if (sudokuInstance.solveSudoku()) {
            sudokuInstance.printGrid();
        }
        else {
            System.out.println("No Solution!");
        }
    }

}
