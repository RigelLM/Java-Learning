import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Lee Li
 *
 */
public final class RSSReader {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSReader() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html>");
        out.println("<head>");
        String title = " ";
        if (channel.child(getChildElement(channel, "title"))
                .numberOfChildren() != 0) {
            title = channel.child(getChildElement(channel, "title")).child(0)
                    .label();
        }
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body>");
        String link = "";
        link = channel.child(getChildElement(channel, "link")).child(0).label();
        out.println("<h1><a href=\"" + link + "\">" + title + "</a></h1>");
        String description = " ";
        if (channel.child(getChildElement(channel, "description"))
                .numberOfChildren() != 0) {
            description = channel.child(getChildElement(channel, "description"))
                    .child(0).label();
        }
        out.println("<p>" + description + "</p>");

        out.println("<table border=\"1\">");
        out.println("<tbody><tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");

    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
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
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<tr>");

        String date = "No date available";
        if (getChildElement(item, "pubDate") != -1) {
            date = item.child(getChildElement(item, "pubDate")).child(0)
                    .label();
        }
        out.println("<td>" + date + "</td>");

        out.print("<td>");
        String source = "No source available";
        if (getChildElement(item, "source") != -1) {
            if (item.child(getChildElement(item, "source"))
                    .numberOfChildren() != 0) {
                source = item.child(getChildElement(item, "source")).child(0)
                        .label();
            }
            out.print(
                    "<a href=\""
                            + item.child(getChildElement(item, "source"))
                                    .attributeValue("url")
                            + "\">" + source + "</a>");
        } else {
            out.print(source);
        }
        out.println("</td>");

        out.print("<td>");
        String news = "No title available";
        if (item.child(getChildElement(item, "title"))
                .numberOfChildren() != 0) {
            news = item.child(getChildElement(item, "title")).child(0).label();
        } else if (item.child(getChildElement(item, "descritption"))
                .numberOfChildren() != 0) {
            news = item.child(getChildElement(item, "description")).child(0)
                    .label();
        }

        if (getChildElement(item, "link") != -1) {
            out.print("<a href=\""
                    + item.child(getChildElement(item, "link")).child(0).label()
                    + "\">" + news + "</a>");
        } else {
            out.print(news);
        }
        out.println("</td>");

        out.println("</tr>");
    }

    /**
     * Read the input RSS feed and creates a well-presented HTML page base on it
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Please enter an URL");
        String url = in.nextLine();
        XMLTree rssFile = new XMLTree1(url);
        if (!(rssFile.label().equals("rss"))
                || !(rssFile.attributeValue("version").equals("2.0"))) {
            out.println("Please input and RSS version 2 file");
        } else {
            out.println("Please enter the name of output file:");
            out.close();
            String outputFile = in.nextLine();
            out = new SimpleWriter1L(outputFile);
            outputHeader(rssFile.child(0), out);

            for (int i = 0; i < rssFile.child(0).numberOfChildren(); i++) {
                if (rssFile.child(0).child(i).label().equals("item")) {
                    processItem(rssFile.child(0).child(i), out);
                }
            }

            outputFooter(out);
        }
        in.close();
        out.close();
    }

}