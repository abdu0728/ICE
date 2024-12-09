import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cartItems;
    private String username;

    ShoppingCart(String Username) {
        cartItems = new ArrayList<>();
        this.username = Username;

    }

    public void addPruduct(Product product) {
        cartItems.add(product);

    }

    public void viewCart() {
        if (cartItems.isEmpty()) {

            System.out.println("din kurve er tom");
        } else {
            System.out.println("din kurve");
            for (Product p : cartItems)
                System.out.println(p);

        }


    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double calculateTotal() {

        return cartItems.stream().mapToDouble(Product::getPrice).sum();

    }

    public void checkout() {

        System.out.println("Total" + calculateTotal() + "DKK");
        cartItems.clear();
        System.out.println("Tak for dit køb!");
        File file = new File(username + "_shoppingcart.ser");

        if (file.exists() && file.delete()) {
            System.out.println("Kurven er slettet fra filen.");
        } else {
            System.out.println("Kunne ikke slette kurvfilen eller filen eksisterer ikke.");
        }
    }


    public void loadCart() {
        File file = new File(username + "_shoppingcart.ser");
        if (!file.exists()) {
            System.out.println("Ingen eksisterende kurv for brugeren: " + username);
            return;
        }


        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            cartItems = (List<Product>) in.readObject();
            System.out.println("Kurven blev indlæst for bruger: " + username);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Fejl ved indlæsning af kurven: " + e.getMessage());
        }
    }

    public void saveCart() {

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(username + "_shoppingcart.ser"))) {
            out.writeObject(cartItems);
            System.out.println("Kurven blev gemt for bruger: " + username + " " + "indhold" + cartItems);
        } catch (IOException e) {
            System.out.println("Fejl ved lagring af kurven: " + e.getMessage());
        }
    }
}
