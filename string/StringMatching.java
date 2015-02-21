package algos.string;


// An implementation of rabin karp algorithm
public class StringMatching {
    static final int PRIME = 13;


    static void rkMatch(String pattern, String text) {

        int patternHash = 0;
        int m = pattern.length();
        char[] patternArray = pattern.toCharArray();

	// Find the hash of the pattern taking into account the positions
        for (int i = 0; i < m; i ++) {
            patternHash = patternHash * PRIME + (int)patternArray[i];
        } 
        System.out.println("PATTERN HASH:" + patternHash);

        char[] textArray = text.toCharArray();
        int n = textArray.length;

        int rollingHash = 0;

        // Initial hash
        for (int i = 0; i < m; i ++) {
            rollingHash = rollingHash * PRIME +  (int)textArray[i];
        }

        int sub = (int)Math.pow(PRIME, (m-1));
 
        // Compute the rolling hash and compare to the hash of the pattern
        for (int i = m; i < n; i ++) {
            // This is a constant operation
            rollingHash = (rollingHash - textArray[i-m] * sub) * PRIME + textArray[i];

            System.out.println("ROLLING HASH: " + rollingHash);
            if (rollingHash == patternHash){
                System.out.println("Exhaustive checking at " + (i-m+1));
                // Do the exhaustive check on a hash match
                if (doExhaustiveCheck(pattern, text, (i-m+1)))
                    System.out.println("Match found at: " + (i - m) + "Pattern: " + pattern + " Matched String: " + text.substring(i-m, (i+1)));
                   
            }
        }

    }

    static boolean doExhaustiveCheck(String pattern, String text, int position) {
        int m = pattern.length();
        for (int i = 0, j = position; i < m && j < (position + m); i ++, j++) {
            if (pattern.charAt(i) != text.charAt(j)) { // Arrays will be better
                System.out.println("Not match at pattern: " + pattern.charAt(i) + " text: " + text.charAt(j) + " textpos: " + j);
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        
       String text = "it rains in spain when it rains but not sniar";
       String pattern = "rains";

       // Rabin Karp
       StringMatching.rkMatch(pattern, text);


    }









} 
