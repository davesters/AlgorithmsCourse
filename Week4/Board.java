import java.util.LinkedList;

public class Board {
    
    private int dimension = 0;
    private int manhattan = 0;
    private int hamming = 0;
    private int[] tiles;
    
    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        this.tiles = getTilesFromBoard(blocks);
    }
    
    private int getTileIndex(int row, int column) {
        if (row >= this.dimension() || column >= this.dimension()) return -1;
        if (row < 0 || column < 0) return -1;
        
        return ((row) * this.dimension()) + column;
    }
    
    private int[] getTilesFromBoard(int[][] board) {
        int[] tempTiles = new int[board.length * board.length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                tempTiles[this.getTileIndex(i, j)] = board[i][j];
            }
        }
        
        return tempTiles;
    }
    
    private int[][] getBoard() {
        int[][] tempBoard = new int[this.dimension()][this.dimension()];
        
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                tempBoard[i][j] = this.tiles[this.getTileIndex(i, j)];
            }
        }
        
        return tempBoard;
    }
    
    private int getTileIndexByValue(int tile) {
        for (int x = 0; x < this.tiles.length; x++) {
            if (this.tiles[x] == tile) return x;
        }
        return -1;
    }
    
    private int[] getBoardIndexFromTileIndex(int tileIndex) {
        int[] index = new int[2];
        
        index[0] = (tileIndex / this.dimension());
        index[1] = (tileIndex % this.dimension());
        return index;
    }
    
    public int dimension() {
        return dimension;
    }
    
    public int hamming() {
        if (this.hamming == 0) {
            int hammingCount = 0;
            
            for (int x = 0; x < this.tiles.length; x++) {
                if (this.tiles[x] == 0) continue;
                
                if (this.tiles[x] != (x + 1))  hammingCount++;
            }
            
            this.hamming = hammingCount;
        }
        
        return this.hamming;
    }
    
    public int manhattan() {
        if (this.manhattan == 0) {
            int manhattanCount = 0;
            int diff = 0;
            
            for (int x = 0; x < this.tiles.length; x++) {
                if (this.tiles[x] == 0) continue;

                int tileShouldBe = x + 1;
                
                if (this.tiles[x] != tileShouldBe) {
                    int[] currIndex = this.getBoardIndexFromTileIndex(x);
                    int[] newIndex =
                        this.getBoardIndexFromTileIndex(this.tiles[x] - 1);
                    
                    manhattanCount += Math.abs(currIndex[0] - newIndex[0]);
                    manhattanCount += Math.abs(currIndex[1] - newIndex[1]);
                }
            }
            
            this.manhattan = manhattanCount;
        }
        
        return this.manhattan;
    }
    
    public boolean isGoal() {        
        for (int x = 0; x < this.tiles.length; x++) {
            if (this.tiles[x] == 0) continue;
            if (this.tiles[x] != (x + 1)) return false;
        }
        
        return true;
    }
    
    public Board twin() {
        int[][] tempTiles = this.getBoard();
        
        if (this.tiles[1] == 0 || this.tiles[0] == 0) {
            tempTiles[1][0] = this.tiles[1 + this.dimension()];
            tempTiles[1][1] = this.tiles[this.dimension()];
        } else {
            tempTiles[0][0] = this.tiles[1];
            tempTiles[0][1] = this.tiles[0];
        }
        
        return new Board(tempTiles);
    }
    
    public boolean equals(Object y) {
        if (y == null) return false;
        
        return (this.toString().equals(y.toString()));
    }
    
    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<Board>();
        int blankTileIndex = this.getTileIndexByValue(0);
        int[] blankIndex = this.getBoardIndexFromTileIndex(blankTileIndex);
        int[] moves = { -this.dimension(), 1, this.dimension(), -1 };

        if (blankIndex[0] > 0) { // Top
            neighbors.add(this.getNeighborBoard(
                                                blankTileIndex,
                                                blankIndex,
                                                blankTileIndex + moves[0]));
        }
        if (blankIndex[1] < (this.dimension() - 1)) { // Right
            neighbors.add(this.getNeighborBoard(
                                                blankTileIndex,
                                                blankIndex,
                                                blankTileIndex + moves[1]));
        }
        if (blankIndex[0] < (this.dimension() - 1)) { // Bottom
            neighbors.add(this.getNeighborBoard(
                                                blankTileIndex,
                                                blankIndex,
                                                blankTileIndex + moves[2]));
        }
        if (blankIndex[1] > 0) { // Left
            neighbors.add(this.getNeighborBoard(
                                                blankTileIndex,
                                                blankIndex,
                                                blankTileIndex + moves[3]));
        }
        
        return neighbors;
    }
    
    private Board getNeighborBoard(int blankTileIndex,
                                   int[] blankIndex,
                                   int swapTileIndex) {
        int[][] tempBoard = this.getBoard();
        int[] swapIndex = this.getBoardIndexFromTileIndex(swapTileIndex);
        
        tempBoard[blankIndex[0]][blankIndex[1]] = this.tiles[swapTileIndex];
        tempBoard[swapIndex[0]][swapIndex[1]] = this.tiles[blankTileIndex];
        
        return new Board(tempBoard);
    }
    
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append(String.format("%d\n", this.dimension()));
        
        for (int x = 0; x < this.dimension(); x++) {
            
            for (int y = 0; y < this.dimension(); y++) {
                output.append(
                    String.format("%d ",
                                  this.tiles[this.getTileIndex(x, y)]));
            }
            
            output.append("\n");
        }
        
        return output.toString();
    }
}