import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Dono
        Owner owner = new Owner("John", 100.0);

        // Loja
        Store store = new Store("Fruit Shop", owner);

        // Clientes
        Client client1 = new Client("John", 100.0);
        Client client2 = new Client("Alice", 100.0);
        store.add(client1);
        store.add(client2);

        // Produtos iniciais (obrigatório!)
        Product apple = new Product("Apple", 3.0, 20);
        Product banana = new Product("Banana", 5.0, 20);
        Product strawberry = new Product("Strawberry", 2.0, 20);
        store.getInventory().add(apple);
        store.getInventory().add(banana);
        store.getInventory().add(strawberry);

        // Interface
        StoreInterface ui = new StoreInterface(store);
        ui.start();
    }
}
