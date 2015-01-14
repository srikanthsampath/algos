import java.util.Arrays;


/*
* Problem is to find a sub-vector that has the max sum in a given vector
* Assumption is that there is at least one positive number
* Solution is to use DP.  Keep track of the current max and current sum.
* If current sum > current max -> update it
* If current sum falls below 0 -> reset
* Note that we are just finding positive vectors and tracking the max of those
*/
public class ContiguousSum {

    private int[] values;
    private int maxSum;
    private int begin = 0, end = 0;

    public ContiguousSum(int[] input) {
        values = Arrays.copyOf(input, input.length);
    }


    public int generateMaxSum() {
        int currentSum = 0;
        int totalMax = 0;
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            currentSum = currentSum + values[i];
            if (currentSum > totalMax) {
                end = i;
                totalMax = currentSum;
                System.out.println("Total Max: " + totalMax);
            }
            else if (currentSum < 0) {
                currentSum = 0;
                begin = i;
            }
            System.out.println("Current Sum: " + currentSum);
        }

        return totalMax;
    }

    public void printVector() {
        for (int i = begin+1; i <= end; i++)
            System.out.print(" " + values[i] + " "); 

        System.out.println();
    }




    public static void main(String[] args) {

        int[] input = new int[args.length];
        int count = 0;
        for (String arg: args) {
            int val = Integer.valueOf(arg).intValue();
            input[count++] = val;
        }

        ContiguousSum cs = new ContiguousSum(input);
        
        int maxSum = cs.generateMaxSum(); 

        cs.printVector();
 
        System.out.println("Max Sum: " + maxSum);
    
    }





}
