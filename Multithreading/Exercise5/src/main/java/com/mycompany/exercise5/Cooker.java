package com.mycompany.exercise5;
import java.util.concurrent.Semaphore;

class Cooker extends Thread {
    // Define the atributes of the thread
    private Semaphore sem;
    private Table table;

    // Define the constructor
    public Cooker(Semaphore sem, Table table) {
        this.sem = sem;
        this.table = table;
    }

    // Define the method which will be executed
    @Override
    public void run() {
        try {
            while (true) {
                sem.acquire();
                if (table.isEmpty){
                    cookMeal(table);
                }
                sem.release();
                /*if (table.isEmpty){
                    sem.acquire();
                    cookMeal(table);
                    sem.release();
                }*/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void cookMeal(Table table) {
        try {
            System.out.println(Thread.currentThread().getName() + " is cooking.");
            Thread.sleep((long) (1000 + Math.random() * 6000));
            table.setEmpty(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
