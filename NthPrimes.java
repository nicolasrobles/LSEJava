// Sieve.java
// Class demo by THC.
// Modified by Scot Drydale on 5/2/99 to only sieve up to sqrt(n).
// Converted to Java by Scot Drysdale on 4/17/00.
// Implements the sieve of Eratosthenes to find prime numbers.

public class NthPrimes
{
  public static void main(String args[]) 
  {
    final int N = 27000000;                 // Look at numbers less than N
  
    // The array named prime has the following interpretation:
    // We want to set the entries so that if prime[i] indicates
    // whether i is prime.
    // We only care about entries 2 to N-1.
  
    boolean [] prime = new boolean[N];

    // Initialize all numbers from 2 to N-1 to be prime.
    for (int i = 2; i < prime.length; i++)
      prime[i] = true;

    // Do the actual sieving, up to sqrt(n). Any factor larger than sqrt(n)
    // will be paired with a factor smaller than sqrt(n).
    // Search for primes, and declare each multiple of a prime to be not prime.
    for (int candidate = 2; candidate <= Math.sqrt(prime.length); candidate++)
      if (prime[candidate])       // Is candidate prime?
      {
        // If so, then declare every multiple not prime.
        for (int multiple = 2 * candidate; multiple < prime.length; 
                 multiple += candidate)
          prime[multiple] = false;
      }
  
    // Print out the primes.
    final int LINESIZE = 10;        // How many on a line
    int whichOne = 1;               // Count of which prime we're printing
  
    System.out.println("The prime numbers less than " + prime.length + " are");
  
    for (int i = 2; i < N; i++)
      if (prime[i]) {
        System.out.print(i + "  ");         // Print it
        if (whichOne++ % LINESIZE == 0)     // End of a line?
          System.out.println();             // If so, go to the next line
        }
      
    System.out.println();
  }
}