package com.example;

import java.util.concurrent.Semaphore;

public class Parking {
    // Define class atributes
    private Semaphore sem;
    private int numOfParkingLots;
    private ParkingLot[] parkingLots;

    // Define constructor
    public Parking(Semaphore sem, int numOfParkingLots){
        this.sem = sem;
        this.numOfParkingLots = numOfParkingLots;
        this.parkingLots = new ParkingLot[numOfParkingLots];
        for (int i = 0; i < parkingLots.length; i++) {
            parkingLots[i] = new ParkingLot(i+1);
        }
    }

    // Define getters and setters
    public int getNumOfParkingLots() {
        return numOfParkingLots;
    }

    public void setNumOfParkingLots(int numOfParkingLots) {
        this.numOfParkingLots = numOfParkingLots;
    }

    public Semaphore getSem(){
        return this.sem;
    }

    public void setSem(Semaphore sem){
        this.sem = sem;
    }

    public ParkingLot[] getParkingLots() {
        return parkingLots;
    }

    public void setParkingLots(ParkingLot[] parkingLots) {
        this.parkingLots = parkingLots;
    }

    // Define toString method
    @Override
    public String toString() {
        String parkingGraph = "";
        for (int i = 0; i < parkingLots.length; i++) {
            parkingGraph += "[" + parkingLots[i].getParkedVehicleId() + "] ";
        }
        return parkingGraph;
    }    
}