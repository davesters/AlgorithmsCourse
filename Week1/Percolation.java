/*----------------------------------------------------------------
 *  Author:        David Corona
 *  Login:         davesters81@gmail.com
 *  Written:       02/10/2013
 *  Last updated:  02/10/2013
 *  
 *  Creates an NxN grid of sites. Allows you to open new sites and
 *  test if the grid percolates.
 *
 *----------------------------------------------------------------*/

public class Percolation {
    
    private int gridSize;
    private int virtualTopIndex;
    private int virtualBottomIndex = 0;
    private WeightedQuickUnionUF unionFind;
    private boolean[] sites;
    
    public Percolation(int n)
    {
        gridSize = n;
        virtualTopIndex = (gridSize * gridSize) + 1;
        sites = new boolean[virtualTopIndex + 1];
        
        unionFind = new WeightedQuickUnionUF(virtualTopIndex + 1);
        
        connectTopSites();
        connectBottomSites();
    }
    
    private void connectTopSites()
    {
        for (int x = 1; x <= gridSize; x++)
            unionFind.union(x, 0);
    }
    
    private void connectBottomSites()
    {
        for (int x = (virtualTopIndex - gridSize); x < virtualTopIndex; x++)
            unionFind.union(x, virtualTopIndex);
    }

    public void open(int i, int j)
    {
        validateCoordinates(i, j);

        int siteIndex = getSiteIndex(i, j);
        if (sites[siteIndex]) return;
        
        sites[siteIndex] = true;
        unionAdjacentSitesIfOpen(siteIndex, i, j);
    }
    
    private void validateCoordinates(int row, int column) {
        if (row < 1 || row > gridSize || column < 1 || column > gridSize) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int getSiteIndex(int row, int column)
    {
        if (row < 1 || row > gridSize || column < 1 || column > gridSize) return -1;
        return (((row - 1) * gridSize) + column);
    }

    private void unionAdjacentSitesIfOpen(int siteIndex, int row, int column)
    {        
        int leftSiteIndex = getSiteIndex(row, column - 1);
        int rightSiteIndex = getSiteIndex(row, column + 1);
        int topSiteIndex = getSiteIndex(row - 1, column);
        int bottomSiteIndex = getSiteIndex(row + 1, column);
        
        if (leftSiteIndex != -1 && sites[leftSiteIndex]) {
            unionFind.union(siteIndex, leftSiteIndex);
        }
        if (rightSiteIndex != -1 && sites[rightSiteIndex]) {
            unionFind.union(siteIndex, rightSiteIndex);
        }
        if (topSiteIndex != -1 && sites[topSiteIndex]) {
            unionFind.union(siteIndex, topSiteIndex);
        }
        if (bottomSiteIndex != -1 && sites[bottomSiteIndex]) {
            unionFind.union(siteIndex, bottomSiteIndex);
        }
    }
    
    public boolean isOpen(int i, int j)
    {
        validateCoordinates(i, j);      
        return sites[getSiteIndex(i, j)];
    }

    public boolean isFull(int i, int j)
    {
        validateCoordinates(i, j);
        return (!sites[getSiteIndex(i, j)]);
    }

    public boolean percolates()
    {
        return unionFind.connected(virtualBottomIndex, virtualTopIndex);
    }
}