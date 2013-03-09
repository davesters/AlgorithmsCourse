import java.util.LinkedList;

public class Solver {
    
    private MinPQ<SearchNode> queue = new MinPQ<SearchNode>();
    private MinPQ<SearchNode> twinQueue = new MinPQ<SearchNode>();
    private SearchNode current;
    private SearchNode twinCurrent;
    private int moves = 0;
    private boolean solvable = false;
    private LinkedList<Board> solutionList;
    
    public Solver(Board initial) {
        SearchNode node = new SearchNode(initial, moves);
        SearchNode twin = new SearchNode(initial.twin(), moves);
        
        node.setPrevious(null);
        twin.setPrevious(null);
        
        queue.insert(node);
        twinQueue.insert(twin);
        
        this.solve();
    }
    
    private void solve() {
        this.current = this.queue.delMin();
        this.twinCurrent = this.twinQueue.delMin();
        
        while (!this.current.isGoal() && !this.twinCurrent.isGoal()) {
            
            for (Board board : this.current.getBoard().neighbors()) {
                Board prevBoard = null;
                if (this.current.getPrevious() != null) {
                    prevBoard = this.current.getPrevious().getBoard();
                }
                
                if (!board.equals(prevBoard)) {
                    SearchNode node
                        = new SearchNode(board, this.current.getMoves() + 1);
                    node.setPrevious(this.current);
                    this.queue.insert(node);
                }
            }
            for (Board twinBoard : this.twinCurrent.getBoard().neighbors()) {
                Board prevBoard = null;
                if (this.twinCurrent.getPrevious() != null) {
                    prevBoard = this.twinCurrent.getPrevious().getBoard();
                }
                
                if (!twinBoard.equals(prevBoard)) {
                    SearchNode twinNode
                        = new SearchNode(twinBoard, twinCurrent.getMoves() + 1);
                    twinNode.setPrevious(this.twinCurrent);
                    this.twinQueue.insert(twinNode);
                }
            }
            
            this.current = this.queue.delMin();
            this.twinCurrent = this.twinQueue.delMin();
        }
        
        this.twinQueue = null;
        this.queue = null;
        this.twinCurrent = null;
        if (this.current.isGoal()) {
            this.solvable = true;
            this.solutionList = (LinkedList<Board>) this.solution();
        }
    }
    
    public boolean isSolvable() {
        return solvable;
    }
    
    public int moves() {
        if (!this.isSolvable()) return -1;
        
        return moves;
    }
    
    public Iterable<Board> solution() {
        if (!this.isSolvable()) return null;
        if (this.solutionList != null) return solutionList;
        
        LinkedList<Board> tempSolution = new LinkedList<Board>();
        boolean done = false;
        SearchNode node = this.current;
        moves = -1;
        
        while (!done) {
            tempSolution.addFirst(node.getBoard());
            node = node.previous;
            moves++;
            if (node == null) done = true;
        }
        
        return tempSolution;
    }
    
    private class SearchNode implements Comparable<SearchNode> {
        
        private int priority;
        private Board board;
        private SearchNode previous;
        private int moves;
        
        public SearchNode(Board board, int moves) {
            this.board = board;
            this.priority = board.manhattan() + moves;
        }
        
        public Board getBoard() {
            return this.board;
        }
        
        public boolean isGoal() {
            return this.board.isGoal();
        }
        
        public void setPrevious(SearchNode value) {
            this.previous = value;
        }
        
        public SearchNode getPrevious() {
            return this.previous;
        }
        
        public int getMoves() {
            return moves;
        }

        public int compareTo(SearchNode that) {
            if (this.priority < that.priority) {
                return -1;
            } else if (this.priority == that.priority) {
                return 0;
            } else {
                return 1;
            }
        }
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);
        
        Solver solver = new Solver(initial);
        
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves() + "\n");
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}