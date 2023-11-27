package com.mycompany.azterketa;

import java.util.concurrent.Semaphore;

public class Table {
    // Define class atributes
    boolean isFull;
    boolean isEmpty;
    Semaphore sem;
    String specialization;

    // Define the class constructor
    public Table(Semaphore sem, String specialization) {
        this.sem = sem;
        this.specialization = specialization;
        isFull = false;
        isEmpty = true;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    // Define getters and setters
    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public Semaphore getSem() {
        return sem;
    }

    public void setSem(Semaphore sem) {
        this.sem = sem;
    }
}
