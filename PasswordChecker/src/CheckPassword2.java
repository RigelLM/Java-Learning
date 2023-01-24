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
public final class CheckPassword2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword2() {
    }

    /**
     * Checks whether the given String is longer than 8.
     *
     * @param s
     * @return the result of checking
     */
    private static boolean containEnoughLetters(String s) {
        if (s.length() > 7) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether the given String has uppercase letter.
     *
     * @param s
     * @return the result of checking
     */
    private static boolean containUpperCaseLetter(String s) {
        return s.matches("(.*)[A-Z](.*)");
    }

    /**
     * Checks whether the given String satisfies the OSU criteria for a valid
     * password. Prints an appropriate message to the given output stream.
     *
     * @param s
     *            the String to check
     * @param out
     *            the output stream
     */
    private static void checkPassword(String s, SimpleWriter out) {
        if (containEnoughLetters(s)) {
            if (containUpperCaseLetter(s)) {
                out.println("The password is recorded.");
            } else {
                out.println("The password must contain uppercase letter.");
            }
        } else {
            out.println("The password must be longer than 8 letters!");
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
         * Put your main program code here; it may call myMethod as shown
         */
        out.println(
                "Please enter your password(it has to be longer than 8 characters):");
        String password = in.nextLine();
        checkPassword(password, out);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
