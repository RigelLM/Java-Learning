import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Lee Li
 *
 */
public final class CoinChange2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange2() {
    }

    // Method uses for loop
    private static int[] makeChange(SimpleWriter out, int change) {
        int[] coinValues = { 1, 5, 10, 25, 50, 100 };
        int[] amounts = new int[6];
        for (int i = coinValues.length - 1; i >= 0; i--) {
            int num = change / coinValues[i];
            int remainder = change % coinValues[i];
            change = remainder;
            amounts[i] = num;
        }
        return amounts;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Put your main program code here
         */

        out.println(
                "Please enter the amount(numbers of cents) to make change: ");
        int amount = in.nextInteger();
        int[] amounts = makeChange(out, amount);
        out.println("The amount of dollar: " + amounts[5]);
        out.println("The amount of half-dollar: " + amounts[4]);
        out.println("The amount of quarter: " + amounts[3]);
        out.println("The amount of dime: " + amounts[2]);
        out.println("The amount of nickel: " + amounts[1]);
        out.println("The amount of penny: " + amounts[0]);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
