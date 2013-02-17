import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class Deque<Item> implements Iterable<Item> {
    
    private Node first = null;
    private Node last = null;
    private int count = 0;
    
    public Deque() {
        
    }
    
    public boolean isEmpty() {
        return (count == 0);
    }
    
    public int size() {
        return count;
    }
    
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException();
        
        Node newNode = new Node();
        
        newNode.item = item;
        
        if (this.size() > 0) {
            Node oldFirst = first;
            oldFirst.previous = newNode;
            newNode.next = oldFirst;
            first = newNode;
            first.previous = null;
            count++;
            
            if (this.size() == 2) {
                last = oldFirst;
                last.next = null;
            }
        } else {
            newNode.next = null;
            newNode.previous = null;
            first = newNode;
            last = newNode;
            count++;
        }
    }
    
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException();

        Node newNode = new Node();
        
        newNode.item = item;
        
        if (this.size() > 0) {
            Node oldLast = last;
            last = newNode;
            last.previous = oldLast;
            oldLast.next = last;
            count++;
        } else {
            newNode.next = null;
            newNode.previous = null;
            first = newNode;
            last = newNode;
            count++;
        }
    }
    
    public Item removeFirst() {
        if (count == 0) throw new NoSuchElementException();
        
        Item item = first.item;
        
        if (this.size() == 2) {
            first = last;
            last.previous = null;
            first.next = null;
        } else if (this.size() == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.previous = null;
        }
        
        count--;
        return item;
    }
    
    public Item removeLast() {
        if (count == 0) throw new NoSuchElementException();
        
        Item item = last.item;
        
        if (this.size() == 2) {
            last = first;
            last.previous = null;
            first.next = null;
        } else if (this.size() == 1) {
            first = null;
            last = null;
        } else {
            last = last.previous;
            last.next = null;
        }

        count--;
        return item;
    }
    
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    
    private class DequeIterator implements Iterator<Item> {
        
        private Node current = first;
        private int iteratorCount = 0;
        
        public DequeIterator() {
            iteratorCount = size();
        }
        
        public boolean hasNext() {
            if (iteratorCount != size()) {
                throw new ConcurrentModificationException();
            }

            return (current != null);
        }
        
        public Item next() {
            if (iteratorCount != size()) {
                throw new ConcurrentModificationException();
            }
            
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            
            Item item = current.item;
            current = current.next;
            return item;
        }
        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }
}