public abstract class User {

    private String name;
    private double balance;

    //Constructor for the user class
    public User(String name, double balance){
        
        this.name = name;
        this.balance = balance;

    }


    public String getName(){
        // Return the user's name.
        return name;
    }

    public double getBalance(){
        // Return the current account balance.
        return balance;
    }

    public void setBalance(double balance){
        // Directly set the account balance.
        this.balance = balance;
    }

    public void deposit(double amount){
        // Increase balance by the deposited amount.
        balance += amount;
    }

    //To withdraw an amount if there is enough balance.
    public boolean withdraw(double amount){
        
        if (amount > balance) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    @Override
    public String toString(){
        // Build a readable summary of this user.
        return name + " . "+ getUserType() + " . " + balance;
    }

    // Return the concrete role label for this user.
    public abstract String getUserType();
}
