import components.queue.Queue;
import components.queue.Queue2;
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
public final class Homework {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Homework() {
    }

    public void flip() {
        Queue<T> q = new Queue2<>();
        
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        Queue<Integer> q = new Queue2<Integer>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        flip(q);
        while (q.length() > 0) {
            out.print(q.dequeue());
        }
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
