class Diner extends Thread {
    // Define the variables
    private Table table;

    // Define the constructo
    public Diner(Table table) {
        this.table = table;
    }

    // Define the method which will be executed
    @Override
    public void run() {
        try {
            while (true) {
                table.acquireTable();
                eatMeal();
                Thread.sleep(1500);
                walk();
                table.releaseTable();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eatMeal() {
        System.out.println(Thread.currentThread().getName() + " is eating.");
    }

    private void walk() {
        System.out.println(Thread.currentThread().getName() + " is walking.");
    }
}

