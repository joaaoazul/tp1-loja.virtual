public abstract class User {

    private String name;
    private double balance;

    //Construtor
    public User(String name, double balance){
        this.name = name;
        this.balance = balance;

    }

    public String getName(){
        return name;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    public void deposit(double amount){
        balance += amount;
    }

    public boolean withdraw(double amount){
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public abstract String getUserType();
}
