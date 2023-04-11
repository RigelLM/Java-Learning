import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    @Test
    public void testCombination_NoSubString() {
        String n = "12345";
        String nExpected = "12345";
        String m = "45678";
        String mExpected = "45678";
        int p = 2;
        int pExpected = 2;
        String q = StringReassembly.combination(n, m, p);
        String qExpected = "12345678";
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(pExpected, p);
        assertEquals(qExpected, q);
    }

    @Test
    public void testCombination_NoOverlap() {
        String n = "12345";
        String nExpected = "12345";
        String m = "678";
        String mExpected = "678";
        int p = 0;
        int pExpected = 0;
        String q = StringReassembly.combination(n, m, p);
        String qExpected = "12345678";
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(pExpected, p);
        assertEquals(qExpected, q);
    }

    @Test
    public void testCombination_FirstSubstring() {
        String n = "123";
        String nExpected = "123";
        String m = "12345";
        String mExpected = "12345";
        int p = 3;
        int pExpected = 3;
        String q = StringReassembly.combination(n, m, p);
        String qExpected = "12345";
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(pExpected, p);
        assertEquals(qExpected, q);
    }

    @Test
    public void testCombination_SecondSubstring() {
        String n = "12345";
        String nExpected = "12345";
        String m = "345";
        String mExpected = "345";
        int p = 3;
        int pExpected = 3;
        String q = StringReassembly.combination(n, m, p);
        String qExpected = "12345";
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(pExpected, p);
        assertEquals(qExpected, q);
    }

    @Test
    public void testCombination_Same() {
        String n = "12345";
        String nExpected = "12345";
        String m = "12345";
        String mExpected = "12345";
        int p = 5;
        int pExpected = 5;
        String q = StringReassembly.combination(n, m, p);
        String qExpected = "12345";
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(pExpected, p);
        assertEquals(qExpected, q);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_NotSubstring() {
        Set<String> n = new Set2<>();
        n.add("123");
        n.add("456");
        n.add("789");

        Set<String> nExpected = new Set2<>();
        nExpected.add("123");
        nExpected.add("456");
        nExpected.add("789");
        nExpected.add("abc");

        String m = "abc";
        String mExpected = "abc";

        StringReassembly.addToSetAvoidingSubstrings(n, m);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_ContainsSubstring() {
        Set<String> n = new Set2<>();
        n.add("123");
        n.add("456");
        n.add("789");

        Set<String> nExpected = new Set2<>();
        nExpected.add("1234");
        nExpected.add("456");
        nExpected.add("789");

        String m = "1234";
        String mExpected = "1234";

        StringReassembly.addToSetAvoidingSubstrings(n, m);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_IsSubstring() {
        Set<String> n = new Set2<>();
        n.add("123");
        n.add("456");
        n.add("789");

        Set<String> nExpected = new Set2<>();
        nExpected.add("123");
        nExpected.add("456");
        nExpected.add("789");

        String m = "12";
        String mExpected = "12";

        StringReassembly.addToSetAvoidingSubstrings(n, m);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_ContainsMultiSubstrings() {
        Set<String> n = new Set2<>();
        n.add("123");
        n.add("456");
        n.add("789");

        Set<String> nExpected = new Set2<>();
        nExpected.add("123456789");

        String m = "123456789";
        String mExpected = "123456789";

        StringReassembly.addToSetAvoidingSubstrings(n, m);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testLinesFromInput_OneLine() {
        SimpleReader input = new SimpleReader1L("test/TestFile1Line.txt");

        Set<String> n = StringReassembly.linesFromInput(input);

        Set<String> nExpected = n.newInstance();
        nExpected.add("12345");

        assertEquals(nExpected, n);
        input.close();
    }

    @Test
    public void testLinesFromInput_MultiLines_NoSub() {
        SimpleReader input = new SimpleReader1L("test/TestFileMultiLines.txt");

        Set<String> n = StringReassembly.linesFromInput(input);

        Set<String> nExpected = n.newInstance();
        nExpected.add("123");
        nExpected.add("456");
        nExpected.add("789");

        assertEquals(nExpected, n);
        input.close();
    }

    @Test
    public void testLinesFromInput_MultiLines_Sub() {
        SimpleReader input = new SimpleReader1L(
                "test/TestFileMultiLinesSub.txt");

        Set<String> n = StringReassembly.linesFromInput(input);

        Set<String> nExpected = n.newInstance();
        nExpected.add("123456");
        nExpected.add("789");

        assertEquals(nExpected, n);
        input.close();
    }

    @Test
    public void testPrintWithLineSeparators_NoSymbol() {
        SimpleWriter output = new SimpleWriter1L();

        output.println();
        output.println(
                "------ testPrintWithLineSeparators_NoSymbol Result ------");

        String n = "123455678";

        String nExpected = "123455678";

        StringReassembly.printWithLineSeparators(n, output);

        output.println();
        output.println(
                "------ testPrintWithLineSeparators_NoSymbol Result ------");

        assertEquals(nExpected, n);

        output.close();
    }

    @Test
    public void testPrintWithLineSeparators_OneSymbol() {
        SimpleWriter output = new SimpleWriter1L();

        output.println();
        output.println(
                "------ testPrintWithLineSeparators_OneSymbol Result ------");

        String n = "12345~5678";

        String nExpected = "12345~5678";

        StringReassembly.printWithLineSeparators(n, output);

        output.println();
        output.println(
                "------ testPrintWithLineSeparators_OneSymbol Result ------");

        assertEquals(nExpected, n);

        output.close();
    }

    @Test
    public void testPrintWithLineSeparators_MultiSymbols() {
        SimpleWriter output = new SimpleWriter1L();

        output.println();
        output.println(
                "------ testPrintWithLineSeparators_MultiSymbols Result ------");

        String n = "12~345~567~8";

        String nExpected = "12~345~567~8";

        StringReassembly.printWithLineSeparators(n, output);

        output.println();
        output.println(
                "------ testPrintWithLineSeparators_MultiSymbols Result ------");

        assertEquals(nExpected, n);

        output.close();
    }

}
