package com.mycompany.azterketa;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Azterketa {
    public static void main(String[] args) {
        // Create the shared objects
        // Creating a sempahore with 1 permit, it works as a mutex
        Table tableSushi = new Table(new Semaphore(1, true), "Sushi");
        Table tablePasta = new Table(new Semaphore(1, true), "Pasta");
        Table tableMarmitako = new Table(new Semaphore(1, true), "Marmitako");

        Table[] aukerak = new Table[3];
        aukerak[0] = tableSushi;
        aukerak[1] = tablePasta;
        aukerak[2] = tableMarmitako;


        // Create the chef Threads
        Chef chefSushi = new Chef(tableSushi, "Sushi");
        Chef chefPasta = new Chef(tablePasta, "Pasta");
        Chef chefMarmitako = new Chef(tableMarmitako, "Marmitako");

        // Ask for the number of diners and create them
        Scanner in = new Scanner(System.in);
        int numOfDiners = 0;
        while(numOfDiners < 9){
            System.out.print("Enter the number of diners, must be bigger or equals to 9: ");
            numOfDiners = in.nextInt();
        }        

        Diner[] diners = new Diner[numOfDiners];
        for (int i = 0; i < diners.length; i++) {
            diners[i] = new Diner(aukerak, (i+1));
        }

        // Start all the threads
        chefSushi.start();
        chefPasta.start();
        chefMarmitako.start();

        for (int i = 0; i < diners.length; i++) {
            diners[i].start();
        }
    }
}
