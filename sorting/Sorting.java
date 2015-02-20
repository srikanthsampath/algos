import java.util.Arrays;


public class Sorting {

    public static class QuickSort {

         private int[] numbers;

         public QuickSort(int[] input) {
             numbers = Arrays.copyOf(input, input.length);
         }

         public void quickSort(int begin, int end) {
             print();
             // Single elements
             if (begin >= end)
                 return;
 
             // Find the partition element.  It's in its right place
             int q = partition(begin, end);
            
             System.out.println("Partition Index: " + q + " Elem: " + numbers[q]);
             // Recursively solve the subarrays around the partition
             quickSort(begin, (q - 1));
             quickSort((q + 1), end);
         }         


         private int partition(int left, int right) {
             System.out.println("Left: " + left + " Right: " + right);
         
             // Identify the pivot
             int pivot = numbers[left];

             int i = left;
             int j = right;
 
             System.out.println("Pivot: " + pivot + " I: " + i + " J: " + j);
            
             // Go through the array and find the correct spot for the pivot
             while (i < j) {
                 while (numbers[i] <= pivot && i < right) // <= is needed so that we go past the pivot
                     i++;

                 while (numbers[j] > pivot)
                     j--;

        
                 if (i < j) { // Swap if possible
                     System.out.println(" Swapping " + i + " and " + j + " " + numbers[i] + " " + numbers[j]);
                     swap(i, j);
                 }
             } 

             // Put the pivot in the right place
             swap(left, j);
             return j;
         } 

         private void swap(int from, int to) {
             int temp = numbers[from];
             numbers[from] = numbers[to];
             numbers[to] = temp;
         }

         private void print() { 
             for (int num: numbers)
                 System.out.print(num + " ");

              System.out.println();
         }
    }


    public static class MergeSort {
         private int[] numbers;
         private int[] tempArray;

         public MergeSort(int[] input) {
             numbers = Arrays.copyOf(input, input.length);
             tempArray = new int[numbers.length]; // Temporary storage for the merge
         }


         public void mergeSort(int begin, int end) {
             int mid = begin + (end - begin)/2 ;

             if (begin < end)  {
                 mergeSort(begin, mid); // recursively divide
                 mergeSort((mid + 1), end);
                 merge(begin, mid, end); // and merge
             }
         } 

         private void merge(int begin, int mid, int end) {

             //Copy to the temporary array
             for (int i = begin; i <= end; i ++)
                 tempArray[i] = numbers[i];

             
             int i = begin, j = mid + 1, k = begin;

             while (i <= mid && j <= end) { // Merge looking at the smaller values
                 if (tempArray[i] <= tempArray[j]) {
                     numbers[k] = tempArray[i];
                     i++;
                 } else if (tempArray[i] > tempArray[j]) {
                     numbers[k] = tempArray[j];
                     j++;
                 } 
                 k++;
             }

             while (i <= mid) { // Need to do only the remnants of the first half
                 numbers[k] = tempArray[i]; // as the second half is already there
                 i ++;
                 k ++;
             }
                   
         }

       void print() {
           for (int val : numbers) 
               System.out.print(val + " " );
           
           System.out.println(); 
       }
    }


    public static void main(String[] args) {

       int[] values = new int[args.length];
       int i = 0;

       for (String arg: args) {
           Integer value = Integer.valueOf(arg);
           values[i++] = value.intValue();
       }

       System.out.println("Doing Quick Sort");

       QuickSort qs = new QuickSort(values);
       qs.quickSort(0, (values.length -1));
       qs.print();

       System.out.println("Doing Merge Sort");

       MergeSort ms = new MergeSort(values);
        
       ms.mergeSort(0, (values.length - 1));

       ms.print();  
    }








}
