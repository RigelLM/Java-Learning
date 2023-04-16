import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
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
public final class Glossary {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Glossary() {
    }

    /**
     * Compare {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.toLowerCase().compareTo(o2.toLowerCase());
        }
    }

    /**
     * Process the source file and get all words and definitions.
     *
     * @param source
     *            the name of the source file
     * @param words
     *            the queue that stores all the terms
     * @param definitions
     *            the map that stores all the definitions to the terms
     * @requires source, words, and definitions are not null
     * @ensures <pre>
     * words = [all terms in lexicographic order]
     * definitions = [all {term, definition} pairs]
     * </pre>
     */
    public static void getWordsDefinitions(String source, Queue<String> words,
            Map<String, String> definitions) {
        assert source != null : "Violation of : source is not null";
        assert words != null : "Violation of : words is not null";
        assert definitions != null : "Violation of : definitions is not null";

        SimpleReader in = new SimpleReader1L(source);
        Queue<String> tempQ = words.newInstance();
        Map<String, String> tempM = definitions.newInstance();

        while (!in.atEOS()) {
            // Get the word between definitions and empty lines
            String word = in.nextLine();

            // Get the definition between words and empty lines
            String definition = "";
            // Get the first line of definition
            String lines = in.nextLine();
            definition += lines;
            // Get the rest of lines of definition if line is not empty
            while (!lines.equals("") && !in.atEOS()) {
                lines = in.nextLine();
                if (!lines.equals("")) {
                    definition += (" " + lines);
                }
            }

            // Put all words into the queue
            tempQ.enqueue(word);
            // Put all definitions into the map
            tempM.add(word, definition);
        }

        // Sort all the words
        Comparator<String> cs = new StringLT();
        tempQ.sort(cs);

        // Replace the words and definitions
        words.transferFrom(tempQ);
        definitions.transferFrom(tempM);

        in.close();
    }

    /**
     * Generate the index file.
     *
     * @param folder
     *            the name of the folder that stores the result
     * @param words
     *            the queue that stores all the terms in lexicographic order
     * @requires folder and words are not null
     * @ensures <pre>
     * index.html file is generated in the folder
     * </pre>
     */
    public static void generateIndexFile(String folder, Queue<String> words) {
        assert words != null : "Violation of : words is not null";
        assert folder != null : "Violation of: folder is not null";

        SimpleWriter out = new SimpleWriter1L(folder + "/index.html");

        // Generate all the start tags in index.html
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr />");
        out.println("<h3>Index</h3>");

        // Generate the list
        out.println("<ul>");
        Queue<String> tempQ = words.newInstance();
        while (words.length() != 0) {
            // Get words and definitions
            String word = words.dequeue();
            out.println(
                    "<li><a href=\"" + word + ".html\">" + word + "</a></li>");
            tempQ.enqueue(word);
        }
        out.println("</ul>");

        // Generate all the end tags in index.html
        out.println("</body>");
        out.println("</html>");

        // Restore words
        words.transferFrom(tempQ);

        out.close();
    }

    /**
     * Generate word.html files.
     *
     * @param folder
     *            the name of the folder that stores the result
     * @param words
     *            the queue that stores all the terms in lexicographic order
     * @param definitions
     *            the map that stores all definitions to the terms
     * @requires folder, words, and definitions are not null
     * @ensures <pre>
     * word.html files are generated in the folder
     * </pre>
     */
    public static void generateWordFiles(String folder, Queue<String> words,
            Map<String, String> definitions) {
        assert folder != null : "Violation of : folder is not null";
        assert words != null : "Violation of : words is not null";
        assert definitions != null : "Violation of : definitions is not null";

        Queue<String> tempQ = words.newInstance();

        while (words.length() != 0) {
            // Get words and definitions
            String word = words.dequeue();
            String definition = definitions.value(word);

            // Redirect the output stream to the required folder
            SimpleWriter out = new SimpleWriter1L(
                    folder + "/" + word + ".html");

            // Output the content in the file
            out.println("<html>");
            out.println("<head>");
            out.println("<title>" + word + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2><b><i><font color=\"red\">" + word
                    + "</font></i></b></h2>");
            out.println("<blockquote>" + definition + "</blockquote>");
            out.println("<hr />");
            out.println("<p>Return to <a href=\"index.html\">index</a>.</p>");
            out.println("</body>");
            out.println("</html>");

            // Close the output stream
            out.close();

            tempQ.enqueue(word);
        }

        // Restore words
        words.transferFrom(tempQ);
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

        // Ask user the input file's name
        out.println("Please enter the file that you want to process: ");
        String sourceFile = in.nextLine();
        // Ask user the place to store the result
        out.println(
                "Please enter the folder that you want to store the result: ");
        String resultFolder = in.nextLine();

        // Close the input and output streams
        in.close();
        out.close();

        Queue<String> words = new Queue1L<>();
        Map<String, String> definitions = new Map1L<>();

        // Process the source file
        getWordsDefinitions(sourceFile, words, definitions);

        generateIndexFile(resultFolder, words);

        generateWordFiles(resultFolder, words, definitions);

    }

}
