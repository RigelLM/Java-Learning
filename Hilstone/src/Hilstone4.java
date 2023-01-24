import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Hilstone4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Hilstone4() {
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
        //the length of series
        int amount = 0;
        //the largest term, record the largest term that has been generated
        int largest = term;
        //make sure the starting number is positive
        if (n > 0) {
            while (term != 1) {
                out.println(term);
                if (term % 2 == 0) {
                    term = term / 2;
                    //update the largest term if newly generated term is larger
                    if (term > largest) {
                        largest = term;
                    }
                } else {
                    term = term * 3 + 1;
                    if (term > largest) {
                        largest = term;
                    }
                }
                //add the amount for every number in the series
                amount++;
            }
            //print the last term of the series
            out.println("1");
            //print the length of the series
            out.println("The length of the series is " + (amount + 1));
            //print the largest term of the series
            out.println("The largest term of the series is " + largest);
        }
    }

    /**
     * Repeatedly asks the user for a positive integer until the user enters
     * one. Returns the positive integer.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive integer entered by the user
     */
    private static int getPositiveInteger(SimpleReader in, SimpleWriter out) {
        boolean condition = false;
        int num = 0;
        while (!condition) {
            out.println("Please Enter a positive number:");
            String input = in.nextLine();
            if (FormatChecker.canParseInt(input)) {
                num = Integer.parseInt(input);
                if (num > 0) {
                    condition = true;
                }
            }
        }
        return num;
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
        String answer = "y";
        while (answer.equals("y")) {
            //generate series
            int n = getPositiveInteger(in, out);
            generateSeries(n, out);
            //ask whether to continue
            out.println(
                    "Do you wanna calculate another series? Enter y for yes.");
            answer = in.nextLine();
        }
        out.println("That's it.");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
