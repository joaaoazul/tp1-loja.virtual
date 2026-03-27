import java.util.Scanner;
import com.validlib.StringValidator;

public class Helpers {

    public static String askName(Scanner scanner){
        while(true){
            System.out.println("Enter the product's new name: ");
            String name = scanner.nextLine();

            if(StringValidator.isNullOrEmpty(name) || !StringValidator.hasMinLen(name, 2)){
                System.out.println("The input is invalid, try again.");

            } else {
                return name;
            }
        }
    }

    public static double askPrice(Scanner scanner){
        while(true){
            System.out.println("Enter the new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            if(price <= 0){
                System.out.println("The price is invalid, try again.");
            } else {
               return price;
            }
        }
    }

}
