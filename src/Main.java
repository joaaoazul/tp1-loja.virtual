import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Creates initial store owner and store data.
        Owner owner = new Owner("Rui Costa", 100.0);

        Store store = new Store("Ricky Chinaware", owner);
        //The store's theme is a chinaware or porcelain store, ikea like, that sells plates, mugs, silverware, etc.

        Client client1 = new Client("Vangelis Pavlidis", 100.0);
        Client client2 = new Client("Cristiano R. Aveiro", 100.0);
        store.add(client1);
        store.add(client2);

        Product dinnerPlate = new Product("Dinner Plate", 10.0, 20);
        Product sidePlate = new Product("Side Plate", 5.0, 20);
        Product coffeeMug = new Product("Coffee Mug", 2.0, 20);
        Product fork = new Product("Fork", 1.5, 20);
        store.getInventory().add(dinnerPlate);
        store.getInventory().add(sidePlate);
        store.getInventory().add(coffeeMug);
        store.getInventory().add(fork);


        StoreInterface ui = new StoreInterface(store);

        // Starts the console interface loop.
        ui.start();
    }
}
