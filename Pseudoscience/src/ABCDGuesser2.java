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
public final class ABCDGuesser2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser2() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        out.println("Please enter a positive number: ");
        String input = in.nextLine();
        while (!FormatChecker.canParseDouble(input)
                || Double.parseDouble(input) < 0) {
            out.println("Please enter a number: ");
            input = in.nextLine();
        }
        out.println(input);
        return Double.parseDouble(input);
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out) {
        out.println("Please enter a positive number (!= 1.0): ");
        String input = in.nextLine();
        while (!FormatChecker.canParseDouble(input)
                || Double.parseDouble(input) == 1.0
                || Double.parseDouble(input) < 0) {
            out.println("Please enter a positive number(!= 1.0): ");
            input = in.nextLine();
        }
        out.println(input);
        return Double.parseDouble(input);
    }

    private static void ForLoop(SimpleReader in, SimpleWriter out) {
        double target = getPositiveDouble(in, out);
        double w = getPositiveDoubleNotOne(in, out);
        double x = getPositiveDoubleNotOne(in, out);
        double y = getPositiveDoubleNotOne(in, out);
        double z = getPositiveDoubleNotOne(in, out);

        double[] power = { -5.0, -4.0, -3.0, -2.0, -1.0, -1.0 / 2.0, -1.0 / 3.0,
                -1.0 / 4.0, 0.0, 1.0 / 4.0, 1.0 / 3.0, 1.0 / 2.0, 1.0, 2.0, 3.0,
                4.0, 5.0 };

        int index1 = 0, index2 = 0, index3 = 0, index4 = 0;

        double bestResult = 0.0;
        double relativeError = 0.0;
        double bestRelativeError = 1.0;
        double guess = 0.0;
        double[] powers = { power[index1], power[index2], power[index3],
                power[index4] };

        for (index1 = 0; index1 < 17; index1++) {
            double firstTerm = Math.pow(w, power[index1]);
            index2 = 0;
            for (index2 = 0; index2 < 17; index2++) {
                double secondTerm = Math.pow(x, power[index2]);
                for (index3 = 0; index3 < 17; index3++) {
                    double thirdTerm = Math.pow(y, power[index3]);
                    index4 = 0;
                    for (index4 = 0; index4 < 17; index4++) {
                        double forthTerm = Math.pow(z, power[index4]);
                        guess = firstTerm * secondTerm * thirdTerm * forthTerm;
                        relativeError = Math
                                .abs((guess - target) / target * 100.0);
                        if (bestRelativeError > relativeError) {
                            bestRelativeError = relativeError;
                            bestResult = guess;
                            powers[0] = power[index1];
                            powers[1] = power[index2];
                            powers[2] = power[index3];
                            powers[3] = power[index4];
                        }
                    }

                }

            }
        }
        out.println("Target = " + target);
        out.println("Guess = " + bestResult);
        out.println("Relative Error = " + bestRelativeError + "%");
        out.println("powers = " + powers[0] + " " + powers[1] + " " + powers[2]
                + " " + powers[3]);
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

        ForLoop(in, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
