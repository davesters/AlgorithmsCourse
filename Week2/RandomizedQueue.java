import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int count = 0;
    private Item[] queue;

    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        queue[0] = null;
    }
    
    public boolean isEmpty() {
        return (count == 0);
    }
    
    public int size() {
        return count;
    }
    
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException();
        
        if (count == queue.length) {
            resize(queue.length * 2);
        }
        
        queue[count++] = item;
    }
    
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        
        for (int i = 0; i < count; i++) {
            copy[i] = queue[i];
        }
        
        queue = copy;
    }
    
    public Item dequeue() {
        if (count == 0) throw new NoSuchElementException();
        
        int rnd = 0;
        
        if (count > 1) {
            rnd = StdRandom.uniform(0, --count);
        } else {
            rnd = --count;
        }
        
        Item item = queue[rnd];
        if (count > 0 && rnd != count) queue[rnd] = queue[count];
        queue[count] = null;
        
        if (count > 0 && count == (queue.length / 4)) {
            resize(queue.length / 2);
        }
        
        return item;
    }
    
    public Item sample() {
        if (count == 0) throw new NoSuchElementException();
        
        if (count > 1) {
            return queue[StdRandom.uniform(0, count)];
        } else {
            return queue[0];
        }
    }
    
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        
        private Item[] rndQueue;
        private int iteratorCount = 0;
        private int rndCount = 0;
        
        public RandomizedQueueIterator() {
            iteratorCount = size();
            rndCount = size();
            rndQueue = (Item[]) new Object[iteratorCount];
            
            for (int x = 0; x < iteratorCount; x++) {
                rndQueue[x] = queue[x];
            }
        }
        
        public boolean hasNext() {
            if (iteratorCount != size()) {
                throw new ConcurrentModificationException();
            }

            return (rndCount != 0);
        }
        
        public Item next() {
            if (iteratorCount != size()) {
                throw new ConcurrentModificationException();
            }
            
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            int rnd = 0;
            if (rndCount > 1) {
                rnd = StdRandom.uniform(0, --rndCount);
            } else {
                rnd = --rndCount;
            }
            
            Item item = rndQueue[rnd];
            if (rndCount > 0 && rnd != rndCount) rndQueue[rnd] = rndQueue[rndCount];
            rndQueue[rndCount] = null;
            
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    public static void main(String[] args) {
        Integer[] arr = new Integer[5];
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 0;
        
        for (int x = 0; x < 30000; x++) {
            RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
            queue.enqueue(0);
            queue.enqueue(1);
            queue.enqueue(2);
            
            int rnd = StdRandom.uniform(0, 3);
            
            for (int y = 0; y < 3; y++) {
                Integer deq = queue.dequeue();
                if (deq == rnd) {
                    arr[y]++;
                    break;
                }
            }
        }
        
        for (int z = 0; z < 3; z++) {
            StdOut.println(z + ": " + arr[z]);
        }
    }
}