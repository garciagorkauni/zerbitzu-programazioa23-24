package com.mycompany.exercise4;

class Table {
    // Define the variables
    private int availableTables;

    // Define the constructor
    public Table(int initialTables) {
        availableTables = initialTables;
    }

    // Define the synchronized methods
    public synchronized void acquireTable() throws InterruptedException {
        while (availableTables == 0) {
            wait();
        }
        availableTables--;
    }

    public synchronized void releaseTable() {
        availableTables++;
        notify();
    }
}

