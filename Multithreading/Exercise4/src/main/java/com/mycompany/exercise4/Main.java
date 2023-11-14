package com.mycompany.exercise4;

class Main {
    public static void main(String[] args) {
        
        // Create the shared non-primitive object
        Table table = new Table(2);

        // Create the consumer threads
        Diner diner1 = new Diner(table);
        Diner diner2 = new Diner(table);
        Diner diner3 = new Diner(table);

        // Start the threads
        diner1.start();
        diner2.start();
        diner3.start();
    }
}

