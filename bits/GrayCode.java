package algos.bits;

public class GrayCode {


    private static String[] calculateCode(int n) {
       
       // Termination case
       if (n == 1) {
           String[] codes = new String[n*2];
           codes[0] = "0";
           codes[1] = "1";
           return codes;
       }

       // Recursively call 
       String codes[] = calculateCode(n -1);

       int size = codes.length;
       String[] newCodes = new String[codes.length *2];   

       // Copy to 2 arrays and add "0" and "1"
       for (int i = 0; i < codes.length; i++) {
           newCodes[i] = "0" + codes[i]; 
           newCodes[2*size - i -1] = "1" + codes[i];
       }
       return newCodes;
    }




    //Calcualte Gray Code given an integer n
    public static void main(String[] args) {

        int n = Integer.valueOf(args[0]).intValue();

        String[] code = GrayCode.calculateCode(n);
        
        for (int i = 0; i < code.length; i ++)
            System.out.println(code[i]);
     }





}
















