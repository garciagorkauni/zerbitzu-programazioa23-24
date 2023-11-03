package com.mycompany.exercise5;
import java.util.concurrent.Semaphore;

class Diner extends Thread {
    // Define the atributes of the thread
    private Semaphore tables;

    // Define the constructor
    public Diner(Semaphore tables) {
        this.tables = tables;
    }

    // Define the method which will be executed
    @Override
    public void run() {
        try {
            while (true) {
                tables.acquire();
                eatMeal();
                Thread.sleep((long) (1000 + Math.random() * 3000));
                tables.release();
                walk();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eatMeal() {
        System.out.println(Thread.currentThread().getName() + " is eating.");
    }
    private void walk() {
        System.out.println(Thread.currentThread().getName() + " is walking.");
    }
}