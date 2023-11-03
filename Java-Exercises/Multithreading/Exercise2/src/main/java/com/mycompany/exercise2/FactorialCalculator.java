package com.mycompany.exercise2;

class FactorialCalculator extends Thread {
  // Define variables
  private long start, end;
  private SharedSum totalSum;

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

    totalSum.add(partialSum);
  }
}