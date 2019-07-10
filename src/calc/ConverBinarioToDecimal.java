package calc;

import java.util.Scanner;
/**
 *
 * @author Taffarel
 */
public class ConverBinarioToDecimal {

    private static void printBinaryform(int number) {

        int remainder;

        if (number <= 1) {
            System.out.print(number);
            return;
        }

        remainder = number % 2;
        printBinaryform(number >> 1);
        System.out.print(remainder);
    }

    public static double ConvertBinarioToDecimal(String binario) {
        try {
            int quantDig = binario.length();

            double resultado = 0;

            for (String arg : binario.split("")) {
                quantDig--;

                //System.out.println(String.valueOf(Double.parseDouble(arg) * Math.pow(2, quantDig)));
                resultado += Double.parseDouble(arg) * Math.pow(2, quantDig);

            }
            System.out.println("-----------------");

            return resultado;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }

        return -1;
    }

    public static double potencia(String d1, String d2) {

        return Math.pow(Double.parseDouble(d1), Double.parseDouble(d2));
    }

    public static void convertDecimalTo() {
        int number;

        Scanner in = new Scanner(System.in);

        System.out.println("Enter a positive integer");
        number = in.nextInt();

        if (number < 0) {
            System.out.println("Error: Not a positive integer");
        } else {

            System.out.print("Convert to binary is:");
            //System.out.print(binaryform(number));
            printBinaryform(number);
        }
    }
    
    public static void convertBinaryToDecimal() {
          String n1;

        Scanner in = new Scanner(System.in);

        boolean x = true;

        while (x) {
            System.out.print("Binario para Decimal:");

            n1 = in.next();

            if (n1.equals("\\q")) {
                System.exit(0);
            } else {
                System.out.println(String.valueOf(ConvertBinarioToDecimal(n1)).replaceAll("\\.\\d+", ""));
            }
        } 
    }

    public static void main(String[] args) {

        convertDecimalTo();

    }
}
