import java.util.Arrays;

/*
* Find the Longest increasing subsequence using DP
*/


public class LIS {
    private static int[] intArray; 	// Input Array
    private static int[] lisArray;	// LIS lengths
    private static int[] indexArray; 	// Index to track the LIS

    public LIS(int[] input) {
        intArray = Arrays.copyOf(input, input.length);
        lisArray = new int[input.length];     
        indexArray = new int[input.length];
    }


    
    // Find the LIS ending at a particular position
    // Key is that the number at particular position should be 
    // greater than the ending position (subsequence)
    private void doLIS(int position) {
        int currentMax = 0;
        boolean set = false;
        int fromIndex = position;
        for (int j = 0; j < position; j++) {
            if (intArray[position] > intArray[j] && lisArray[j] > currentMax) {
                currentMax = lisArray[j];
                fromIndex = j;
            }
        }
        lisArray[position] = currentMax + 1; 
        indexArray[position] = fromIndex;
    }

    public void generateLIS() {
        for (int i = 0; i < lisArray.length; i ++) {
            doLIS(i); 
        } 
    }


    public void printLIS() {
        int indexMax = 0;
        int valMax = lisArray[0];

        for (int i = 0; i < lisArray.length; i ++) {
            if ( lisArray[i] > valMax) {
                valMax = lisArray[i];
                indexMax = i;
            }
        }

        int count = valMax;
        int curIndex = indexMax;
        while (count > 0) {
            System.out.println("Elem: " + intArray[curIndex]);
            curIndex = indexArray[curIndex];
            count --;
        }
    }



    public static void main(String[] args ) {
        int[] input = new int[args.length];
         
        int count = 0;
        for (String arg: args) {
             int value = Integer.valueOf(arg).intValue();
             input[count++] = value;
        }

        LIS lis = new LIS(input);

        lis.generateLIS();
        lis.printLIS();
    }


}  
