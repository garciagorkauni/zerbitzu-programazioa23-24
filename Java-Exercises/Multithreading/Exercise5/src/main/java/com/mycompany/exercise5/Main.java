import java.util.concurrent.Semaphore;
import Diner;

class Main {  
  public static void main(String[] args) {

    // Create the shared semaphore
    Semaphore tables = new Semaphore(2, true);

    //Create the threads
    Diner diner1 = new Diner(tables);
    Diner diner2 = new Diner(tables);
    Diner diner3 = new Diner(tables);

    // Start the threads
    diner1.start();
    diner2.start();
    diner3.start();
  }
}