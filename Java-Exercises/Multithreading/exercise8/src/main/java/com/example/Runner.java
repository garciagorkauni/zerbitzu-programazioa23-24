package com.example;

import java.util.concurrent.Semaphore;

public class Runner extends Thread {
    // Define the class atribute/s
    private Semaphore sem;
    private int position;
    private Team team;

    // Define the constructor
    public Runner(Semaphore sem, int position, Team team){
        this.sem = sem;
        this.position = position;
        this.team = team;
    }

    // Define the method to be executed
    @Override
    public void run(){
        // Simulate the running
        try {
            sem.acquire();
            runRace();
            if(team.getLeftRunners() > 1){
                System.out.println("I'm done. Passing the baton to child " + (position + 1) + ".");
                team.setLeftRunners(team.getLeftRunners()-1);
            }else{
                System.out.println("I'm done!");
            }
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void runRace() throws InterruptedException{
        System.out.println("I'm thread " + position + ", running...");
        Thread.sleep(2000);
    }

}
