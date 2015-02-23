Algorithm:

* Maintain 2 heaps - max heap and min heap.  basically a left and right. 
* Elements in the max heap is less than elements in the min heap
* Ensure the two heaps have equal # of elements or differ by 1
* If they have equal elements - median is the mid point of the two
* If one of them has more (say max) - then the root is the median.

1. Insert the first two elements into 2 heaps - the larger one in the min heap and the smaller one in the max heap
2. for every element insertion
      * ensure that # of elements in max heap = # elements in min heap 
                 or
      * # of elements in max heap is one more than # of elements in min heap
      * size and value constaints are maintained.
      * you may have to choose different strategies depending on odd or even numbers so far.  Pop and push top elements in the two heaps.
