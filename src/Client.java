public class Client extends User{

    private Inventory inventory;

    public Client(String name, double balance) {
        // Initializes a client with a personal inventory.
        super(name, balance);
        this.inventory = new Inventory();
    }

    public Client(String name){
        // Creates a client with the default starting balance.
        this(name, 100);
    }

    @Override
    public String getUserType() {
        // Identify this user as a client.
        return "Client";
    }

    public Inventory getInventory(){
        // Returns the inventory owned by this client.
        return inventory;
    }




}
