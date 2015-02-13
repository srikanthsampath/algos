package algos.Trie;


import java.io.BufferedReader;
import java.io.FileReader;


/*
* A trie implementation for latin strings
*/

public class Trie {

    private static final int ALPHABET = 26;
    private Trie[] children;	// Pointers to the varioius links
    private boolean isPresent;	// Is the word so far in the dictionary
    private String value;	// Not needed only for debuging
    private static Trie root = null;	// Singleton

    // Singleton
    private Trie() {
        children = new Trie[ALPHABET];
        isPresent = false;
    }

    // Add the string to Trie
    public boolean addString(String str) {
        String lower = str.toLowerCase();
        int length = lower.length();
        Trie current = this;
        for (int i = 0; i < length; i ++) {
            char c = lower.charAt(i);
            int index = c - 'a';
            if (current.children[index] == null) {
                current.children[index] = new Trie(); 
            }
            current = current.children[index]; 
        }

//        System.out.println("Value: " + str);
        current.setValue(lower);
        current.setPresent(true);;
   
        return true;   
    
    } 

    public void setValue (String str) {
        this.value = str;
    }

    public void setPresent(boolean value) {
        isPresent = value;
    }
    

    public boolean findString(String str) {
        String lower = str.toLowerCase();
        int length = lower.length();
        Trie current = this;
  
        // Chase the links down
        for (int i = 0; i < length; i ++) {
            char c = lower.charAt(i);
            int index = c - 'a';
//            System.out.println("Index: " + index);
            current = current.children[index]; 
            if (current == null)
                break; 
        }

        // Return if we terminate with the string of interest
        if ((current != null) && current.getPresent())
            return true;

        return false;
    }

    public boolean getPresent() {
        return isPresent;
    }

    // Return the singleton trie
    public static Trie getTrie() {
        if (root == null) {
            root =  new Trie();
            return (root);
        } else {
            return (root);
        } 
    }

    // Print the Trie
    public static void print(Trie current, String buf) {
        if (current.getPresent())
            System.out.println("WORD: " + buf);
        for (int i = 0; i < ALPHABET; i ++) {
            if (current.children[i] != null) {
                String childBuf = buf + ((char)('a' + i)); 
                print(current.children[i], childBuf);
            }
        }
    }
        


    public static void main(String[] args) {

        String input = args[0];
         
        Trie trie = Trie.getTrie();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(input));

            String str = null;

            try {
                // Insert entries to the Trie
                while ((str = br.readLine()) != null) {
                    trie.addString(str);
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
  
        String buf = new String();
        Trie.print(trie, buf);

        String str = "Hello";
        System.out.println("Finding " + str);
        System.out.println(trie.findString(str));
        str = "World";
        System.out.println("Finding " + str);
        System.out.println(trie.findString("World"));
        str = "Word";
        System.out.println("Finding " + str);
        System.out.println(trie.findString("Word"));
        str = "TABLE";
        System.out.println("Finding " + str);
        System.out.println(trie.findString("TABLE"));
        str = "tablet";
        System.out.println("Finding " + str);
        System.out.println(trie.findString("tablet"));
        str = "tab";
        System.out.println("Finding " + str);
        System.out.println(trie.findString("tab"));
        str = "Number";
        System.out.println("Finding " + str);
        System.out.println(trie.findString("Number"));
    }
}

