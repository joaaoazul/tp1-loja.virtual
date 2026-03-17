public class Owner extends User{

    public Owner(String name, double balance){
        super(name, balance);
    }

    @Override
    public String getUserType() {
        return "Owner";
    }

}
