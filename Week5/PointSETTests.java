import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import org.junit.runners.JUnit4;

public class PointSETTests {
    
    @Test
    public void PointSET_Should_Contain_Point_After_Adding() {
        PointSET ps = new PointSET();
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.25, 0.25);
        
        ps.insert(p1);
        ps.insert(p2);
        assertTrue(ps.contains(p1));
        assertTrue(ps.contains(p2));
    }
    
    @Test
    public void PointSET_Should_Return_Correct_Size() {
        PointSET ps = new PointSET();
        Point2D p1 = new Point2D(0.5, 0.5);
        Point2D p2 = new Point2D(0.25, 0.25);
        
        assertEquals(0, ps.size());
        ps.insert(p1);
        ps.insert(p2);
        assertEquals(2, ps.size());
    }
    
    @Test
    public void PointSET_Returns_IsEmpty_True() {
        PointSET ps = new PointSET();
        assertEquals(true, ps.isEmpty());
    }

    @Test
    public void PointSET_Returns_IsEmpty_False() {
        PointSET ps = new PointSET();
        ps.insert(new Point2D(0.5, 0.5));
        
        assertEquals(false, ps.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void PointSET_Throws_Exception_On_Null_Insert() {
        PointSET ps = new PointSET();
        
        ps.insert(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PointSET_Throws_Exception_On_Null_Contains() {
        PointSET ps = new PointSET();
        
        ps.contains(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PointSET_Throws_Exception_On_Null_Range() {
        PointSET ps = new PointSET();
        
        ps.range(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void PointSET_Throws_Exception_On_Null_Nearest() {
        PointSET ps = new PointSET();
        
        ps.nearest(null);
    }
    
    @Test
    public void PointSET_Returns_Correct_Result_for_Range() {
        PointSET ps = new PointSET();
        
        ps.insert(new Point2D(0.7, 0.2));
        ps.insert(new Point2D(0.5, 0.4));
        ps.insert(new Point2D(0.2, 0.3));
        ps.insert(new Point2D(0.4, 0.7));
        ps.insert(new Point2D(0.9, 0.6));
        
        LinkedList<Point2D> points = (LinkedList<Point2D>) ps.range(new RectHV(0.1, 0.2, 0.55, 0.5));
        
        assertEquals(2, points.size());
        assertTrue(points.contains(new Point2D(0.2, 0.3)));
        assertTrue(points.contains(new Point2D(0.5, 0.4)));
    }
}