import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.JUnit4;
import java.util.*;

public class DequeTests {
    
    @Test(expected = NullPointerException.class)
    public void Should_throw_exception_when_adding_null_at_front() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void Should_throw_exception_when_adding_null_at_end() {
        Deque<String> deque = new Deque<String>();
        
        deque.addLast(null);
    }

    @Test
    public void Should_return_correct_count() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addLast("World");
        
        assertEquals("Should contain 2 items", 2, deque.size());
    }
    
    @Test
    public void Should_be_empty() {
        Deque<String> deque = new Deque<String>();
        
        assertTrue("Should be empty", deque.isEmpty());
    }

    @Test
    public void Should_not_be_empty() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addFirst("World");
        
        assertFalse("Should not be empty", deque.isEmpty());
    }
    
    @Test
    public void Should_return_correct_items_from_front() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addFirst(", ");
        deque.addFirst("World");
        deque.addFirst("!");
        
        assertEquals("Should contain 4 items", 4, deque.size());
        assertEquals("Should return '!'", "!", deque.removeFirst());
        assertEquals("Should return 'World'", "World", deque.removeFirst());
        assertEquals("Should return ', '", ", ", deque.removeFirst());
        assertEquals("Should return 'Hello'", "Hello", deque.removeFirst());
        assertEquals("Should contain 0 items", 0, deque.size());
    }

    @Test
    public void Should_return_correct_items_from_end() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addFirst(", ");
        deque.addFirst("World");
        deque.addFirst("!");
        
        assertEquals("Should contain 4 items", 4, deque.size());
        assertEquals("Should return 'Hello'", "Hello", deque.removeLast());
        assertEquals("Should return ', '", ", ", deque.removeLast());
        assertEquals("Should return 'World'", "World", deque.removeLast());
        assertEquals("Should return '!'", "!", deque.removeLast());
        assertEquals("Should contain 0 items", 0, deque.size());
    }

    @Test
    public void Should_return_correct_items() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addFirst(", ");
        deque.addFirst("World");
        deque.addFirst("!");
        
        assertEquals("Should contain 4 items", 4, deque.size());
        assertEquals("Should return '!'", "!", deque.removeFirst());
        assertEquals("Should return 'Hello'", "Hello", deque.removeLast());
        assertEquals("Should return 'World'", "World", deque.removeFirst());
        assertEquals("Should return ', '", ", ", deque.removeLast());
        assertEquals("Should contain 0 items", 0, deque.size());
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Should_throw_exception_when_removing_first_from_empty_deque() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addFirst(", ");
        
        deque.removeFirst();
        deque.removeFirst();
        deque.removeFirst();
    }
    
    @Test(expected = NoSuchElementException.class)
    public void Should_throw_exception_when_removing_last_from_empty_deque() {
        Deque<String> deque = new Deque<String>();
        
        deque.addFirst("Hello");
        deque.addFirst(", ");
        
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void Iterator_remove_should_throw_exception() {
        Deque<String> deque = new Deque<String>();
        
        Iterator<String> iterator = deque.iterator();
        iterator.remove();
    }

    @Test
    public void Iterator_returns_false_on_hasNext() {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("Hello");
        deque.addFirst(", ");

        Iterator<String> iterator = deque.iterator();
        
        for (int x = 0; x < 2; x++) {
            iterator.next();
        }
        
        assertFalse("Should return false on hasNext()", iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void Iterator_throws_exception_on_next_when_done() {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("Hello");
        deque.addFirst(", ");

        Iterator<String> iterator = deque.iterator();
        
        for (int x = 0; x < 3; x++) {
            iterator.next();
        }
    }
    
    @Test(expected = ConcurrentModificationException .class)
    public void Iterator_throws_exception_when_deque_changed_on_next() {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("Hello");
        deque.addFirst(", ");

        Iterator<String> iterator = deque.iterator();
        
        deque.addFirst("World");
        iterator.next();
    }
    
    @Test
    public void Iterator_doesnt_throw_exception_when_created_after_changed() {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("Hello");
        deque.addFirst(", ");
        
        Iterator<String> iterator1 = deque.iterator();
        deque.addFirst("World");
        
        Iterator<String> iterator2 = deque.iterator();
        
        assertTrue("Should return true on hasNext()", iterator2.hasNext());
    }

    @Test(expected = ConcurrentModificationException .class)
    public void Iterator_throws_exception_when_deque_changed_on_hasNext() {
        Deque<String> deque = new Deque<String>();

        deque.addFirst("Hello");
        deque.addFirst(", ");
        
        Iterator<String> iterator1 = deque.iterator();
        deque.addFirst("World");
        
        Iterator<String> iterator2 = deque.iterator();
        
        assertTrue("Should return true on hasNext()", iterator2.hasNext());
        iterator1.hasNext();
    }
    
    @Test
    public void Deque_randomly_adds_and_removes_and_matches_real_list() {
        List<Integer> list = new LinkedList<Integer>();
        Deque<Integer> deque = new Deque<Integer>();
        Random generator = new Random();
        
        int toAdd, listItem = 0, dequeItem = 0;
        
        for (int i = 0; i < 50000; i++) {
            if (generator.nextBoolean()) {
                toAdd = generator.nextInt();
                if (generator.nextBoolean()) {
                    
                    list.add(0, toAdd);
                    deque.addFirst(toAdd);
                } else {
                    list.add(toAdd);
                    deque.addLast(toAdd);
                }
            } else {
                if (!deque.isEmpty()) {
                    if (generator.nextBoolean()) {
                        listItem = list.remove(0);
                        dequeItem = deque.removeFirst();
                        
                    } else {
                        listItem = list.remove(list.size() - 1);
                        dequeItem = deque.removeLast();
                    }
                }
            }
            assertEquals(deque + "\n" + list, listItem, dequeItem);
        }
    }
}