package com.example;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    // Define class atribute/s
    private Semaphore sem;
    private int parkingLotId;
    private int parkedCarId;

    // Define constructor
    public ParkingLot(int parkingLotId){
        this.sem = new Semaphore(1);
        this.parkingLotId = parkingLotId;
        this.parkedCarId = 0;
    }

    // Define getters and setters
    public Semaphore getSem() {
        return sem;
    }

    public void setSem(Semaphore sem) {
        this.sem = sem;
    }

    public int getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(int parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public int getParkedCarId() {
        return parkedCarId;
    }

    public void setParkedCarId(int parkedCarId) {
        this.parkedCarId = parkedCarId;
    }
}
