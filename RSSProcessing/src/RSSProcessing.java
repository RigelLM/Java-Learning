import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * This program inputs an XML RSS (version 2.0) feed from a given URL and
 * outputs various elements of the feed to the console.
 *
 * @author Put your name here
 *
 */
public final class RSSProcessing {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSProcessing() {
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of the {@code XMLTree} matching the
     *         given tag or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of the {@code XMLTree} matching the
     *   given tag or -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";
        int index = -1;
        for (int i = 0; i < xml.numberOfChildren(); i++) {
            if (xml.child(i).label().equals(tag)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Processes one news item and outputs the title, or the description if the
     * title is not present, and the link (if available) with appropriate
     * labels.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures out.content = #out.content * [the title (or description) and
     *          link]
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        String news = "No title available";
        if (item.child(getChildElement(item, "title"))
                .numberOfChildren() != 0) {
            news = item.child(getChildElement(item, "title")).child(0).label();
            out.println("Title: " + news);
        } else if (item.child(getChildElement(item, "descritption"))
                .numberOfChildren() != 0) {
            news = item.child(getChildElement(item, "description")).child(0)
                    .label();
            out.println("Description: " + news);
        }

        if (getChildElement(item, "link") != -1) {
            out.println("Link: " + item.child(getChildElement(item, "link"))
                    .child(0).label());
        }
        out.println("");
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        /*
         * Open I/O streams.
         */
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Input the source URL.
         */
        out.print("Enter the URL of an RSS 2.0 news feed: ");
        String url = in.nextLine();
        /*
         * Read XML input and initialize XMLTree. If input is not legal XML,
         * this statement will fail.
         */
        XMLTree rssFile = new XMLTree1(url);
        /*
         * Extract <channel> element.
         */
        XMLTree channel = rssFile.child(0);
        /*
         * TODO: #2 - output title, link, and description
         */

        //processItem(rssFile, out);

        /*
         * TODO: #4 - for each item, output title (or description, if title is
         * not available) and link (if available)
         */
        for (int i = 0; i < rssFile.child(0).numberOfChildren(); i++) {
            if (rssFile.child(0).child(i).label().equals("item")) {
                processItem(rssFile.child(0).child(i), out);
            }
        }

        /*
         * Close I/O streams.
         */
        in.close();
        out.close();
    }

}
