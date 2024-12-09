import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductReader {
    FileIo io = new FileIo();

    public List<Product> readProductFromFile(String path) {
        List<Product> products = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {

                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {


                String line = scanner.nextLine();
                String[] parts = line.trim().split(";");
                if (parts.length == 3) {
                    try {
                        String name = parts[0];
                        double price = Double.parseDouble(parts[1].trim());
                        int Id = Integer.parseInt(parts[2].trim());

                        Product product = new Product(name, price, Id);
                        products.add(product);

                    } catch (NumberFormatException e){

                        System.out.println("ugyldige data i linjen");
                        e.getMessage();
                    }

                }
            }


        }catch (FileNotFoundException e){
            System.out.println("filen blev ikke fundet");
            e.getMessage();
        }
        return products;
    }
}

