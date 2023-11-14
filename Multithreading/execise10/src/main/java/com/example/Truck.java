package com.example;

public class Truck extends Thread{
    // Define class atribute/s
    private Parking parking;
    private int truckId;

    // Define constructor
    public Truck(Parking parking, int truckId){
        this.parking = parking;
        this.truckId = truckId;
    }

    // Define the method to be executed
    @Override
    public void run(){
        
        try {
            while(true){
                // Simulate parking entrance
                parking.getSem().acquire();

                // Park in a parking lot
                for(int i = 0; i < parking.getNumOfParkingLots(); i++){
                    if(parking.getParkingLots()[i].getSem().tryAcquire()){
                        int next = i+1;
                        if(next == parking.getNumOfParkingLots()){
                            parking.getParkingLots()[i].getSem().release();
                            continue;
                        }
                        if(parking.getParkingLots()[next].getSem().tryAcquire()){
                            parking.getParkingLots()[i].setParkedVehicleId(truckId);
                            parking.getParkingLots()[next].setParkedVehicleId(truckId);  
                            System.out.println("ENTRANCE: Truck " + truckId + " parks in parking lots " + parking.getParkingLots()[i].getParkingLotId() + " and " + parking.getParkingLots()[next].getParkingLotId()
                                                + "\nAVAILABLE SPACES: " + calculateAvailableParkingLots()
                                                + "\nParking: " + parking
                                                + "\n");
                            
                            // Let other vehicles use the parking
                            parking.getSem().release();

                            // Simulate some work
                            simulateWork();

                            // Simulate parking exit
                            parking.getSem().acquire();

                            // Let free a parking lot
                            parking.getParkingLots()[i].setParkedVehicleId(0);
                            parking.getParkingLots()[next].setParkedVehicleId(0);
                            parking.getParkingLots()[i].getSem().release();
                            parking.getParkingLots()[next].getSem().release();
                            System.out.println("EXIT: Truck " + truckId + " exiting."
                                                + "\nAVAILABLE SPACES: " + calculateAvailableParkingLots()
                                                + "\nParking: " + parking
                                                + "\n");
                        } else {
                            parking.getParkingLots()[i].getSem().release();
                        }
                    }
                }
                // Let other vehicles use the parking
                parking.getSem().release();

                // Simulate some work
                simulateWork();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void simulateWork() throws InterruptedException{
        Thread.sleep((long) (1000 + Math.random() * 10000));
    }

    public int calculateAvailableParkingLots(){
        int availableParkingLots = 0;
        for(ParkingLot parkingLot : parking.getParkingLots()){
            if (parkingLot.getParkedVehicleId() == 0){
                availableParkingLots++;
            }
        }
        return availableParkingLots;
    }
}
