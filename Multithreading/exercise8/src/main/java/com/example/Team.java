package com.example;

public class Team {
    // Define variables
    private int numOfRunners;
    private int leftRunners;

    // Define the constructor
    public Team(int numOfRunners) {
        this.numOfRunners = numOfRunners;
        this.leftRunners = numOfRunners;
    }

    // Define getters and setters
    public int getNumOfRunners() {
        return numOfRunners;
    }

    public void setNumOfRunners(int numOfRunners) {
        this.numOfRunners = numOfRunners;
    }

    public int getLeftRunners() {
        return leftRunners;
    }

    public void setLeftRunners(int leftRunners) {
        this.leftRunners = leftRunners;
    }    
}
