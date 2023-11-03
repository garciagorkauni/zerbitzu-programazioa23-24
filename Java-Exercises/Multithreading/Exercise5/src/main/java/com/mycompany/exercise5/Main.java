package com.mycompany.exercise5;
import java.util.concurrent.Semaphore;

class Main {  
  public static void main(String[] args) {
    /*FIX: Create producer class, and table shared object (with boolean
    variable to control if there is something to eat or not). 
    Producer thread will cook if table.isEmpty(), else, it will wait.
    Consumer thread will eat if table.haveMeal(), else, it will wait.*/

    // Create the shared semaphore and table objects
    Semaphore sem = new Semaphore(1);
    Table table = new Table(true);


    //Create the consumer threads
    Diner diner1 = new Diner(sem, table);
    Diner diner2 = new Diner(sem, table);
    
    //Create the producer thread
    Cooker cooker1 = new Cooker(sem, table);

    // Start the threads
    diner1.start();
    diner2.start();
    cooker1.start();
  }
}