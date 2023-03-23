import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 *
 */

/**
 * @author Serein
 *
 */
public class NNToStringWithCommasTest {

    @Test
    public void test1() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber r = new NaturalNumber2(1);
        String rExpected = "1";
        String rOutput = redirectToMethodUnderTest(r);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(rExpected, rOutput);
    }

    @Test
    public void test2() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n = new NaturalNumber2(0);
        String nExpected = "0";
        String nOutput = redirectToMethodUnderTest(n);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(nExpected, nOutput);
    }

    @Test
    public void test3() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber t = new NaturalNumber2(1234);
        String tExpected = "1,234";
        String tOutput = redirectToMethodUnderTest(t);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(tExpected, tOutput);
    }

    @Test
    public void test4() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber rt = new NaturalNumber2(1234567);
        String rtExpected = "1,234,567";
        String rtOutput = redirectToMethodUnderTest(rt);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(rtExpected, rtOutput);
    }

    @Test
    public void test5() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber rt = new NaturalNumber2(10000);
        String rtExpected = "10,000";
        String rtOutput = redirectToMethodUnderTest(rt);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(rtExpected, rtOutput);
    }

    @Test
    public void test6() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber rt = new NaturalNumber2("999999999999999");
        String rtExpected = "999,999,999,999,999";
        String rtOutput = redirectToMethodUnderTest(rt);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(rtExpected, rtOutput);
    }

    @Test
    public void test7() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber rt = new NaturalNumber2("");
        String rtExpected = "0";
        String rtOutput = redirectToMethodUnderTest(rt);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(rtExpected, rtOutput);
    }

    /**
     * Calls the method under test.
     *
     * @param n
     *            the number to pass to the method under test
     * @return the {@code String} returned by the method under test
     * @ensures <pre>
     * redirectToMethodUnderTest = [String returned by the method under test]
     * </pre>
     */
    private static String redirectToMethodUnderTest(NaturalNumber n) {
        return NNtoStringWithCommas5.toStringWithCommas(n);
    }

}
