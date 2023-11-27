package com.mycompany.azterketa;

public class Chef  extends Thread{
    // Define class atributes
    Table table;
    String specialization;

    // Define the constructor of the class
    public Chef(Table table, String specialization) {
        this.table = table;
        this.specialization = specialization;
    }

    // Run method to be executed
    @Override
    public void run(){
        // Infinite loop
        while(true){
            // Firstly take the table's semaphore to make the operation atomic
            try {
                table.getSem().acquire();
                if(table.isEmpty()){
                    cookSpecialization();
                    table.getSem().release();
                } else {
                    // If there is meal in the table, let it free
                    table.getSem().release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cookSpecialization() throws InterruptedException{
        System.out.println("Chef (" + specialization + ") is cooking..."); // Show the chef's situation
        sleep((int)(Math.random() * 5000)); // Simulate some work
        table.setEmpty(false);
        table.setFull(true);
        System.out.println("Now there is meal in the table of " + specialization);
    }
}
