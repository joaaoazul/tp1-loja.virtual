public class Product {

    private String name;
    private double price;
    private int stock;

    //Construtor do produto
    public Product(String name, double price, int stock){
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public Product(String name, double price){
        this(name, price, 20);
    }

    //Getters de nome, preço e stock do produto
    public String getName() {return this.name;}

    public double getPrice() {return this.price;}

    public int getStock() {return this.stock;}

    //Setters de nome, preço e stock do produto
    public void setName(String name) {this.name = name;}

    public void setPrice(double price) {this.price = price;}

    public void setStock(int stock) {this.stock = stock;}


}
