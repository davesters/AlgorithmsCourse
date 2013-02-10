import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runners.JUnit4;

public class PercolationTests {
    
    private Percolation percolation;
    
    @Before
    public void setUp() {
        percolation = new Percolation(10);
    }
    
    @Test
    public void Site_Should_Be_Closed_On_New_Grid() {
        assertEquals("Site should be closed.", true, percolation.isFull(1, 1));
    }
    
    @Test
    public void Site_Should_Be_Open_When_Opened() {
        percolation.open(1, 1);
        
        assertEquals("Site should be open.", true, percolation.isOpen(1, 1));
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void Should_Throw_Exception_When_Invalid_Site() {
        percolation.open(0, 1);
    }
    
    @Test
    public void Grid_Should_Percolate_When_Column_Opened() {
        for (int x = 1; x <= 10; x++) {
            percolation.open(x, 5);
        }
        
        assertEquals("Should percolate.", true, percolation.percolates());
    }

    @Test
    public void Grid_Should_Not_Percolate() {
        for (int x = 1; x <= 10; x+= 2) {
            percolation.open(x, 5);
        }
        
        assertEquals("Should percolate.", false, percolation.percolates());
    }
}
