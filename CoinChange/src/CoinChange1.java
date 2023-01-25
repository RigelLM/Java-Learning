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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {
    }

    // Recursion Method
    private static void makeChange(SimpleWriter out, int coinValue,
            int change) {
        int remainder = change % coinValue;
        int amount = change / coinValue;

        switch (coinValue) {
            case 100:
                out.println("The amount of dollar: " + amount);
                break;
            case 50:
                out.println("The amount of half-dollar: " + amount);
                break;
            case 25:
                out.println("The amount of quarter: " + amount);
                break;
            case 10:
                out.println("The amount of dime: " + amount);
                break;
            case 5:
                out.println("The amount of nickel: " + amount);
                break;
            case 1:
                out.println("The amount of penny: " + amount);
                break;
        }

        int nextValue = 0;
        switch (coinValue) {
            case 100:
                nextValue = 50;
                break;
            case 50:
                nextValue = 25;
                break;
            case 25:
                nextValue = 10;
                break;
            case 10:
                nextValue = 5;
                break;
            case 5:
                nextValue = 1;
                break;
        }

        if (remainder != 0) {
            makeChange(out, nextValue, remainder);
        }
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
        makeChange(out, 100, amount);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
