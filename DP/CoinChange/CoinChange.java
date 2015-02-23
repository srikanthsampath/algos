package algos.DP.CoinChange;


public class CoinChange {
    private int[] coins = {1, 5, 10, 20, 25}; // set of changes
    private int[] nCoins = null;
    

    public CoinChange() {
    }

    // Make change
    public void makeChange(int change) {

        // fill up the # of coins needed in a bottom up manner
        nCoins = new int[change + 1]; // array for change - indexed at 1
        for (int i = 0; i <= change ; i ++) {
            System.out.println("Trying change for: " + i);
            int count = findMinCoins(i);
            nCoins[i] = count;
            System.out.println("Setting change for: " + i + " to " + count);
        }
    }

    private int findMinCoins(int value) {
        int min = 0;
        for (int coin: coins) {
            if (value >= coin) {
               int index = value - coin;
               int candidate = nCoins[index];
               candidate ++;

//               System.out.println("Coin: " + coin);
//               System.out.println("Candidate: " + candidate);

               if (min == 0 || min > candidate) {
                   min = candidate;
//                   System.out.println("Minimum: " + candidate);
               } else {
//                   System.out.println("Minimum: No Change");
               }
            }  
        }

        return min;

    }
    

    // Dirty code for coin change needs cleanup
    public static void main(String[] args) {
        int toChange = Integer.valueOf(args[0]).intValue();

        CoinChange change = new CoinChange();
        change.makeChange(toChange);
//        change.print();
    }


}
