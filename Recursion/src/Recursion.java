import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Recursion {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Recursion() {
    }

    /**
     * Returns the number of digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to count
     * @return the number of digits of {@code n}
     * @ensures numberOfDigits = [number of digits of n]
     */
    private static int numberOfDigits(NaturalNumber n) {
        int count = 0;
        n.divideBy10();
        if (!n.isZero()) {
            // get the new value from the inner loop
            count = numberOfDigits(n);
        }
        // update count in every loop
        count++;
        return count;
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */

    private static int sumOfDigits(NaturalNumber n) {
        int sum = 0;
        // get each digit
        sum += n.divideBy10();
        if (!n.isZero()) {
            // add each digit to the sum
            sum += sumOfDigits(n);
        }
        return sum;
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @clears n
     * @ensures productOfDigits1 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits1(NaturalNumber n) {
        NaturalNumber product = new NaturalNumber2(0);
        // get each digit
        NaturalNumber digit = new NaturalNumber2(n.divideBy10());
        product.multiply(digit);
        if (!n.isZero()) {
            // add each digit to the sum
            product.multiply(productOfDigits1(n));
        }
        return product;
    }

    /**
     * Returns the product of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to multiply
     * @return the product of the digits of {@code n}
     * @ensures productOfDigits2 = [product of the digits of n]
     */
    private static NaturalNumber productOfDigits2(NaturalNumber n) {
        NaturalNumber product = new NaturalNumber2(0);
        // get each digit
        NaturalNumber digit = new NaturalNumber2(n.divideBy10());
        product.multiply(digit);
        if (!n.isZero()) {
            // add each digit to the sum
            product.multiply(productOfDigits1(n));
            n.multiplyBy10(digit.toInt());
        }
        return product;
    }

    /**
     * Reports the value of {@code n} as an {@code int}, when {@code n} is small
     * enough.
     *
     * @param n
     *            the given {@code NaturalNumber}
     * @return the value
     * @requires n <= Integer.MAX_VALUE
     * @ensures toInt = n
     */
    private static int toInt(NaturalNumber n) {
        NaturalNumber max = new NaturalNumber2(Integer.MAX_VALUE);
        if (n.compareTo(max) < 0) {
            return n.toInt();
        } else {
            return 0;
        }
    }

    /**
     * Returns the sum of the digits of {@code n}.
     *
     * @param n
     *            {@code NaturalNumber} whose digits to add
     * @return the sum of the digits of {@code n}
     * @ensures sumOfDigits = [sum of the digits of n]
     */
    private static NaturalNumber sumOfDigits(NaturalNumber n) {
        NaturalNumber sum = new NaturalNumber2(0);
        // get each digit
        NaturalNumber digit = new NaturalNumber2(n.divideBy10());
        sum.add(digit);
        if (!n.isZero()) {
            // add each digit to the sum
            sum.add(sumOfDigits(n));
        }
        return sum;
    }

    /**
     * Divides {@code n} by 2.
     *
     * @param n
     *            {@code NaturalNumber} to be divided
     * @updates n
     * @ensures 2 * n <= #n < 2 * (n + 1)
     */
    private static void divideBy2(NaturalNumber n) {
        int ones, tens;
        if (!n.isZero()) {
            // process the last two digits
            ones = n.divideBy10();
            tens = n.divideBy10();
            if (tens % 2 != 0) {
                ones += 10;
            }
            ones = ones / 2;
            // recover the penultimate digit
            n.multiplyBy10(tens);
            // recursion call
            divideBy2(n);
            // set the last digit
            n.multiplyBy10(ones);
        }
    }

    /**
     * Checks whether a {@code String} is a palindrome.
     *
     * @param s
     *            {@code String} to be checked
     * @return true if {@code s} is a palindrome, false otherwise
     * @ensures isPalindrome = (s = rev(s))
     */
    public static boolean isPalindrome(String str) {
        //test for end of recursion
        if (str.length() < 2) {
            return true;
        }

        //check first and last character for equality
        if (str.charAt(0) != str.charAt(str.length() - 1)) {
            return false;
        }

        //recursion call
        return isPalindrome(str.substring(1, str.length() - 1));
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
    }

}
