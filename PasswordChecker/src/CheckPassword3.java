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
public final class CheckPassword3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CheckPassword3() {
    }

    /**
     * Checks whether the given String is longer than 8.
     *
     * @param s
     * @return the result of checking
     */
    private static boolean containEnoughLetters(String s) {
        return (s.length() > 7);
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
     * Checks whether the given String has lowercase letter.
     *
     * @param s
     * @return the result of checking
     */
    private static boolean containLowerCaseLetter(String s) {
        return s.matches("(.*)[a-z](.*)");
    }

    /**
     * Checks whether the given String has special letter.
     *
     * @param s
     * @return the result of checking
     */
    private static boolean containSpecialLetter(String s) {
        return s.matches("(.*)[!@#$%^&*()\\-+=\\{\\}\\[\\]\\:;,.\\?](.*)");
    }

    /**
     * Checks whether the given String has numbers.
     *
     * @param s
     * @return the result of checking
     */
    private static boolean containNumbers(String s) {
        return s.matches("(.*)[0-9](.*)");
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
        int condition = 0;
        if (containEnoughLetters(s)) {
            if (containUpperCaseLetter(s)) {
                condition++;
            }
            if (containLowerCaseLetter(s)) {
                condition++;
            }
            if (containSpecialLetter(s)) {
                condition++;
            }
            if (containNumbers(s)) {
                condition++;
            }
            if (condition < 3) {
                out.println("The password does not satisfy the criteria!");
            } else {
                out.println("The password satisfies the criteria!");
            }
        } else {
            out.println("The password has to be longer than 8!");
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
