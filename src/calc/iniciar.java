package calc;

import java.util.Scanner;

/**
 *
 * @author Taffarel
 */
public class iniciar {

    public static double ConvertBinarioToDecimal(String binario) {
        try {
            int quantDig = binario.length();

            double resultado = 0;

            for (String arg : binario.split("")) {
                quantDig--;

                //System.out.println(String.valueOf(Double.parseDouble(arg) * Math.pow(2, quantDig)));
                resultado += Double.parseDouble(arg) * Math.pow(2, quantDig);

            }
            System.out.println("-------");

            return resultado;
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }

        return -1;
    }

    public static double potencia(String d1, String d2) {

        return Math.pow(Double.parseDouble(d1), Double.parseDouble(d2));
    }

    public static void main(String[] args) {
        new TelaCalc().setVisible(true);
    }
}
