import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Program with implementation of {@code NaturalNumber} secondary operation
 * {@code root} implemented as static method.
 *
 * @author Lee Li
 *
 */
public final class NaturalNumberRoot {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private NaturalNumberRoot() {
    }

    /**
     * Updates {@code n} to the {@code r}-th root of its incoming value.
     *
     * @param n
     *            the number whose root to compute
     * @param r
     *            root
     * @updates n
     * @requires r >= 2
     * @ensures n ^ (r) <= #n < (n + 1) ^ (r)
     */
    public static void root(NaturalNumber n, int r) {
        assert n != null : "Violation of: n is  not null";
        assert r >= 2 : "Violation of: r >= 2";
        //the constant value of 2 used for division
        final NaturalNumber two = new NaturalNumber2(2);
        //tooHigh = n + 1;
        NaturalNumber tooHigh = new NaturalNumber2();
        tooHigh.copyFrom(n);
        tooHigh.increment();
        //lowEnough = 0;
        NaturalNumber lowEnough = new NaturalNumber2(0);
        //guess = (tooHigh + lowEnough) / 2;
        NaturalNumber guess1 = new NaturalNumber2();
        guess1.copyFrom(tooHigh);
        guess1.add(lowEnough);
        guess1.divide(two);
        //guess + 1;
        NaturalNumber guess2 = new NaturalNumber2();
        guess2.copyFrom(guess1);
        guess2.increment();
        //guess^r
        //(guess+1)^r
        NaturalNumber guess1Square = new NaturalNumber2();
        NaturalNumber guess2Square = new NaturalNumber2();
        guess1Square.copyFrom(guess1);
        guess2Square.copyFrom(guess2);
        guess1Square.power(r);
        guess2Square.power(r);
        //compare guess1Square with n and update tooHigh/lowEnough
        //until n falls into the interval of (guess1Square, guess2Square)
        while (guess1Square.compareTo(n) > 0
                || guess2Square.compareTo(n) <= 0) {
            if (guess1Square.compareTo(n) >= 0) {
                tooHigh.copyFrom(guess1);
            } else {
                lowEnough.copyFrom(guess1);
            }
            //guess = (tooHigh + lowEnough) / 2;
            guess1.copyFrom(tooHigh);
            guess1.add(lowEnough);
            guess1.divide(two);
            //guess + 1;
            guess2.copyFrom(guess1);
            guess2.increment();
            //guess^r
            //(guess+1)^r
            guess1Square.copyFrom(guess1);
            guess2Square.copyFrom(guess2);
            guess1Square.power(r);
            guess2Square.power(r);
        }
        //save the answer
        n.copyFrom(guess1);
    }

    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();

        final String[] numbers = { "0", "1", "13", "1024", "189943527", "0",
                "1", "13", "4096", "189943527", "0", "1", "13", "1024",
                "189943527", "82", "82", "82", "82", "82", "9", "27", "81",
                "243", "143489073", "2147483647", "2147483648",
                "9223372036854775807", "9223372036854775808",
                "618970019642690137449562111",
                "162259276829213363391578010288127",
                "170141183460469231731687303715884105727" };
        final int[] roots = { 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 15, 15, 15, 15, 15,
                2, 3, 4, 5, 15, 2, 3, 4, 5, 15, 2, 2, 3, 3, 4, 5, 6 };
        final String[] results = { "0", "1", "3", "32", "13782", "0", "1", "2",
                "16", "574", "0", "1", "1", "1", "3", "9", "4", "3", "2", "1",
                "3", "3", "3", "3", "3", "46340", "46340", "2097151", "2097152",
                "4987896", "2767208", "2353973" };

        for (int i = 0; i < numbers.length; i++) {
            NaturalNumber n = new NaturalNumber2(numbers[i]);
            NaturalNumber r = new NaturalNumber2(results[i]);
            root(n, roots[i]);
            if (n.equals(r)) {
                out.println("Test " + (i + 1) + " passed: root(" + numbers[i]
                        + ", " + roots[i] + ") = " + results[i]);
            } else {
                out.println("*** Test " + (i + 1) + " failed: root("
                        + numbers[i] + ", " + roots[i] + ") expected <"
                        + results[i] + "> but was <" + n + ">");
            }
        }

        out.close();
    }

}
