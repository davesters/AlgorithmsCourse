import static org.junit.Assert.*;

import java.util.*;
import org.junit.*;
import org.junit.runners.JUnit4;

public class BoardTests {
    
    @Test
    public void Board_Returns_Correct_Dimension() {
        int[][] input = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
        
        Board board = new Board(input);
        
        assertEquals(3, board.dimension());
    }
    
    @Test
    public void Board_Returns_Correct_Twin() {
        int[][] input = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 }
        };
        
        Board board = new Board(input);
        Board twin = board.twin();
        
        assertEquals(4, twin.dimension());
        assertEquals("4\n2 1 3 4 \n5 6 7 8 \n9 10 11 12 \n13 14 15 0 \n", twin.toString());
    }

    @Test
    public void Board_Returns_Correct_Output() {
        int[][] input = {
            { 1, 2, 3, 4 },
            { 5, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 14, 15, 0 }
        };
        
        Board board = new Board(input);
        
        assertEquals("4\n1 2 3 4 \n5 6 7 8 \n9 10 11 12 \n13 14 15 0 \n", board.toString());
    }
    
    @Test
    public void Board_Equals_Similar_Board() {
        int[][] input = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
        
        Board board1 = new Board(input);
        Board board2 = new Board(input);
        
        assertTrue(board1.equals(board2));
    }
    
    @Test
    public void Board_Does_Not_Equal_Different_Board() {
        int[][] input = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };        
        Board board1 = new Board(input);

        int[][] input2 = {
            { 2, 1, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
        Board board2 = new Board(input2);
        
        assertTrue(board1.equals(board2) == false);
    }
    
    @Test
    public void Board_Returns_Correct_Hamming_Count() {
        int[][] input = {
            { 2, 1, 3, 4 },
            { 5, 6, 7, 8 },
            { 11, 10, 9, 12 },
            { 13, 14, 0, 15 }
        };
        
        Board board = new Board(input);
        
        assertEquals(5, board.hamming());
    }

    @Test
    public void Board_Returns_Correct_Manhattan_Count() {
        int[][] input = {
            { 2, 1, 3, 4 },
            { 14, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 5, 0, 15 }
        };
        
        Board board = new Board(input);
        
        assertEquals(9, board.manhattan());
    }

    @Test
    public void Board_Returns_Correct_Manhattan_Count_On_Tricky_Board() {
        int[][] input = {
            { 5, 8, 7 },
            { 1, 4, 6 },
            { 3, 0, 2 }
        };
        
        Board board = new Board(input);
        
        assertEquals(17, board.manhattan());
    }

    @Test
    public void Board_Returns_Correct_Manhattan_Count_On_Large_Board() {
        int[][] input = {
            { 74, 46, 52, 65,  6, 99, 29, 30, 94, 51 },
            { 91, 10, 81, 49, 76, 40, 42, 57, 70, 93 },
            { 33, 54,  8,  2, 73, 15, 45, 88, 17, 90 },
            { 79, 16, 89, 95, 69,  0, 23, 82, 78, 72 },
            { 48, 64, 80,  5, 55, 13, 27, 96, 77, 47 },
            { 63, 28, 71, 60, 62,  7, 22, 34, 31, 25 },
            { 84, 97, 26, 21,  3, 68, 92, 44, 39,  9 },
            { 36, 12,  4, 20, 83, 56, 19, 67, 24, 50 },
            {  1, 11, 43, 38, 14, 32, 66, 75, 53, 18 },
            { 59, 61, 37, 41, 85, 86, 87, 35, 58, 98 }
        };
    
        Board board = new Board(input);
        
        assertEquals(646, board.manhattan());
    }
    
    @Test
    public void Board_IsGoal_Returns_False_on_scrambled_board() {
        int[][] input = {
            { 2, 1, 3 },
            { 6, 5, 4 },
            { 7, 0, 8 }
        };
        
        Board board = new Board(input);
        assertTrue(board.isGoal() == false);
    }
    
    @Test
    public void Board_IsGoal_Returns_True_On_Ordered_board() {
        int[][] input = {
            { 1, 2, 3 },
            { 4, 5, 6 },
            { 7, 8, 0 }
        };
        
        Board board = new Board(input);
        assertTrue(board.isGoal());
    }
    
    @Test
    public void Board_Returns_Correct_Neighbors() {
        int[][] input = {
            { 2, 1, 3, 4 },
            { 14, 6, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 5, 0, 15 }
        };
        
        Board board = new Board(input);
        LinkedList<Board> neighbors = (LinkedList<Board>) board.neighbors();
        
        assertEquals(3, neighbors.size());
        assertEquals("4\n2 1 3 4 \n14 6 7 8 \n9 10 0 12 \n13 5 11 15 \n", neighbors.get(0).toString());
        assertEquals("4\n2 1 3 4 \n14 6 7 8 \n9 10 11 12 \n13 5 15 0 \n", neighbors.get(1).toString());
        assertEquals("4\n2 1 3 4 \n14 6 7 8 \n9 10 11 12 \n13 0 5 15 \n", neighbors.get(2).toString());

        input = new int[][] {
            { 2, 1, 3, 4 },
            { 14, 0, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 5, 6, 15 }
        };
        board = new Board(input);
        neighbors = (LinkedList<Board>) board.neighbors();

        assertEquals(4, neighbors.size());
        assertEquals("4\n2 0 3 4 \n14 1 7 8 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(0).toString());
        assertEquals("4\n2 1 3 4 \n14 7 0 8 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(1).toString());
        assertEquals("4\n2 1 3 4 \n14 10 7 8 \n9 0 11 12 \n13 5 6 15 \n", neighbors.get(2).toString());
        assertEquals("4\n2 1 3 4 \n0 14 7 8 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(3).toString());

        input = new int[][] {
            { 0, 1, 3, 4 },
            { 14, 2, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 5, 6, 15 }
        };
        board = new Board(input);
        neighbors = (LinkedList<Board>) board.neighbors();

        assertEquals(2, neighbors.size());
        assertEquals("4\n1 0 3 4 \n14 2 7 8 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(0).toString());
        assertEquals("4\n14 1 3 4 \n0 2 7 8 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(1).toString());
        
        input = new int[][] {
            { 15, 1, 3, 4 },
            { 14, 2, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 5, 6, 0 }
        };
        board = new Board(input);
        neighbors = (LinkedList<Board>) board.neighbors();

        assertEquals(2, neighbors.size());
        assertEquals("4\n15 1 3 4 \n14 2 7 8 \n9 10 11 0 \n13 5 6 12 \n", neighbors.get(0).toString());
        assertEquals("4\n15 1 3 4 \n14 2 7 8 \n9 10 11 12 \n13 5 0 6 \n", neighbors.get(1).toString());

        input = new int[][] {
            { 13, 1, 3, 4 },
            { 14, 2, 7, 8 },
            { 9, 10, 11, 12 },
            { 0, 5, 6, 15 }
        };
        board = new Board(input);
        neighbors = (LinkedList<Board>) board.neighbors();

        assertEquals(2, neighbors.size());
        assertEquals("4\n13 1 3 4 \n14 2 7 8 \n0 10 11 12 \n9 5 6 15 \n", neighbors.get(0).toString());
        assertEquals("4\n13 1 3 4 \n14 2 7 8 \n9 10 11 12 \n5 0 6 15 \n", neighbors.get(1).toString());

        input = new int[][] {
            { 4, 1, 3, 0 },
            { 14, 2, 7, 8 },
            { 9, 10, 11, 12 },
            { 13, 5, 6, 15 }
        };
        board = new Board(input);
        neighbors = (LinkedList<Board>) board.neighbors();

        assertEquals(2, neighbors.size());
        assertEquals("4\n4 1 3 8 \n14 2 7 0 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(0).toString());
        assertEquals("4\n4 1 0 3 \n14 2 7 8 \n9 10 11 12 \n13 5 6 15 \n", neighbors.get(1).toString());
    }
}