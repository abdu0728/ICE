import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class Orders implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private int orderId;
    private double totalPrice;
    private static int nextOrderId = 1;
    private LocalDateTime orderDate;

    Orders(List<Product> products, double totalPrice, LocalDateTime orderDate){
        this.products = products;
        this.orderId = nextOrderId++;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;

    }

    public List<Product> getProducts(){
        return products;
    }
    public int getOrderId(){
        return orderId;
    }
    public double getTotalPrice(){
        return totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
public String toString(){
        return "Order Details:\n" +
                "Products: " + products + "\n" +
                "Order ID: " + orderId + "\n" +
                "Total Price: " + totalPrice + " " +  "dkk" +  "\n" +
                "Order Date: " + orderDate;

    }

    public static void saveNextOrderId() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("nextOrderId.ser"))) {
            out.writeInt(nextOrderId);
        } catch (IOException e) {
            System.out.println("Fejl ved lagring af næste ordre-ID: " + e.getMessage());
        }
    }

    public static void loadNextOrderId() {
        File file = new File("nextOrderId.ser");
        if (!file.exists()) {
            return; // Hvis filen ikke findes, starter vi fra 1
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            nextOrderId = in.readInt();
        } catch (IOException e) {
            System.out.println("Fejl ved indlæsning af næste ordre-ID: " + e.getMessage());
        }
    }
}

