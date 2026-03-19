public class Client extends User{

    private Inventory inventory;

    public Client(String name, double balance) {
        super(name, balance);
        this.inventory = new Inventory();
    }

    public Client(String name){
        this(name, 100);
    }

    @Override
    public String getUserType() {
        return "Client";
    }

    public Inventory getInventory(){
        return inventory;
    }




}
