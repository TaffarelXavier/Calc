/*
 * Feito 07 de Março de 2018
 */
package calc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.nevec.rjm.BigDecimalMath;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import tx.utilitarios.TX_BigDecimal;

/**
 * <p style="font:20px arial;color:green;">
 * Para mostrar os dados serão necessários dois JTextFields. No JTextField1,
 * será mostrado o resultado no primeiro.
 * </p>
 *
 * @author Taffarel
 */
public class Calculadora {

    public Calculadora() {

    }
    /**
     * <p style="font:16px arial">A Variável principal que receberá os valores.
     * <b>Por exemplo:</b> Quando alguém pressionar o número 9, ele será
     * atribuído a esta variável.
     * </p>
     */
    public static BigDecimal algarismo;

    /**
     *
     */
    private static String opedaror;
    /**
     *
     */
    private static String valorOperadorPressionado = "";
    /**
     *
     */
    public static String[] array;

    /**
     *
     */
    private static final BigDecimal BD_ZERO = BigDecimal.ZERO;

    /**
     *
     */
    private static final BigDecimal BD_UM = BigDecimal.ONE;

    /**
     *
     */
    public static BigDecimal BD;

    /**
     *
     * @return
     */
    public static BigDecimal getBD() {
        return BD;
    }

    public static void setBD(BigDecimal BD) {
        Calculadora.BD = BD;
    }

    /**
     *
     * @param txt1 <p style="font:16px arial">O Visor Principal</p>
     * @param numero <p style="font:16px arial">Os números da calculadora: de 0
     * a 9. </p>
     * @param txt2 <p style="font:16px arial">O segundo Visor Principal</p>
     */
    public static void setNumeroX(JFormattedTextField txt1, String numero, JTextField txt2) {
        if (txt1.getText().equals("0.0") || txt1.getText().equals("0")) {
            //txt1.setText("");
            String n = txt1.getText() + numero;
            txt1.setText(n.replace("0", ""));
            algarismo = new BigDecimal(n);
            txt2.setText(numero);
        } else {
            String n = txt1.getText() + numero;

            txt1.setText(n);

            //algarismo = new BigDecimal(n);
            //Valor Padrão
            if (Boolean.valueOf(valorOperadorPressionado)) {
                txt2.setText(txt1.getText() + "" + valorOperadorPressionado);
            } else {
                txt2.setText(txt2.getText() + "" + numero);
            }

        }
    }

    /**
     *
     * @param j1 <p style="font:16px arial">O JTextField principal</p>
     * @param j2 <p style="font:16px arial">O JTextField secundário</p>
     */
    public static void calcular(JTextField j1, JTextField j2) {

        BigDecimal resultado;

        
        

        if (!getTextFiel1(j1)) {

            switch (opedaror) {
                case "x":
                    array = j2.getText().split("x");
                    System.out.println(array[0]);
                    resultado = multiplicar(array[0], j1.getText());
                    j2.setText(array[0] + "" + opedaror + "" + j1.getText() + "=" + resultado);
                    j1.setText(String.valueOf(resultado));
                    break;
                case ":":
                    array = j2.getText().split(":");
                    resultado = dividir(array[0], j1.getText());
                    j2.setText(array[0] + opedaror + Double.valueOf(j1.getText()) + "=" + String.valueOf(resultado));
                    j1.setText(String.valueOf(resultado));
                    break;
                case "+":
                    array = j2.getText().split("\\+");
                    resultado = somar(array[0], j1.getText());
                    j2.setText(array[0] + opedaror + Double.valueOf(j1.getText()) + "=" + String.valueOf(resultado));
                    j1.setText(String.valueOf(resultado));
                    break;
                case "-":
                    array = j2.getText().split("-");
                    //resultado = subtrair(Double.valueOf(array[0]), Double.valueOf(j1.getText()));
                    //j2.setText(array[0] + opedaror + Double.valueOf(j1.getText()) + "=" + String.valueOf(resultado));
                    //j1.setText(String.valueOf(resultado));
                    break;
                case "^":
                    array = j2.getText().split("\\^");
                    //resultado = potencia(Double.valueOf(array[0]), Double.valueOf(j1.getText()));
                    //j2.setText(array[0] + opedaror + Double.valueOf(j1.getText()) + "=" + String.valueOf(resultado));
                    //j1.setText(String.valueOf(resultado));
                    break;
                case "%":
                    calcularPorcentagem(j1, j2);
                    break;
            }

        }
    }

    /**
     * <p style="font:16px arial">Calcular a quantidade, em função do total e a
     * porcentagem que representa</p>
     *
     * @param j1 <p style="font:16px arial">O JTextField principal</p>
     * @param j2 <p style="font:16px arial">O JTextField secundário</p>
     * @return 0
     */
    public static BigDecimal calcularPorcentagem(JTextField j1, JTextField j2) {

        array = j2.getText().split("\\%");

        BigDecimal resul = new BigDecimal(array[0]).divide(new BigDecimal(100)).multiply(algarismo);
//;
        j2.setText(j1.getText() + "% de " + array[0] + " é igual a " + String.valueOf(resul));

        j1.setText(String.valueOf(resul));

        return BigDecimal.ZERO;
    }

    /**
     * <p style="font:16px arial">Verifica se o texto de um jTextField está <br>
     * vazio, igual a zero, ou com espaço
     * </p>
     *
     * @param txt1 <p style="font:16px arial">O JTextField principal</p>
     * @return true <p style="font:16px arial">Se o JTextField está com o valor
     * 0, "" ou " "</p>
     */
    public static boolean getTextFiel1(JTextField txt1) {
        return txt1.getText().equals("0") || txt1.getText().equals("") || txt1.getText().equals(" ");
    }

    /**
     * <p style="font:16px arial">Quando pressionado a tecla CE, remove o último
     * caractere.
     * <b>Por exemplo:</b> Se, no visor principal, tiver 0124568799654, e
     * pressionar a tecla CE, então será removido o números da direita pra
     * esquerda, um por um.
     * </p>
     *
     * @param txt1 <p style="font:16px arial">O JTextField principal</p>
     * @return <p style="font:16px arial">O texto removido da direita pra
     * esquera.</p>
     */
    public static String removeLastChar(JTextField txt1) {
        if (txt1.getText().length() != 0 && !txt1.getText().equals("0")) {
            txt1.setText(txt1.getText().substring(0, txt1.getText().length() - 1));
        } else {
            txt1.setText("0");
        }
        return txt1.getText();
    }

    /**
     * <p>
     * Substitui vírgula por ponto. Aqui, é usada uma regular expressão para
     * fazer as substituições</p>
     *
     * @param txt1 <p style="font:16px arial">O JTextField principal</p>
     * @return String
     */
    public static String substituirVirgularPorPonto(JTextField txt1) {
        txt1.setText(txt1.getText().replaceAll("\\,+", ".").replaceAll("\\.", "."));
        return txt1.getText();
    }

    /**
     * <p style="font:16px arial">Calcula o fatoria de um número</p>
     *
     * @param numero <p style="font:16px arial">O número para calcular o
     * fatorial</p>
     * @return BigDecimal o Fatorial do número
     */
    public static double calculaFatorial(double numero) {
        double i, fact = 1;
        for (i = 1; i <= numero; i++) {
            fact = fact * i;
        }
        return fact;
    }

    /**
     * No JTextField principal,
     *
     * @param txt1 <p style="font:16px arial">Alias</p>
     * @return
     */
    public static BigDecimal setNumeroAsKeyPress(JFormattedTextField txt1) {
        try {

            algarismo = new BigDecimal(TX_BigDecimal.replaceNumber(txt1.getText()));

            return algarismo;
        } catch (NumberFormatException e) {
            //JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        return BigDecimal.ZERO;
    }

    /**
     *
     * @param j1 <p style="font:16px arial">O JTextField principal</p>
     * @param j2 <p style="font:16px arial">O JTextField secundário</p>
     * @param oper <p style="font:16px arial">O Operador: *, /, +, - , ^ e
     * %.</p>
     */
    public static void limparVisorEDevinirOperador(JTextField j1, JTextField j2, String oper) {
        opedaror = oper;
        valorOperadorPressionado = j1.getText();
        j1.setText("");
        j2.setText(algarismo.toString() + "" + oper);
    }

    /**
     * <p style="font:16px arial">Limpa o visor, atribui zero à variável
     * <b>algorismo</b> e atribui "" à variável <b>valorOperadorPressionado</b>
     * </p>
     *
     * @param j1 <p style="font:16px arial">O JTextField principal</p>
     * @param j2 <p style="font:16px arial">O JTextField secundário</p>
     */
    public static void limparVisor(JTextField j1, JTextField j2) {
        j1.setText("0");
        j2.setText("0");
        algarismo = BigDecimal.ZERO;
        valorOperadorPressionado = "";
    }

    /**
     *
     * <p style="font:16px arial">Calcula a potência de um número.</p>
     *
     * @param n1
     * @param n2
     * @return
     */
    public static BigDecimal potencia(String n1, String n2) {
        return BigDecimalMath.powRound(new BigDecimal(n1), new BigInteger(n2));
    }

    public static void main(String[] args) {

        System.out.println(powerBig("5", "2").abs());
    }

    /**
     *
     * @param n1 <p style="font:16px arial">Primeiro fator</p>
     * @param n2 <p style="font:16px arial"></p>
     * @return Retorna o <b>produto</b> de <b>n1 e n2<b/>
     */
    public static BigDecimal multiplicar(String n1, String n2) {
        return new BigDecimal(n1).multiply(new BigDecimal(n2));
    }

    public static BigDecimal dividir(String n1, String n2) {
        try {
            return new BigDecimal(n1).divide(new BigDecimal(n2));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Atenção", JOptionPane.ERROR_MESSAGE);
        }
        return BigDecimal.ZERO;
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static BigDecimal somar(String n1, String n2) {
        return new BigDecimal(n1).add(new BigDecimal(n2));
    }

    /**
     *
     * @param n1
     * @param n2
     * @return
     */
    public static BigDecimal subtrair(String n1, String n2) {
        return new BigDecimal(n1).subtract(new BigDecimal(n2));
    }

    public static void mostrarPI(JTextField jTxt1) {
        //algarismo = (BigDecimal) Math.PI;
        //BigDecimalM
        //jTxt1.setText(String.valueOf(Math.PI));
    }

    public static BigDecimal powerBig(String base, String exponent) {

        BigDecimal ans = new BigDecimal(1.0);
        BigDecimal k = new BigDecimal(1.0);
        BigDecimal t = new BigDecimal(-1.0);
        BigDecimal no = new BigDecimal(0.0);

        BigDecimal pt = new BigDecimal(exponent);

        if (pt != no) {
            BigDecimal absExponent = pt.signum() > 0 ? pt : t.multiply(pt);
            while (absExponent.signum() > 0) {
                ans = ans.multiply(new BigDecimal(base));
                absExponent = absExponent.subtract(BigDecimal.ONE);
            }

            if (pt.signum() < 0) {
                // For negative exponent, must invert
                ans = k.divide(ans);
            }
        } else {
            // exponent is 0
            ans = k;
        }

        return ans;
    }

    public static String getValueOf(BigDecimal a) {
        return String.valueOf(a);
    }

    public static BigDecimal calcularLog(JTextField jTxt1) {
        //jTxt1.setText(getValueOf(Math.log10(algarismo)));
        return BigDecimal.ZERO;
    }

    public static double seno(JTextField jTxt1) {
        //jTxt1.setText(getValueOf(Math.sin(algarismo.doubleValue())));
        return 0;
    }

    public static BigDecimal tangente(JTextField jTxt1) {
        //jTxt1.setText(getValueOf(Math.tan(algarismo)));
        return BD_ZERO;
    }

    public static BigDecimal conseno(JTextField jTxt1) {
        //jTxt1.setText(getValueOf(Math.cos(algarismo)));
        return BD_ZERO;
    }

    public static BigDecimal elevarAoCubo(JTextField j1, JTextField j2) {
        try {
            System.out.println(algarismo);
            //BigDecimal resultado = (BigDecimal) Math.pow(Double.valueOf(j1.getText()), 3);
            //j1.setText(String.valueOf(resultado));
            //j2.setText(j1.getText() + "^3=" + String.valueOf(resultado));
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        return BD_ZERO;
    }

}
