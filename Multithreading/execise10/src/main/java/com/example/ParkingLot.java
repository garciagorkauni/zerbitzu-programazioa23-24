package com.example;

import java.util.concurrent.Semaphore;

public class ParkingLot {
    // Define class atribute/s
    private Semaphore sem;
    private int parkingLotId;
    private int parkedVehicleId;

    // Define constructor
    public ParkingLot(int parkingLotId){
        this.sem = new Semaphore(1);
        this.parkingLotId = parkingLotId;
        this.parkedVehicleId = 0;
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

    public int getParkedVehicleId() {
        return parkedVehicleId;
    }

    public void setParkedVehicleId(int parkedVehicleId) {
        this.parkedVehicleId = parkedVehicleId;
    }
}
