import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This is a program that repeatedly asks user to enter a number to compute its
 * sqrt with Newton iteration and asks for another number for to be the boundary
 * error.It would automatically exit when the first number is negative.
 *
 * @author Lee
 *
 */
public final class Newton4 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton4() {
    }

    /**
     * Computes estimate of square root of x to within relative error 0.01%.
     *
     * @param x
     *            positive number to compute square root of
     * @param error
     *            positive number of the estimation error
     * @return estimate of square root
     */
    private static double sqrt(double x, double error) {
        if (x != 0.0) {
            //the first estimate of sqrt of x
            double r = x;
            //make sure the relative error is less then 0.01%
            while (Math.abs(Math.pow(r, 2) - x) / x > Math.pow(error, 2)) {
                r = (r + x / r) / 2;
            }
            return r;
        }
        return x;
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
        out.println("Plear enter a number and the error:");
        double n = in.nextDouble();
        double m = in.nextDouble();
        //break when the number is negative
        while (n >= 0) {
            out.println(sqrt(n, m));
            out.println("Plear enter a new number:");
            n = in.nextDouble();
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
