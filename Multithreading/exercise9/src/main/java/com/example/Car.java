package com.example;

public class Car extends Thread{
    // Define class atribute/s
    private Parking parking;
    private int carId;

    // Define constructor
    public Car(Parking parking, int carId){
        this.parking = parking;
        this.carId = carId;
    }

    // Define the method to be executed
    @Override
    public void run(){
        
        try {
            while(true){
                // Simulate parking entrance
                parking.getSem().acquire();

                // Park in a parking lot
                for(ParkingLot parkingLot : parking.getParkingLots()){
                    if(parkingLot.getSem().tryAcquire()){
                        parkingLot.setParkedCarId(carId);  
                        System.out.println("ENTRANCE: Car " + carId + " parks in parking lot " + parkingLot.getParkingLotId()
                                            + "\nAVAILABLE SPACES: " + calculateAvailableParkingLots()
                                            + "\nParking: " + parking
                                            + "\n");
                        
                        // Let other cars use the parking
                        parking.getSem().release();

                        // Simulate some work
                        simulateWork();

                        // Simulate parking exit
                        parking.getSem().acquire();

                        // Let free a parking lot
                        parkingLot.setParkedCarId(0);
                        parkingLot.getSem().release();
                        System.out.println("EXIT: Car " + carId + " exiting."
                                            + "\nAVAILABLE SPACES: " + calculateAvailableParkingLots()
                                            + "\nParking: " + parking
                                            + "\n");
                    }
                }
                // Let other cars use the parking
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
            if (parkingLot.getParkedCarId() == 0){
                availableParkingLots++;
            }
        }
        return availableParkingLots;
    }
}
