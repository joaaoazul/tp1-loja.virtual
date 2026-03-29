import java.util.InputMismatchException;
import java.util.Scanner;
import com.validlib.StringValidator;
import com.validlib.NumberValidator;

public class Helpers {

    public static String askName(Scanner scanner){
        while(true){
            System.out.println("Enter the product's new name: ");
            String name = scanner.nextLine();

            if(StringValidator.isNullOrEmpty(name) && StringValidator.hasMinLen(name, 2)) { return name; };
            //else
            System.out.println("The input is invalid, try again.");
        }
    }

    public static double askPrice(Scanner scanner){
        while(true){
            try {
            System.out.println("Enter the new price: ");
            double value = scanner.nextDouble();
            scanner.nextLine();

            if(NumberValidator.isPositive(value)) {return value;};
            System.out.println("Price must be greater than 0, try again.");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again.");
                scanner.nextLine();
            }
        }
    }

    public static int askInt(Scanner scanner, String prompt){
        while(true){
            try{
                System.out.print(prompt);
                int index = scanner.nextInt();
                scanner.nextLine();
                return index;
            } catch (InputMismatchException e) {
                System.out.println("Your input was not valid, try again please.");
                scanner.nextLine();
            }
        }
    }

    public static int askPositiveInt(Scanner scanner, String prompt){
        while(true) {
            int value = askInt(scanner, prompt);
            if(NumberValidator.isPositive(value)) {return value;};
            System.out.println("Value must be greater than 0, try again.");
        }
    }

}
