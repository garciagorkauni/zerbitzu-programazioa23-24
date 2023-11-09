package com.example;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Create the shared object and a semaphore (as a mutex with 1 permit) to be shared
        Semaphore sem = new Semaphore(1, true);
        Team team = new Team(4);

        // Create the runner threads
        Runner thread1 = new Runner(sem, 1, team);
        Runner thread2 = new Runner(sem, 2, team);
        Runner thread3 = new Runner(sem, 3, team);
        Runner thread4 = new Runner(sem, 4, team);
        System.out.println("All threads created.");

        // Simulate the race
        System.out.print("Ready, ");
        Thread.sleep(500);
        System.out.print("set, ");
        Thread.sleep(500);
        System.out.println("go!");

        thread1.start();
        Thread.sleep(500); // Aded sleep for using semaphore's fairness
        thread2.start();
        Thread.sleep(500);
        thread3.start();
        Thread.sleep(500);
        thread4.start();

        // Wait to the threads
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }finally{
            System.out.println("All threads have finished.");
        }

    }
}
