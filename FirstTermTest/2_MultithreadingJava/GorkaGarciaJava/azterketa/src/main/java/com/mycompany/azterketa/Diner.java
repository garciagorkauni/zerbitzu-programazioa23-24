package com.mycompany.azterketa;

public class Diner extends Thread {
    // Define class atributes
    Table[] aukerak;
    int dinerId;

    // Define the constructor of the class
    public Diner(Table[] aukerak, int dinerId) {
        this.aukerak = aukerak;
        this.dinerId = dinerId;
    }

    // Run method to be executed
    @Override
    public void run(){
        // Infinite loop
        while(true){
            // Select a random table
            int randomTable = (int)(Math.random() * 3);
            try {
                // Take the table's semaphore to make the operation atomic
                // Try taking it, if is not possible, the diner will wait
                aukerak[randomTable].getSem().acquire();
                if(aukerak[randomTable].isFull()){
                    // If the table is full, eat meal and let it free
                    eat(randomTable);
                    aukerak[randomTable].getSem().release();
                } else {
                    // If there is not meal in the table, let it free
                    System.out.println("Diner " + dinerId + " go for a walk because " + aukerak[randomTable].getSpecialization() + " table was not able...");
                    aukerak[randomTable].getSem().release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void eat(int randomTable) throws InterruptedException{
        System.out.println("Diner " + dinerId + " is eating " + aukerak[randomTable].getSpecialization() + "..."); // Show the diner's situation
        sleep((int)(Math.random() * 5000)); // Simulate some work
        aukerak[randomTable].setEmpty(true);
        aukerak[randomTable].setFull(false);
        System.out.println("Now there is not meal in the table of " + aukerak[randomTable].getSpecialization());
    }
}
