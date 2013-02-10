/*----------------------------------------------------------------
 *  Author:        David Corona
 *  Login:         davesters81@gmail.com
 *  Written:       02/10/2013
 *  Last updated:  02/10/2013
 *  
 *  Runs percolation loops and outputs the results
 * 
 *  % java Percolation Stats N T
 *  N: Size of percolation grid
 *  T: Number of times to run percolation loop.
 *
 *----------------------------------------------------------------*/

public class PercolationStats {
    
    private Percolation percolation;
    private int N, T;
    private double stdDevResult = 0;
    private double meanResult = 0;
    private double confidenceResult = 0;

    public PercolationStats(int n, int t) {
        if (n < 1 || t < 1) throw new IllegalArgumentException();

        N = n;
        T = t;
        
//        Stopwatch stopwatch = new Stopwatch();
        double[] results = runPercolation();
        
        stdDevResult = StdStats.stddev(results);
        meanResult = StdStats.mean(results);
        confidenceResult = (1.96 * stdDevResult) / Math.sqrt(T);
        
//        double elapsedTime = stopwatch.elapsedTime();
//        StdOut.println("Elapsed Time:\t\t\t= " + elapsedTime + " seconds");
//        StdOut.println("-------------------------------------------------");
    }
    
    private double[] runPercolation() {
        double[] results = new double[T];
        
        for (int x = 0; x < T; x++) {
            int openSites = 0;
            percolation = new Percolation(N);
            
            while (!percolation.percolates()) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                
                if (!percolation.isOpen(i, j)) {
                    percolation.open(i, j);
                    openSites++;
                }
            }
            results[x] = (double) openSites / (double) (N * N);
        }
        
        return results;
    }

    public double mean() {
        return meanResult;
    }

    public double stddev() {
        return stdDevResult;
    }

    public double confidenceLo() {
        return (meanResult - confidenceResult);
    }

    public double confidenceHi() {
        return (meanResult + confidenceResult);
    }

    public static void main(String[] args) {
        if (args.length != 2) throw new IllegalArgumentException();

        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);
        
        PercolationStats percolationStats = new PercolationStats(n, t);
        
        StdOut.println("mean\t\t\t\t=" + percolationStats.mean());
        StdOut.println("stddev\t\t\t\t=" + percolationStats.stddev());
        StdOut.println("95% confidence interval\t="
                           + percolationStats.confidenceLo() + ", "
                           + percolationStats.confidenceHi());
    }
}