import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Put your name here
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_9999999999999_21() {
        NaturalNumber n = new NaturalNumber2("9999999999999");
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_9999999999999() {
        NaturalNumber n = new NaturalNumber2("9999999999999");
        NaturalNumber nExpected = new NaturalNumber2("9999999999999");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_1_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        NaturalNumber p = new NaturalNumber2(1);
        NaturalNumber pExpected = new NaturalNumber2(1);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_0_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void testIsWitnessToCompositeness_2_4() {
        NaturalNumber w = new NaturalNumber2(2);
        NaturalNumber wExpected = new NaturalNumber2(2);
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean m = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean mExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsWitnessToCompositeness_100_10000() {
        NaturalNumber w = new NaturalNumber2(100);
        NaturalNumber wExpected = new NaturalNumber2(100);
        NaturalNumber n = new NaturalNumber2(10000);
        NaturalNumber nExpected = new NaturalNumber2(10000);
        boolean m = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean mExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsWitnessToCompositeness_3_17() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        boolean m = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean mExpected = false;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsWitnessToCompositeness_3_9999999999999() {
        NaturalNumber w = new NaturalNumber2(3);
        NaturalNumber wExpected = new NaturalNumber2(3);
        NaturalNumber n = new NaturalNumber2("9999999999999");
        NaturalNumber nExpected = new NaturalNumber2("9999999999999");
        boolean m = CryptoUtilities.isWitnessToCompositeness(w, n);
        boolean mExpected = true;
        assertEquals(wExpected, w);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean m = CryptoUtilities.isPrime2(n);
        boolean mExpected = true;
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsPrime2_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        boolean m = CryptoUtilities.isPrime2(n);
        boolean mExpected = true;
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsPrime2_4() {
        NaturalNumber n = new NaturalNumber2(4);
        NaturalNumber nExpected = new NaturalNumber2(4);
        boolean m = CryptoUtilities.isPrime2(n);
        boolean mExpected = false;
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsPrime2_5() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        boolean m = CryptoUtilities.isPrime2(n);
        boolean mExpected = true;
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsPrime2_15() {
        NaturalNumber n = new NaturalNumber2(15);
        NaturalNumber nExpected = new NaturalNumber2(15);
        boolean m = CryptoUtilities.isPrime2(n);
        boolean mExpected = false;
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testIsPrime2_9999999999999() {
        NaturalNumber n = new NaturalNumber2("9999999999999");
        NaturalNumber nExpected = new NaturalNumber2("9999999999999");
        boolean m = CryptoUtilities.isPrime2(n);
        boolean mExpected = false;
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_3() {
        NaturalNumber n = new NaturalNumber2(3);
        NaturalNumber nExpected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_98764321234() {
        NaturalNumber n = new NaturalNumber2("98764321234");
        NaturalNumber nExpected = new NaturalNumber2("98764321261");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }
}