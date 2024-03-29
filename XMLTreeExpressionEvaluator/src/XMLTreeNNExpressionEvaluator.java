import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        NaturalNumber value = new NaturalNumber2(0);
        switch (exp.label()) {
            case "plus":
                value.add(evaluate(exp.child(0)));
                value.add(evaluate(exp.child(1)));
                break;
            case "minus":
                value.add(evaluate(exp.child(0)));
                value.subtract(evaluate(exp.child(1)));
                break;
            case "times":
                value.add(evaluate(exp.child(0)));
                value.multiply(evaluate(exp.child(1)));
                break;
            case "divide":
                value.add(evaluate(exp.child(0)));
                value.divide(evaluate(exp.child(1)));
                break;
            case "number":
                if (Integer.parseInt(exp.attributeValue("value")) < 1) {
                    components.utilities.Reporter
                            .fatalErrorToConsole("Number should be positive");
                } else {
                    value = new NaturalNumber2(exp.attributeValue("value"));
                    break;
                }
        }
        return value;
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

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}