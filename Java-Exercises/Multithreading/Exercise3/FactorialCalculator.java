import SharedSum;
import java.util.concurrent.Semaphore;

class FactorialCalculator extends Thread {
  // Define variables
  private long start, end;
  private SharedSum totalSum;
  private Semaphore sem;

  // Define the constructor of the thread
  public FactorialCalculator(long start, long end, SharedSum totalSum) {
      this.start = start;
      this.end = end;
      this.totalSum = totalSum;
  }
  
  // Calculate the wanted sum
  @Override
  public void run() {
    long partialSum = 0;

    for (long i = start; i <= end; i++) {
        partialSum += i;
    }
    
    //Using a semaphore as a mutex, sum the partial sum to the total one
    sem.acquire();
    totalSum.add(partialSum);
    sem.release();
  }
}