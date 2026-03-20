public class Product {

    private String name;
    private double price;
    private int stock;

    //Constructor of the product
    public Product(String name, double price, int stock){
       
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price){
        // Create a product with default stock.
        this(name, price, 20);
    }

    // Getters, they return the current value of the product's attributes
    public String getName() {return this.name;}

    public double getPrice() {return this.price;}

    
    public int getStock() {return this.stock;}

  
    // Setters, they upgrade the product's attributes directly
    public void setName(String name) {this.name = name;}
 
    public void setPrice(double price) {this.price = price;}

    public void setStock(int stock) {this.stock = stock;}


}
