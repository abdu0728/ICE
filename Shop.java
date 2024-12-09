import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shop {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Login instaceOfLogin = new Login();
        TextUi ui = new TextUi(instaceOfLogin);
        Login login = new Login();
        List<Product> products = new ArrayList<>();
        Orders.loadNextOrderId();
        System.out.println("velkommen til onlineshoppen");
        boolean opretkonto=ui.promptBinary("ønker du at oprette en konto ? Tast Y eller Tast N for at logge ind", "Y","N");
        String username;
        if(opretkonto){
            login.createUser();
            username = login.getuserName();
        } else if (login.loginOption()){
            username = login.getuserName();
        } else {

            System.out.println("brugernavn er forkert");
            return;
        }
       ShoppingCart cart = new ShoppingCart(username);
        String Username = login.getuserName();
        if (Username != null && !Username.isEmpty()) {
            System.out.println("Bruger logged ind som: " + Username);
            OrderHandler handler = new OrderHandler(username);
        } else {
            System.out.println("Brugernavn er ugyldigt.");
        }

        OrderHandler handler = new OrderHandler(Username);
        boolean running = true;
        while (running) {
            System.out.println("1: Vis produkter");
            System.out.println("2: Tilføj til kurv");
            System.out.println("3: vis kurve");
            System.out.println("4: checkout");
            System.out.println("5: vis tidligere ordrer");
            System.out.println("6: returnere produckt");
            System.out.println("7: Afslut");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    ProductReader reader = new ProductReader();
                     products = reader.readProductFromFile(".idea/onlineShop.txt");
                    if (products == null || products.isEmpty()) {
                        products = new ArrayList<>();
                        System.out.println("ingen produkter fundet i filen");

                    } else {
                        System.out.println("Her er en oversigt over produkterne der sælges");
                        for (Product product : products) {
                            System.out.println(product);;

                        }

                    }

                    break;

                case 2:
                    int Id = ui.promptNumeric("indtast Id for produktet");
                    Product selectedProduct = products.stream().filter(product -> product.getId() == Id).findFirst().orElse(null);
                    if(selectedProduct!= null){

                        cart.addPruduct(selectedProduct);
                        System.out.println("du har tilføjet produktet med ID: " + selectedProduct.getId() + "til kurven");
                        cart.saveCart();


                    } else {

                        System.out.println("produktet med ID: " + selectedProduct.getId() + "findes ikke");
                    }
                    break;


                case 3:
                    File file = new File(username + "_shoppingcart.ser");
                    if(file.exists()) {
                        cart.loadCart();
                    } else {

                        System.out.println("file is not found");
                    }
                    cart.viewCart();
            break;

                case 4:
                    double total = cart.calculateTotal();
                    if(total>0) {
                        handler.addOrder(new ArrayList<>(cart.getCartItems()), total);
                        cart.checkout();
                        handler.saveOrder();
                        Orders.saveNextOrderId();


                    } else {

                        System.out.println("der er ikke noget at tjekke ud");


                    } break;
                case 5:

                    handler.loadOrderHistory();

                    break;

                case 6:
                int orderId =ui.promptNumeric("idtast orderId");
                int productId =ui.promptNumeric("indtast productId");
                handler.returnProductuct(productId,orderId);
                    break;

                case 7:

                    running = false;
                    System.out.println("farvel");

            }


        }
    }
}




