public class Inventory {

    private Product[] products;
    private int size;

    public Inventory(){
        this.products = new Product[100];
        this.size = 0;
    }


    public boolean add(Product p){
        if (size < 100) {
            products[size] = p;
            size ++;
            return true;
        } else {
            return false;
        }
    }

    //Remover produtos do inventário
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

    //Getter do inventário de produtos
    public Product get(int index) {
       if (index < 0 || index >= size) {
           return null;
       }
       return products[index];
    }

    public int getSize() {
        return size;
    }


    //Setter do tamanho do array do inventário
   public void setSize(int size){
        this.size = size;
   }
}
