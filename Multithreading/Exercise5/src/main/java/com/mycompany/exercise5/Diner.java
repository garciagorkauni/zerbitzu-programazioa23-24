package com.mycompany.exercise5;
import java.util.concurrent.Semaphore;

class Diner extends Thread {
    // Define the atributes of the thread
    private Semaphore sem;
    private Table table;

    // Define the constructor
    public Diner(Semaphore sem, Table table) {
        this.sem = sem;
        this.table = table;
    }

    // Define the method which will be executed
    @Override
    public void run() {
        try {
            while (true) {
                sem.acquire();
                if (!table.isEmpty){
                    eatMeal();
                }
                sem.release();
                walk();
                /*if (!table.isEmpty){
                    sem.acquire();
                    eatMeal();
                    sem.release();
                    walk();
                }*/
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eatMeal() {
        try {
            System.out.println(Thread.currentThread().getName() + " is eating.");
            Thread.sleep((long) (1000 + Math.random() * 6000));
            table.setEmpty(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void walk() {
        try {
            System.out.println(Thread.currentThread().getName() + " is walking.");
            Thread.sleep((long) (1000 + Math.random() * 6000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}