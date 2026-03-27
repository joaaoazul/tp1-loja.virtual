public class Store {

    private String name;
    private Owner owner;
    private Inventory inventory;
    private Client[] clients;
    private int clientCount;
    //criar const para max clients




    public Store(String name, Owner owner){
        // Initialize store data and fixed-size client list.
        this.name = name;
        this.owner = owner;
        this.inventory = new Inventory();
        this.clients = new Client[100];
        this.clientCount = 0;

    }

    public Owner getOwner(){
        // Return the current store owner.
        return owner;
    }

    public String getName() {
        // Return the store name.
        return name;
    }

    public Inventory getInventory(){
        // Return the store inventory.
        return inventory;
    }

    public Client getClients(int index) {
        // Return a client at the given position.
        return clients[index];
    }

    public int getClientCount() {
        // Return how many clients are registered.
        return clientCount;
    }

    public void setName(String newName){
        // Update the store name.
        this.name = newName;
    }

    public void setOwner(Owner owner) {
        // Update the owner reference.
        this.owner = owner;
    }

    public boolean add(Client client){
        // Add a client if there is still capacity.
        if (clientCount < 100){
            clients[clientCount] = client;
            clientCount++;
            return true;
        } else {
            return false;
        }

    }



}
