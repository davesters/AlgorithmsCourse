import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.JUnit4;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class PointTests {
    
    @Test
    public void Point_Should_Be_Less_Than() {
        Point p1 = new Point(1, 1);
        
        assertEquals(-1, p1.compareTo(new Point(1, 2)));
    }

    @Test
    public void Point_Should_Be_Greater_Than() {
        Point p1 = new Point(2, 2);
        
        assertEquals(1, p1.compareTo(new Point(1, 1)));
    }

    @Test
    public void Points_Should_Be_Equal() {
        Point p1 = new Point(1, 1);
        
        assertEquals(0, p1.compareTo(new Point(1, 1)));
    }
    
    @Test
    public void Slope_Should_Be_Two() {
        Point p1 = new Point(1, 1);
        
        assertEquals(2.0, p1.slopeTo(new Point(2, 3)), 0.01);
    }
    
    @Test
    public void Slope_Should_Be_0_On_Vertical_Lines() {
        Point p1 = new Point(1, 3);
        assertEquals(0.0, p1.slopeTo(new Point(3, 3)), 0.01);
    }

    @Test
    public void Slope_Should_Be_Negative_Infinity_On_Degenerate_Lines() {
        Point p1 = new Point(3, 3);
        
        assertEquals(Double.NEGATIVE_INFINITY, p1.slopeTo(new Point(3, 3)), 0.01);
    }

    @Test
    public void Slope_Should_Be_Positive_Infinity_On_Horizontal_Lines() {
        Point p1 = new Point(3, 1);
        
        assertEquals(Double.POSITIVE_INFINITY, p1.slopeTo(new Point(3, 3)), 0.01);
    }
    
    @Test
    public void Comparator_Equals_Points_Returns_Zero() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 3);
        Point p3 = new Point(1, 3);
        assertTrue(p1.SLOPE_ORDER.compare(p2, p3) == 0);
    }
    
    @Test
    public void Comparator_First_Point_Has_Greater_Slope_Returns_One() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 3);
        Point p3 = new Point(1, 3);
        assertTrue(p1.SLOPE_ORDER.compare(p2, p3) == -1.0);
    }

    @Test
    public void Comparator_Second_Point_Has_Greater_Slope_Returns_Negative() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, 3);
        Point p3 = new Point(3, 3);
        assertTrue(p1.SLOPE_ORDER.compare(p2, p3) == 1.0);
    }
}