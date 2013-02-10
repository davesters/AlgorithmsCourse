import org.junit.Test;
import org.junit.runners.JUnit4;

public class PercolationStatsTests {
    
    @Test(expected = IllegalArgumentException.class)
    public void Should_Throw_Invalid_Argument_Exception() {
        PercolationStats percolationStats = new PercolationStats(0, 0);
    }
    
    @Test
    public void Has_Mean_Result() {
        PercolationStats percolationStats = new PercolationStats(10, 100);
        
        org.junit.Assert.assertEquals("Doesn't contain mean result", true, percolationStats.mean() > 0);
    }

    @Test
    public void Has_StdDev_Result() {
        PercolationStats percolationStats = new PercolationStats(10, 100);
        
        org.junit.Assert.assertEquals("Doesn't contain stddev result", true, percolationStats.stddev() > 0);
    }

    @Test
    public void Has_Confidence_Results() {
        PercolationStats percolationStats = new PercolationStats(10, 100);
        
        org.junit.Assert.assertEquals("Doesn't contain confidenceLo result", true, percolationStats.confidenceLo() > 0);
        org.junit.Assert.assertEquals("Doesn't contain confidenceHi result", true, percolationStats.confidenceHi() > 0);
    }
}