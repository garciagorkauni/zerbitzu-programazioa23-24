package com.mycompany.exercise2;

class SharedSum {
    private long totalSum = 0;

    public synchronized void add(long partialSum) {
        totalSum += partialSum;
    }

    public long getTotalSum() {
        return totalSum;
    }
}