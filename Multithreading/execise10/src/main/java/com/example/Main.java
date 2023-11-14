package com.example;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Ask for the atributes
        Scanner scanner = new Scanner(System.in);
        System.out.print("Parking lots in your parking: ");
        int numOfParkingLots = scanner.nextInt();
        System.out.print("Cars in your system: ");
        int numOfCars = scanner.nextInt();
        System.out.print("Trucks in your system: ");
        int numOfTrucks = scanner.nextInt();
        scanner.close();

        // Create the objects depending on the user's input
        Parking parking = new Parking(new Semaphore(1, true), numOfParkingLots);

        Car[] cars = new Car[numOfCars];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(parking, (i+1));
        }

        Truck[] trucks = new Truck[numOfTrucks];
        for (int i = 0; i < trucks.length; i++) {
            trucks[i] = new Truck(parking, (i+101));
        }

        // Start all the threads
        for (int i = 0; i < cars.length; i++) {
            cars[i].start();
            Thread.sleep(500);
        }

        for (int i = 0; i < trucks.length; i++) {
            trucks[i].start();
            Thread.sleep(500);
        }
    }
}