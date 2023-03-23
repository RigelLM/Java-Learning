import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and FindBugs warnings).
 *
 * @author Lee
 */
public final class Swap {

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber2();
        temp.copyFrom(n1);
        n1.copyFrom(n2);
        n2.copyFrom(temp);
    }

    /**
     * Swaps the two given {@code NaturalNumber}s.
     *
     * @param n1
     *            the first {@code NaturalNumber}
     * @param n2
     *            the second {@code NaturalNumber}
     * @updates n1
     * @updates n2
     * @ensures n1 = #n2 and n2 = #n1
     */
    private static void swapNN2(NaturalNumber n1, NaturalNumber n2) {
        NaturalNumber temp = new NaturalNumber2();
        temp.transferFrom(n1);
        n1.transferFrom(n2);
        n2.transferFrom(temp);
    }

    /**
     * Squares a given {@code NaturalNumber}.
     *
     * @param n
     *            the number to square
     * @updates n
     * @ensures n = #n * #n
     */
    private static void square(NaturalNumber n) {
        n.power(2);
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        NaturalNumber a = new NaturalNumber2(2);
        NaturalNumber b = new NaturalNumber2(10);

        SimpleWriter out = new SimpleWriter1L();

        swapNN(a, b);
        out.println(a);
        out.println(b);

        swapNN2(a, b);
        out.println(a);
        out.println(b);

        square(a);
        out.println(a);

        out.close();
    }

}
