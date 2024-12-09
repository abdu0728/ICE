import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderHandler {
    private List<Orders> orderHistory;
    String username;
    OrderHandler(String username){
   this.username = username;
  orderHistory = new ArrayList<>();


    }

    public void addOrder(List<Product> product, double totalPrice){
        Orders newOrder = new Orders(product,totalPrice, LocalDateTime.now());

        orderHistory.add(newOrder);
    }
    public void viewOrderHistory(){

        for(Orders o : orderHistory)
            System.out.println("dine ordre" + o);

    }
    public void saveOrder() {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(username + "_orderHistory.ser"))) {
            out.writeObject(orderHistory);
            System.out.println("Kurven blev gemt for bruger: " + username + "indhold" + orderHistory);
        } catch (IOException e) {
            System.out.println("Fejl ved lagring af kurven: " + e.getMessage());
        }
    }

    public void loadOrderHistory() {
        File file = new File(username + "_orderHistory.ser");
        if (!file.exists()) {
            System.out.println("Ingen eksisterende kurv for brugeren: " + username);
            return;
        }


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            orderHistory = (List<Orders>) in.readObject();
            System.out.println("Kurven blev indlæst for bruger: " + username + " " + orderHistory);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af kurven: " + e.getMessage());
        }
    }

    public void returnProductuct(int productId , int orderId){

        System.out.println("Order History: " + orderHistory);
        System.out.println("Indtastet Order ID: " + orderId);
        System.out.println("indtastet ProduktId" + productId);
    Orders targetOrder = orderHistory.stream().filter(Orders -> Orders.getOrderId() == orderId).findFirst().orElse(null);
    if(targetOrder == null){
        System.out.println("kunne ikke finde OrderId");

    }

        List<Product> products = targetOrder.getProducts();
    Product productToReturn = products.stream().filter(product -> product.getId() == productId).findFirst().orElse(null);
    if(productToReturn == null){

        System.out.println("kunne ikke finde produktet med productId"+ " "+ productId);
    }
    products.remove(productToReturn);
    System.out.println("produktet er fjernet fra din Ordrerliste og du får " + productToReturn.getPrice() + "dkk" + " " + "tilbage");
     saveOrder();
    }

}
