import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIo {
    public ArrayList<String> readData(String path) {
        ArrayList<String> products = new ArrayList();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String [] parts = line.trim().split(",");
                if(parts.length == 3){
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    int Id = Integer.parseInt(parts[2].trim());
                    products.add(String.valueOf(new Product(name, price,Id)));
                } else {

                    System.out.println("ugyldig");
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return products;
    }

    public static void saveData(List<String> Users, String path, String header) {
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(header + "\n"); //Giv csv filen en header
            for (String s : Users) {
                writer.write(s + "\n"); //"Tess, 40000";
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("something went wrong when writing to file");
        }
    }

    public String[] readBoardData(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        int counter = 0;

        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data[counter] = line;
                counter++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return data;

    }
}

