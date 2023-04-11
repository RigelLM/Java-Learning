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

    private static void indexHeader(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Glossary</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Glossary</h2>");
        out.println("<hr />");
        out.println("<h3>Index</h3>");
        out.println("<ul>");
    }

    private static void indexFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    private static void generateList(String word, SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<li><a href=\"" + word + ".html\">" + word + "</a></li>");
    }

    private static void outputWord(String folder, String word,
            String definition) {
        assert word != null : "Violation of: word is not null";
        assert definition != null : "Violation of: definition is not null";

        SimpleWriter out = new SimpleWriter1L(folder + "/" + word + ".html");

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

        out.close();
    }

    private static void processWords(String resultFolderName, SimpleReader in,
            SimpleWriter out) {
        assert in != null : "Violation of : in is not null";
        assert in.isOpen() : "Violation of : in.is_open";

        indexHeader(out);

        while (!in.atEOS()) {
            String word = in.nextLine();
            String definition = "";

            String lines = in.nextLine();
            while (!lines.equals("")) {
                definition += (" " + lines);
                lines = in.nextLine();
            }
            outputWord(resultFolderName, word, definition);
            generateList(word, out);
        }

        indexFooter(out);
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

        out.println("Please enter the file that you want to process: ");
        String sourceFileName = in.nextLine();
        out.println(
                "Please enter the folder that you want to store the result: ");
        String resultFolderName = in.nextLine();
        in.close();
        out.close();

        in = new SimpleReader1L(sourceFileName);
        out = new SimpleWriter1L(resultFolderName + "/index.html");

        processWords(resultFolderName, in, out);

        in.close();
        out.close();
    }

}
