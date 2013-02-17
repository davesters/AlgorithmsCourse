import java.util.Iterator;

public class Subset {
    
    public static void main(String[] args) {
        int amount = 0;
        
        if (args.length == 0) {
            StdOut.println("Must specify the number of string to print back.");
            throw new IllegalArgumentException();
        }
        try {
            amount = Integer.parseInt(args[0]);
        } catch (NumberFormatException er) {
            StdOut.println("Must provide a number.");
            throw new IllegalArgumentException();
        }
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty())
        {
            queue.enqueue(StdIn.readString());
        }
        
        printResults(queue, amount);
    }
    
    private static void printResults(RandomizedQueue<String> queue, int amount) {
        Iterator<String> iterator = queue.iterator();
        
        int counter = 0;
        while (iterator.hasNext() && counter < amount) {
            StdOut.println(iterator.next());
            counter++;
        }
    }
}