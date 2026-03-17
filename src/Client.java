public class Client extends User{

    private Inventory inventory;

    public Client(String name, double balance) {
        super(name, balance);
        this.inventory = new Inventory();
    }

    @Override
    public String getUserType() {
        return "Client";
    }

    public Inventory getInventory(){
        return inventory;
    }




}
