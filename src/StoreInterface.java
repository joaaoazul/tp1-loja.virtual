import java.util.InputMismatchException;
import java.util.Scanner;
import com.validlib.StringValidator;


public class StoreInterface {
    private Store store;
    private Scanner scanner;
    private User currentUser;

    // Initializes the interface, scanner and sets the current user to the first client
    public StoreInterface(Store store) {
        this.store = store;
        this.scanner = new Scanner(System.in);
        this.currentUser = store.getClients(0);
    }

    // Shows the client list and switches the current user to the selected client
    public void handleSwitchClient() {
        System.out.println("List of clients: ");
        for (int i = 0; i < store.getClientCount(); i++) {
            System.out.println((i + 1) + " - " + store.getClients(i).getName() + " " + store.getClients(i).getBalance());
        }

        System.out.print("Enter the index number: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > store.getClientCount()) {
            System.out.println("Invalid index number, please, try again.");
            return;
        }

        currentUser = store.getClients(index - 1);
        System.out.println("User switched to Client " + currentUser.getName() + ".");
    }

    // Switches the current user to the store owner
    public void handleSwitchOwner() {
        currentUser = store.getOwner();
        System.out.println("User switch to Store Owner.");
    }

    // Registers a new client with the given name and a starting balance of 100
    public void handleClientRegistration() {
        System.out.print("Please insert new Client's name: ");
        String name = scanner.nextLine();
        store.add(new Client(name, 100.0));
        System.out.println("Client " + name + " successfully created!");
    }

    // Reads a product name and price and adds it to the store inventory with stock of 20
    public void handleProductAdd() {
        System.out.print("Input the product name: ");
        String name = scanner.nextLine();

        System.out.print("Input the product price: ");
        double price;
        try {
            price = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: invalid price. Please enter a number.");
            scanner.nextLine(); // limpar o buffer
            return;
        }

        System.out.println("Input the qtd of stock: ");
        int stock;
        try {
            stock = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Error: invalid price. Please enter a number.");
            scanner.nextLine();
            return;
        }

        if (stock != 0) {
            store.getInventory().add(new Product(name, price, stock));
        } else {
            store.getInventory().add(new Product(name, price));
        }
        ;
    }
    // Shows the product list and removes the selected product from the store inventory
    public void handleProductRemoval() {
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        store.getInventory().remove(index - 1);
        System.out.println("Product removed successfully.");
    }

    // Shows the product list and allows editing the name or price of the selected product
    public void handleProductEdit() {
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        Product p = store.getInventory().get(index - 1);
        String name = p.getName();
        double price = p.getPrice();

        System.out.println("Select an option: " + "\n" + "1. Change the name;" + "\n" + "2. Change the price.");
        int choosing = scanner.nextInt();
        scanner.nextLine();

        if (choosing == 1) {
            p.setName(Helpers.askName(scanner));
        } else if (choosing == 2) {
            p.setPrice(Helpers.askPrice(scanner));
        }

    }

    // Shows the product list and increases the stock of the selected product by the given amount
    public void handleStock() {
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        Product p = store.getInventory().get(index - 1);

        System.out.print("Enter how much stock do you want to add? ");
        int amount = scanner.nextInt();
        scanner.nextLine();
        p.setStock(p.getStock() + amount);

        System.out.println("Increased stock of product by " + amount + ".");
    }

    // Reads a new store name and renames the store
    public void handleStoreRebrand() {

        while(true){
            System.out.print("Enter new store name: ");
            String newName = scanner.nextLine();
            if (StringValidator.isNullOrEmpty(newName)){
                System.out.println("The input is invalid, try again.");
            } else if (!StringValidator.hasLen(newName, 3, 12)){
                System.out.println("The input is invalid, try again.");
            } else {
                store.setName(newName);
                System.out.println("Store rebranded to " + newName + ".");
                showStoreView();
                break;
            }

        }

    }

    // Allows the client to buy a product from the store by selecting index and quantity
    public void handleBuy() {
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index.");
            return;
        }

        Product p = store.getInventory().get(index - 1);

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (quantity > p.getStock()) {
            System.out.println("We don't have enough " + p.getName() + " to fulfil this order of " + quantity + ".");
            return;
        }

        double totalCost = p.getPrice() * quantity;
        if (totalCost > currentUser.getBalance()) {
            double needed = totalCost - currentUser.getBalance();
            System.out.println("You don't have enough balance to buy " + quantity + " " + p.getName() + ". You need at least more " + needed + ".");
            return;
        }

        currentUser.withdraw(totalCost);
        p.setStock(p.getStock() - quantity);

        Client client = (Client) currentUser;
        client.getInventory().add(p, quantity);
        store.getOwner().deposit(totalCost);

        System.out.println("Successfully bought " + quantity + " of " + p.getName() + ".");
        showStoreView();
    }

    // Allows the client to return a product from their inventory and receive a full refund
    public void handleReturn() {
        Client client = (Client) currentUser;

        System.out.println(client.getName() + "'s inventory");

        if (client.getInventory().getSize() < 1) {
            System.out.println("(Your inventory is empty...\nTime to go shopping.)");
            return;
        }

        for (int i = 0; i < client.getInventory().getSize(); i++) {
            Product p = client.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > client.getInventory().getSize()) {
            System.out.println("Invalid product index.");
            return;
        }

        Product clientProduct = client.getInventory().get(index - 1);

        Product storeProduct = null;
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            if (store.getInventory().get(i).getName().equals(clientProduct.getName())) {
                storeProduct = store.getInventory().get(i);
                break;
            }
        }

        if (storeProduct == null) {
            System.out.println("Product no longer exists in the store.");
            return;
        }

        double refund = clientProduct.getPrice() * clientProduct.getStock();
        if (!store.getOwner().withdraw(refund)) {
            System.out.println("The store owner doesn't have enough balance to refund.");
            return;
        }

        storeProduct.setStock(storeProduct.getStock() + clientProduct.getStock());
        currentUser.deposit(refund);
        client.getInventory().remove(index - 1);

        System.out.println("Successfully returned " + clientProduct.getStock() + " of product " + clientProduct.getName() + ".");
        showStoreView();
    }

    // Displays the current client's inventory with index, name, stock and price of each product
    public void handleInventory() {
        Client client = (Client) currentUser;

        System.out.println(client.getName() + "'s Inventory:");

        if (client.getInventory().getSize() < 1) {
            System.out.println("(Your inventory is empty...\nTime to go shopping.)");
            return;
        }

        for (int i = 0; i < client.getInventory().getSize(); i++) {
            Product p = client.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }
    }

    // Displays the store name, current user, product list and available actions based on user type
    private void showStoreView() {
        System.out.println("------");
        System.out.println("=== " + store.getName() + "  ===");
        System.out.println("Current user: " + currentUser.getName() + " (" + (currentUser instanceof Owner ? "Owner" : "Client") + ") " + currentUser.getBalance());

        System.out.println("------");
        System.out.println("Products");
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.println("------");
        System.out.println("Actions");

        if (currentUser instanceof Owner) {
            System.out.println("store - Show store view");
            System.out.println("add - Add a product");
            System.out.println("remove - Remove a product");
            System.out.println("edit - Edit a product");
            System.out.println("stock - Increase stock of a product");
            System.out.println("rename - Change store name");
            System.out.println("client - Switch to a client");
            System.out.println("register - Add new client");
            System.out.println("creator - Easter Egg");
        } else {
            System.out.println("store - Show store view");
            System.out.println("buy - Buy a product");
            System.out.println("inv - List client inventory items");
            System.out.println("return - Return a product that was bought");
            System.out.println("client - Switch to another client");
            System.out.println("owner - Switch to owner of store");
            System.out.println("register - Add new client");
        }


    }

    // Main loop that reads and executes user actions until the program is terminated
    public void start() {
        while (true) {
            showStoreView();

            System.out.print("Enter action: ");
            String action = scanner.nextLine().trim().toLowerCase();

            if (currentUser instanceof Owner) {
                // Owners actions
                switch (action) {
                    case "store":
                        showStoreView();
                        break;
                    case "add":
                        handleProductAdd();
                        break;
                    case "remove":
                        handleProductRemoval();
                        break;
                    case "edit":
                        handleProductEdit();
                        break;
                    case "stock":
                        handleStock();
                        break;
                    case "rename":
                        handleStoreRebrand();
                        break;
                    case "client":
                        handleSwitchClient();
                        break;
                    case "register":
                        handleClientRegistration();
                        break;
                    case "creator":
                        System.out.println("Made by - a91152 João Azul");
                        break;
                    default:
                        System.out.println("Invalid action " + action + ".");
                }

            } else {
                // Clients actions
                switch (action) {
                    case "store":
                        showStoreView();
                        break;
                    case "buy":
                        handleBuy();
                        break;
                    case "inv":
                        handleInventory();
                        break;
                    case "return":
                        handleReturn();
                        break;
                    case "client":
                        handleSwitchClient();
                        break;
                    case "owner":
                        handleSwitchOwner();
                        break;
                    case "register":
                        handleClientRegistration();
                        break;
                    default:
                        System.out.println("Invalid action " + action + ".");
                }
            }

        }

    }
}