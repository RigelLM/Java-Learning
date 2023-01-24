import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hilstone2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hilstone2() {
    }

    /**
     * Generates and outputs the Hailstone series starting with the given
     * integer.
     *
     * @param n
     *            the starting integer
     * @param out
     *            the output stream
     */
    private static void generateSeries(int n, SimpleWriter out) {
        int term = n;
        int amount = 0;
        //make sure the starting number is positive
        if (n > 0) {
            while (term != 1) {
                out.println(term);
                if (term % 2 == 0) {
                    term = term / 2;
                } else {
                    term = term * 3 + 1;
                }
                //add the amount for every number in the series
                amount++;
            }
            //print the last term of the series
            out.println("1");
            out.println("The length of the series is " + (amount + 1));
        } else {
            out.println("Please enter a positive number.");
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
         * ask the users for a starting number
         */
        int n = in.nextInteger();
        //generate series
        generateSeries(n, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
