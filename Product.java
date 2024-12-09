import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int Id;
    private double price;

    public Product(String name, double price, int Id) {
        this.name = name;
        this.Id = Id;
        this.price = price;

    }

    public int getId() {
        return Id;
    }

    public double getPrice() {
        return price;
    }

    public String toString(){

        return name + " " + "Id: " +  Id + " " +  "price: " + " " + price + " " + "dkk";
    }

}

