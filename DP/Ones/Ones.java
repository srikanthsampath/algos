package algos.DP.Ones;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.Arrays;


// Find the biggest square matrix of Ones
// Lots of assumptions - size of the matrix etc

public class Ones {
    private int[][] numbers; 
    private int[][] size = new int [6][5];

    public Ones(int[][] values) {
        numbers = Arrays.copyOf(values, values.length);

        // Initialize size array with the input array
        for (int i = 0; i < 6; i ++) {
            for (int j = 0; j < 5; j ++) {
                size[i][j] = numbers[i][j];
            }
        }  
    }

    private void calculateSize() {

        // For each element - if the neighboring 3 are 1s - then size is the min of the sizes of the 3 + 1
        for (int i = 1; i < 6; i ++){
            for (int j = 1; j < 5; j ++) {
                if ((numbers[i-1][j-1] == 1 && numbers[i][j-1] == 1 && numbers[i-1][j] == 1) && numbers[i][j] == 1) {
                   int min1 = (size[i-1][j] < size[i][j-1]) ? size[i-1][j] : size[i][j-1];
                   int min = (size[i-1][j-1] < min1)? size[i-1][j-1] : min1; 
                   size[i][j] = min + 1;
                }
            }
        }
    } 

    private void print() {
        for (int i = 0; i < 6; i ++){
            for (int j = 0; j < 5; j ++) {
                 System.out.print(size[i][j] + "\t");
            }
            System.out.println(); 
        }
    }

    private int getMax() {
        int max = 1;

        for (int i = 1; i < 6; i ++){
            for (int j = 1; j < 5; j ++) {
                if (size[i][j] > max)
                    max = size[i][j];
            }
        }
        
        return (max);
    }






    public static void main(String[] args) {
        String file = args[0];

        BufferedReader br = null;
        int[][] input = new int[6][5]; 

        try {
            br = new BufferedReader(new FileReader(file));

            String str = null;

            try {
                int i = 0;
                // Insert entries to the Trie
                while ((str = br.readLine()) != null) {
                    String[] splits = str.split("\t");
                    int j = 0;
                    for (String split : splits) {
                        int val = Integer.valueOf(split).intValue();
                        input[i][j] = val;
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
 
        Ones ones = new Ones(input); 
        ones.calculateSize();
        ones.print();
        System.out.println("Max ones: " + ones.getMax());






    }






}
