public class Store {

    private String name;
    private Owner owner;
    private Inventory inventory;
    private Client[] clients;
    private int clientCount;



    public Store(String name, Owner owner){
        this.name = name;
        this.owner = owner;
        this.inventory = new Inventory();
        this.clients = new Client[100];
        this.clientCount = 0;

    }

    public Owner getOwner(){return owner;}

    public String getName() {return name;}

    public Inventory getInventory(){return inventory;}

    public Client getClients(int index) {return clients[index];}

    public int getClientCount() {return clientCount;}

    public void setName(String newName){this.name = newName;}

    public void setOwner(Owner owner) {this.owner = owner;}

    public boolean add(Client client){
        if (clientCount < 100){
            clients[clientCount] = client;
            clientCount++;
            return true;
        } else {
            return false;
        }

    }



}
