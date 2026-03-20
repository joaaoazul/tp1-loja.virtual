public class Inventory {

    private Product[] products;
    private int size;

    public Inventory(){
        // Prepares an empty inventory with fixed capacity.
        this.products = new Product[100];
        this.size = 0;
    }


    // To add a product if there is free space.
    public boolean add(Product p){
        
        if (size < 100) {
            products[size] = p;
            size ++;
            return true;
        } else {
            return false;
        }
    }
    
    // Add's a copy of the product bought or, if it has already that product in the inv, increases the stock, doesn't create a new product.
    public boolean add(Product p, int quantity){
        for (int i = 0; i < size; i++){
            if(products[i].getName().equals(p.getName())){
                int currentStock = products[i].getStock();
                products[i].setStock(currentStock + quantity);
                return true;
            }
        }
        Product copy = new Product(p.getName(), p.getPrice(), quantity);
        return add(copy);
    }

    //Removes inventory products by index
    public void remove(int index) {
        if(index < 0 || index >= size){
            return;
        }
        for (int i = index; i < size -1; i++){
            products[i]  = products[i+1];
        }
        products[size -1] = null;
        size--;
    }

    //Getter from a product by index
    public Product get(int index) {
       
       if (index < 0 || index >= size) {
           return null;
       }
       return products[index];
    }

    public int getSize() {
        // Return the number of stored products.
        return size;
    }


    //Setter for the inventory size (used internally).
   public void setSize(int size){
        this.size = size;
   }
}
