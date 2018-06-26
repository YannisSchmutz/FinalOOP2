package serialization;

import java.io.*;
import java.util.ArrayList;

/*
 * Writes list of objects to file, reads them again afterwards
 * Object-list-serialization
 * Object input stream
 * Object output stream
 *
 */

/*

The steps for making a deep copy using serialization are:
1. Ensure that all classes in the object's graph are serializable.
2. Create input and output streams.
3. Use the input and output streams to create object input and object output streams.
4. Pass the object that you want to copy to the object output stream.
5. Read the new object from the object input stream and cast it back to the class of the object you sent.


 */

public class ProductList {



    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
        System.out.println("ProductList");

        // Define output stream
        ObjectOutputStream out = new ObjectOutputStream (new FileOutputStream("src/serialization/products.dat"));
        // Define input stream
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/serialization/products.dat"));

        // Create ArrayList of products
        ArrayList<Product> productList = new ArrayList<Product>();

        // Create products and add them to the list
        productList.add(new Product("Product 1", 19.90, 7));
        productList.add(new Product("Product 2", 29.90, 14));
        productList.add(new Product("Product 3", 39.90, 21));

        // Write object list to file
        out.writeObject(productList);


        // Read object list again, !!! Cast needed !!!
        ArrayList<Product> prLst = (ArrayList<Product>) in.readObject();
        // Print out
        System.out.println("Products:");
        System.out.println(prLst);



        out.close();


    }
}
