Have a stream of numbers.... how do we maintain a set of k random samples?
1. Store the first k in say an array A
2. From k+1 sample onwards.. (say i)
   * Generate a random number between 1 - i say 'x'
   * If the random  number 'x' < k, replace A[x] with element i


How does this work?
   * For the first k - they are there w/ a probability (k/k) -> 1
   * For k+1 onwards...
	* Pr(i-th element making it) -> k/i
        * Pr(some element getting nuked out] = (k/i) * (1/k) = 1/i
        * Pr(element sticks around once they were in the reservoir) = i-1/i
        * Pr(an element is in the array after i rounds) = (k/i-1)*(i-1/i) = k/i

