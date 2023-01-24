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
public final class Homework {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework() {
    }

    /**
     * Returns the {@code r}-th root of {@code n}.
     *
     * @param n
     *            the number to which we want to apply the root
     * @param r
     *            the root
     * @return the root of the number
     * @requires n >= 0 and r > 0
     * @ensures root ^ (r) <= n < (root + 1) ^ (r)
     */
    private static String reverse(String s) {
        String result;
        if (s.length() == 0) {
            return s;
        } else {
            String sub = s.substring(1);
            String reverseSub = reverse(sub);
            result = reverseSub + s.charAt(0);
        }
        return result;
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
         * Put your main program code here; it may call myMethod as shown
         */
        String s = "abcde";
        out.println(reverse(s));
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
