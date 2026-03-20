public class Owner extends User{

    public Owner(String name, double balance){
        // Initialize an owner with name and balance.
        super(name, balance);
    }

    @Override
    public String getUserType() {
        // Identify this user as the store owner.
        return "Owner";
    }

}
