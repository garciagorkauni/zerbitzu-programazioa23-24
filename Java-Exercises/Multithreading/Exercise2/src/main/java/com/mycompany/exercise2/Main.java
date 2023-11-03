package com.mycompany.exercise2;

class Main {
  // Define the variables
  private static final int N = 1000000;
  private static final int THREADS_QUANTITY = 4;
  
  public static void main(String[] args) {
    // Create an object to share betwen the threads
    SharedSum totalSum = new SharedSum();

    // Create an array with the required number of threads and start them
    FactorialCalculator[] threads = new FactorialCalculator[THREADS_QUANTITY];

    for (int i = 0; i < THREADS_QUANTITY; i++) {
      long start = i * (N / THREADS_QUANTITY) + 1;
      long end = (i + 1) * (N / THREADS_QUANTITY);      
      threads[i] = new FactorialCalculator(start, end, totalSum);
      threads[i].start();
    }

    // Wait the thread finish
    for (int i = 0; i < THREADS_QUANTITY; i++) {
        try {
            threads[i].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Show the result
    System.out.println("Total Sum: " + totalSum.getTotalSum());
  }
}