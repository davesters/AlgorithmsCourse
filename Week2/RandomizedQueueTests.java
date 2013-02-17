import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.JUnit4;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class RandomizedQueueTests {
    
    @Test(expected = NullPointerException.class)
    public void Should_throw_exception_when_enqueueing_null() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.enqueue(null);
    }

    @Test
    public void Should_return_correct_count() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        
        assertEquals("Should contain 2 items", 2, rndqueue.size());
    }
    
    @Test
    public void Should_be_empty() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        assertTrue("Should be empty", rndqueue.isEmpty());
    }

    @Test
    public void Should_not_be_empty() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        
        assertFalse("Should not be empty", rndqueue.isEmpty());
    }
    
    @Test
    public void Should_return_sample() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        
        assertEquals("Should contain 2 items", 2, rndqueue.size());
        assertTrue("Should return sample string", (rndqueue.sample() instanceof String));
        assertEquals("Should contain 2 items", 2, rndqueue.size());
    }

    @Test
    public void Should_dequeue_items() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        
        assertEquals("Should contain 2 items", 2, rndqueue.size());
        assertTrue("Should return sample string", (rndqueue.dequeue() instanceof String));
        assertEquals("Should contain 1 items", 1, rndqueue.size());
        
        rndqueue.dequeue();
        assertEquals("Should contain 0 items", 0, rndqueue.size());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Should_throw_exception_when_sampling_empty_queue() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.sample();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Should_throw_exception_when_dequeueing_empty_queue() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.dequeue();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void Iterator_remove_should_throw_exception() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        Iterator<String> iterator = rndqueue.iterator();
        iterator.remove();
    }

    @Test
    public void Iterator_returns_false_on_hasNext() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();

        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");

        Iterator<String> iterator = rndqueue.iterator();
        
        iterator.next();
        iterator.next();
        
        assertFalse("Should return false on hasNext()", iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void Iterator_throws_exception_on_next_when_done() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();

        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");

        Iterator<String> iterator = rndqueue.iterator();
        
        for (int x = 0; x < 3; x++) {
            iterator.next();
        }
    }
    
    @Test(expected = ConcurrentModificationException .class)
    public void Iterator_throws_exception_when_queue_changed_on_next() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();

        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");

        Iterator<String> iterator = rndqueue.iterator();
        
        rndqueue.enqueue("baz");
        iterator.next();
    }
    
    @Test
    public void Iterator_doesnt_throw_exception_when_created_after_changed() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();

        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        
        Iterator<String> iterator1 = rndqueue.iterator();
        rndqueue.enqueue("baz");
        
        Iterator<String> iterator2 = rndqueue.iterator();
        
        assertTrue("Should return true on hasNext()", iterator2.hasNext());
    }

    @Test(expected = ConcurrentModificationException .class)
    public void Iterator_throws_exception_when_queue_changed_on_hasNext() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();

        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        
        Iterator<String> iterator1 = rndqueue.iterator();
        rndqueue.enqueue("baz");
        
        Iterator<String> iterator2 = rndqueue.iterator();
        
        assertTrue("Should return true on hasNext()", iterator2.hasNext());
        iterator1.hasNext();
    }
    
    @Test
    public void Iterator_returns_different_results_from_second_iterator() {
        RandomizedQueue<String> rndqueue = new RandomizedQueue<String>();
        
        rndqueue.enqueue("foo");
        rndqueue.enqueue("bar");
        rndqueue.enqueue("baz");
        rndqueue.enqueue("foo2");
        rndqueue.enqueue("bar2");
        rndqueue.enqueue("baz2");
        rndqueue.enqueue("foo3");
        rndqueue.enqueue("bar3");
        rndqueue.enqueue("baz3");
        rndqueue.enqueue("foo4");
        rndqueue.enqueue("bar4");
        rndqueue.enqueue("baz4");
        rndqueue.enqueue("foo5");
        rndqueue.enqueue("bar5");
        rndqueue.enqueue("baz5");
        
        Iterator<String> iterator1 = rndqueue.iterator();
        Iterator<String> iterator2 = rndqueue.iterator();
        String[] results1 = new String[rndqueue.size()];
        String[] results2 = new String[rndqueue.size()];
        
        for (int x = 0; x < rndqueue.size(); x++) {
            results1[x] = iterator1.next();
        }
        for (int y = 0; y < rndqueue.size(); y++) {
            results2[y] = iterator2.next();
        }
        
        boolean matches = true;
        for (int z = 0; z < rndqueue.size(); z++) {
            if (results1[z] != results2[z]) {
                matches = false;
                break;
            }
        }
        
        assertFalse("Iterator results should not match", matches);
    }
}