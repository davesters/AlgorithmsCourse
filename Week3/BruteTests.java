import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import org.junit.runners.JUnit4;

public class BruteTests {
    
    @Test
    public void Brute_Reads_All_Ints_and_Has_Correct_Array() {
        int[] input = { 10, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 0, 0 };
        
        Brute brute = new Brute(input);
        
        assertTrue(brute.pointCount() == 10);
        assertEquals("(1, 1)", brute.getPoint(0).toString());
        assertEquals("(5, 5)", brute.getPoint(4).toString());
        assertEquals("(0, 0)", brute.getPoint(9).toString());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void Brute_Empty_Array_Throws_Exception() {
        int[] input = new int[0];
        
        Brute brute = new Brute(input);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void Brute_Incomplete_Array_Throws_Exception() {
        int[] input = { 2, 1, 1, 2 };
        
        Brute brute = new Brute(input);
    }
}