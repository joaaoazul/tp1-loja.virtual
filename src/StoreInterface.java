import java.util.Scanner;



public class StoreInterface {
    private final Store store;
    private final Scanner scanner;
    private User currentUser;

    // Initializes the interface, scanner and sets the current user to the first client
    public StoreInterface(Store store) {
        this.store = store;
        this.scanner = new Scanner(System.in);
        this.currentUser = store.getClients(0);
    }

    private void showProductsList(){
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }
    }

    // Shows the client list and switches the current user to the selected client
    public void handleSwitchClient() {
        System.out.println("List of clients: ");
        for (int i = 0; i < store.getClientCount(); i++) {
            System.out.println((i + 1) + " - " + store.getClients(i).getName() + " " + store.getClients(i).getBalance());
        }

        int index = Helpers.askInt(scanner, "Enter the index of the client: ");

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
        String name = Helpers.askName(scanner, "Enter the client's name: ", 3, 20);
        store.add(new Client(name, 100.0));
        System.out.println("Client " + name + " successfully created!");
    }

    // Reads a product name and price and adds it to the store inventory with stock of 20
    public void handleProductAdd() {
        String name = Helpers.askName(scanner, "Enter the product's name: ", 3, 15);

        double price = Helpers.askPrice(scanner, "Input the product price: ");

        int stock = Helpers.askPositiveInt(scanner, "Input the qtd of stock: ");

        store.getInventory().add(new Product(name, price, stock));
    }
    // Shows the product list and removes the selected product from the store inventory
    public void handleProductRemoval() {
        showProductsList();

        int index = Helpers.askPositiveInt(scanner, "Enter product index: ");

        if (index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        store.getInventory().remove(index - 1);
        System.out.println("Product removed successfully.");
    }

    // Shows the product list and allows editing the name or price of the selected product
    public void handleProductEdit() {
        showProductsList();

        int index = Helpers.askPositiveInt(scanner, "Enter product index: ");

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        Product p = store.getInventory().get(index - 1);

        System.out.println();
        int choosing = Helpers.askIntRange(scanner, "Select an option: " + "\n" + "1. Change the name;" + "\n" + "2. Change the price.", 1, 2);

        if (choosing == 1) {
            p.setName(Helpers.askName(scanner, "Enter the new name: ", 3, 15));
        } else if (choosing == 2) {
            p.setPrice(Helpers.askPrice(scanner, "Enter the new price: "));
        }

    }

    // Shows the product list and increases the stock of the selected product by the given amount
    public void handleStock() {
        showProductsList();

        int index = Helpers.askPositiveInt(scanner, "Please enter the product's index: ");

        if (index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        Product p = store.getInventory().get(index - 1);

        int amount = Helpers.askPositiveInt(scanner, "Enter how much stock do you want to add? ");
        p.setStock(p.getStock() + amount);

        System.out.println("Increased stock of product by " + amount + ".");
    }

    // Reads a new store name and renames the store
    public void handleStoreRebrand() {
        String newName = Helpers.askName(scanner, "Enter new store name: ", 3, 12);
        store.setName(newName);
        System.out.println("Store rebranded to " + newName + ".");
        showStoreView();
    }

    // Allows the client to buy a product from the store by selecting index and quantity
    public void handleBuy() {
        showProductsList();

        int index = Helpers.askPositiveInt(scanner, "Enter product index: ");

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index.");
            return;
        }

        Product p = store.getInventory().get(index - 1);

        int quantity = Helpers.askPositiveInt(scanner, "Enter quantity: ");

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

        int index = Helpers.askPositiveInt(scanner, "Enter product index: ");

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

    public void handleBalance(){
        System.out.println("List of clients: ");
        for (int i = 0; i < store.getClientCount(); i++) {
            System.out.println((i + 1) + " - " + store.getClients(i).getName() + " " + store.getClients(i).getBalance());
        }
        int index = Helpers.askPositiveInt(scanner, "Enter the client's index: ");

        if (index > store.getClientCount()) {
            System.out.println("Invalid index, please try again.");
            return;
        }

        Client client = (Client) store.getClients(index -1);

        double amount = Helpers.askPrice(scanner, "Enter the amount to add: ");

        client.deposit(amount);
        System.out.println("Added " + amount + " to " + client.getName() + "'s balance.");


    }

    // Displays the store name, current user, product list and available actions based on user type
    private void showStoreView() {
        System.out.println("------");
        System.out.println("=== " + store.getName() + "  ===");
        System.out.println("Current user: " + currentUser.getName() + " (" + (currentUser instanceof Owner ? "Owner" : "Client") + ") " + currentUser.getBalance());

        System.out.println("------");
        System.out.println("Products");
        showProductsList();

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
            System.out.println("balance - Add balance to a client's account");
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
                    case "balance":
                        handleBalance();
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