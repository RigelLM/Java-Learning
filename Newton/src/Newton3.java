import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This is a program that repeatedly asks user to enter a number to compute its
 * sqrt with Newton iteration and asks for another number for to be the boundary
 * error.
 *
 * @author Lee
 *
 */
public final class Newton3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Newton3() {
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
        // Check if x equals 0. If so, return 0 directly
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
        String answer;
        double error;
        double n;
        //ask the user whether to compute a sqrt
        out.println(
                "Do you wanna compute the sqrt of a number?Enter y for yes:");
        answer = in.nextLine();
        // if user chooses yes, ask the error and compute the estimation
        if (answer.equals("y")) {
            out.println("Please enter the error:");
            error = in.nextDouble();
            out.println("Please enter a number:");
            n = in.nextDouble();
            out.println(sqrt(n, error));
            // While the answer is yes, continue to ask the user whether to continue
            while (answer.equals("y")) {
                out.println(
                        "Do you wanna compute the sqrt of a number?Enter y for yes:");
                answer = in.nextLine();
                if (answer.equals("y")) {
                    // ask the user to enter a number to compute the sqrt
                    out.println("Please enter a number:");
                    n = in.nextDouble();
                    out.println(sqrt(n, error));
                }
            }
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
