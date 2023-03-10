import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This is a program that repeatedly asks user to enter a number to compute its
 * sqrt with Newton iteration.
 *
 * @author Lee
 *
 */
public final class Newton1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton1() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @return estimate of square root
     */
    private static double sqrt(double x) {
        //the first estimate of sqrt of x
        double r = x;
        //make sure the relative error is less then 0.01%
        while (Math.abs(Math.pow(r, 2) - x) / x > Math.pow(0.0001, 2)) {
            r = (r + x / r) / 2;
        }
        return r;
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
        //ask the user whether to compute a sqrt
        String answer = "y";
        while (answer.equals("y")) {
            out.println(
                    "Do you wanna compute the sqrt of a number? Enter y for yes:");
            answer = in.nextLine();
            if (answer.equals("y")) {
                // ask the user to enter a number to compute the sqrt
                out.println("Plear enter a number:");
                double n = in.nextDouble();
                out.println(sqrt(n));
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
