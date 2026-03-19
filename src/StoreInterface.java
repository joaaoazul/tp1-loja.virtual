import java.util.Scanner;

public class StoreInterface {
    private Store store;
    private Scanner scanner;
    private User currentUser;

    public StoreInterface(Store store) {
        this.store = store;
        this.scanner = new Scanner(System.in);

        this.currentUser = store.getClients(0);

    }

    public void handleSwitchClient() {
        System.out.println("List of clients: ");
        for (int i = 0; i < store.getClientCount(); i++) {
            System.out.println((i + 1) + " - " + store.getClients(i).getName() + " " + store.getClients(i).getBalance());
        }
        System.out.println("Enter the index number: ");
        int index = scanner.nextInt();
        scanner.nextLine(); //limpar buffer

        if (index < 1 || index > store.getClientCount()) {
            System.out.println("Invalid index number, please, try again.");
            return;
        }

        currentUser = store.getClients(index - 1);
        System.out.println("User switched to Client " + currentUser.getName() + ".");
    }

    public void handleSwitchOwner() {
        currentUser = store.getOwner();
        System.out.println("User switch to Store Owner.");
    }

    public void handleClientRegistration() {
        System.out.println("Please insert new Client's name: ");
        String name = scanner.nextLine();
        store.add(new Client(name, 100.0));
        System.out.println("Client " + name + "successfully created!");
    }

    public void handleProductAdd() {
        System.out.println("Input the product name: ");
        String name = scanner.nextLine();
        System.out.println("Input the product price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); //limpar buffer
        store.getInventory().add(new Product(name, price, 20));
    }

    public void handleProductRemoval() {
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine(); //limpar buffer

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        store.getInventory().remove(index - 1);
        System.out.println("Product removed successfully.");
    }

    public void handleProductEdit() {
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine(); //limpar buffer

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
            System.out.println("Enter the product's new name: ");
            name = scanner.nextLine();
        } else if (choosing == 2) {
            System.out.println("Enter the new price: ");
            price = scanner.nextDouble();
            scanner.nextLine();
        }

        p.setName(name);
        p.setPrice(price);
    }

    public void handleStock() {

        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine(); //limpar buffer

        if (index < 1 || index > store.getInventory().getSize()) {
            System.out.println("Invalid product index, please try again.");
            return;
        }

        Product p = store.getInventory().get(index - 1);

        System.out.println("Enter how much stock do you want to add?");
        int amount = scanner.nextInt();
        scanner.nextLine();
        p.setStock(p.getStock() + amount);

        System.out.println("Increased stock of product by " + amount + ".");
    }

    public void handleStoreRebrand() {
        System.out.print("Enter new store name: ");
        String newName = scanner.nextLine();
        store.setName(newName);

        System.out.println("Store rebranded to " + newName + ".");
        showStoreView();
    }

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
            System.out.println("We don't have enough " + p.getName() + "to fulfil this order of " + quantity);
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

        System.out.println("Successfully bought " + quantity + " of " + p.getName() + ".");
        showStoreView();
    }

    public void handleReturn() {
        Client client = (Client) currentUser;

        // Mostrar inventário do cliente
        System.out.println(client.getName() + "'s inventory");
        for (int i = 0; i < client.getInventory().getSize(); i++) {
            Product p = client.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x" + p.getStock() + " " + p.getPrice());
        }

        // Pedir índice
        System.out.print("Enter product index: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index < 1 || index > client.getInventory().getSize()) {
            System.out.println("Invalid product index.");
            return;
        }

        Product clientProduct = client.getInventory().get(index - 1);

        // Verificar se o produto ainda existe na loja
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

        // Verificar se o dono tem saldo para reembolsar
        double refund = clientProduct.getPrice() * clientProduct.getStock();
        if (!store.getOwner().withdraw(refund)) {
            System.out.println("Store owner doesn't have enough balance to refund.");
            return;
        }

        // Devolver stock à loja e saldo ao cliente
        storeProduct.setStock(storeProduct.getStock() + clientProduct.getStock());
        currentUser.deposit(refund);

        // Remover do inventário do cliente
        client.getInventory().remove(index - 1);

        System.out.println("Successfully returned " + clientProduct.getStock() + " of product " + clientProduct.getName() + ".");
        showStoreView();
    }



    private void showStoreView() {

        System.out.println(store.getName());
        System.out.println("Current user " + currentUser);

        System.out.println("Products");
        for (int i = 0; i < store.getInventory().getSize(); i++) {
            Product p = store.getInventory().get(i);
            System.out.println((i + 1) + " - " + p.getName() + " x"
                    + p.getStock() + " " + p.getPrice());
        }

        System.out.println("------");
        System.out.println("Actions (Please write the action word)");

        if (currentUser instanceof Owner) {
            System.out.println("store - Show store view");
            System.out.println("add - Add a product");
            System.out.println("remove - Remove a product");
            System.out.println("edit - Edit a product");
            System.out.println("stock - Increase stock of a product");
            System.out.println("rename - Change store name");
            System.out.println("client - Switch to a client");
            System.out.println("register - Add new client");

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


    public void start() {
        while (true) {
            showStoreView();

            System.out.print("Enter action: ");
            String action = scanner.nextLine().trim().toLowerCase();

            if (currentUser instanceof Owner) {
                // ações do owner
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
                    default:
                        System.out.println("Invalid action " + action + ".");
                }

            } else {
                // ações do cliente
                switch (action) {
                    case "store":
                        showStoreView();
                        break;
                    case "buy":
                        handleBuy();
                        break;
                    case "inv":
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
