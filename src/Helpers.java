import java.util.InputMismatchException;
import java.util.Scanner;
import com.validlib.StringValidator;
import com.validlib.NumberValidator;

public class Helpers {

    public static String askName(Scanner scanner, String prompt, int min, int max){
        while(true){
            System.out.println(prompt);
            String name = scanner.nextLine();

            if(!StringValidator.isNullOrEmpty(name) && StringValidator.hasMinLen(name, min) && StringValidator.hasMaxLen(name, max)) { return name; };
            //else
            System.out.println("The input is invalid, try again.");
        }
    }


    public static double askPrice(Scanner scanner, String prompt){
        while(true){
            try {
            System.out.println(prompt);
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

    public static String formatPrice(double value){
        return String.format("%.2f €", value);

    }

    public static int askPositiveInt(Scanner scanner, String prompt){
        while(true) {
            int value = askInt(scanner, prompt);
            if(NumberValidator.isPositive(value)) {return value;};
            System.out.println("Value must be greater than 0, try again.");
        }
    }

    public static int askIntRange(Scanner scanner, String prompt, int min, int max){
        while(true) {
            int value = askInt(scanner, prompt);
            if(NumberValidator.isInRange(value, min, max)){ return value;}
            System.out.println("The value " + value + " must be between " + min + " and " + max + ". Please try again.");

        }
    }

}
