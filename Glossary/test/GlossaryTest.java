import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;

public class GlossaryTest {

    @Test
    public void getWordsDefinitionsTest_Numbers() {

        String n = "test/Numbers/Numbers.txt";
        String nExpected = "test/Numbers/Numbers.txt";

        Queue<String> m = new Queue1L<>();
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("1");
        mExpected.enqueue("2");
        mExpected.enqueue("3");

        Map<String, String> q = new Map1L<>();
        Map<String, String> qExpected = new Map1L<>();
        qExpected.add("3", "3");
        qExpected.add("1", "1");
        qExpected.add("2", "2");

        Glossary.getWordsDefinitions(n, m, q);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(qExpected, q);
    }

    @Test
    public void getWordsDefinitionsTest_Strings_LowerCase() {

        String n = "test/Strings_LowerCase/Strings_LowerCase.txt";
        String nExpected = "test/Strings_LowerCase/Strings_LowerCase.txt";

        Queue<String> m = new Queue1L<>();
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("a");
        mExpected.enqueue("b");
        mExpected.enqueue("c");

        Map<String, String> q = new Map1L<>();
        Map<String, String> qExpected = new Map1L<>();
        qExpected.add("c", "c");
        qExpected.add("a", "a");
        qExpected.add("b", "b");

        Glossary.getWordsDefinitions(n, m, q);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(qExpected, q);
    }

    @Test
    public void getWordsDefinitionsTest_Strings_UpperCase() {

        String n = "test/Strings_UpperCase/Strings_UpperCase.txt";
        String nExpected = "test/Strings_UpperCase/Strings_UpperCase.txt";

        Queue<String> m = new Queue1L<>();
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("A");
        mExpected.enqueue("B");
        mExpected.enqueue("C");

        Map<String, String> q = new Map1L<>();
        Map<String, String> qExpected = new Map1L<>();
        qExpected.add("C", "c");
        qExpected.add("A", "a");
        qExpected.add("B", "b");

        Glossary.getWordsDefinitions(n, m, q);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(qExpected, q);
    }

    @Test
    public void getWordsDefinitionsTest_Strings_Mixed() {

        String n = "test/Strings_Mixed/Strings_Mixed.txt";
        String nExpected = "test/Strings_Mixed/Strings_Mixed.txt";

        Queue<String> m = new Queue1L<>();
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("A");
        mExpected.enqueue("b");
        mExpected.enqueue("c");

        Map<String, String> q = new Map1L<>();
        Map<String, String> qExpected = new Map1L<>();
        qExpected.add("c", "c");
        qExpected.add("A", "a");
        qExpected.add("b", "b");

        Glossary.getWordsDefinitions(n, m, q);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(qExpected, q);
    }

    @Test
    public void getWordsDefinitionsTest_Strings_Numbers() {

        String n = "test/Strings_Numbers/Strings_Numbers.txt";
        String nExpected = "test/Strings_Numbers/Strings_Numbers.txt";

        Queue<String> m = new Queue1L<>();
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("3");
        mExpected.enqueue("5");
        mExpected.enqueue("a");
        mExpected.enqueue("D");
        mExpected.enqueue("f");

        Map<String, String> q = new Map1L<>();
        Map<String, String> qExpected = new Map1L<>();
        qExpected.add("3", "3");
        qExpected.add("a", "a");
        qExpected.add("5", "5");
        qExpected.add("f", "f");
        qExpected.add("D", "d");

        Glossary.getWordsDefinitions(n, m, q);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(qExpected, q);
    }

    @Test
    public void generateIndexFileTest_Numbers() {

        String n = "test/Numbers";
        String nExpected = "test/Numbers";

        Queue<String> m = new Queue1L<>();
        m.enqueue("1");
        m.enqueue("2");
        m.enqueue("3");
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("1");
        mExpected.enqueue("2");
        mExpected.enqueue("3");

        Glossary.generateIndexFile(n, m);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void generateWordFilesTest_Numbers() {

        String n = "test/Numbers";
        String nExpected = "test/Numbers";

        Queue<String> m = new Queue1L<>();
        m.enqueue("1");
        m.enqueue("2");
        m.enqueue("3");
        Queue<String> mExpected = new Queue1L<>();
        mExpected.enqueue("1");
        mExpected.enqueue("2");
        mExpected.enqueue("3");

        Map<String, String> q = new Map1L<>();
        q.add("3", "3");
        q.add("1", "1");
        q.add("2", "2");
        Map<String, String> qExpected = new Map1L<>();
        qExpected.add("3", "3");
        qExpected.add("1", "1");
        qExpected.add("2", "2");

        Glossary.generateWordFiles(n, m, q);

        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
        assertEquals(qExpected, q);
    }
}
