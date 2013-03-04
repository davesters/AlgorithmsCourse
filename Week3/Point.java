/*************************************************************************
 * Name: David Corona
 * Email: davesters81@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER;

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
        this.SLOPE_ORDER = new SlopeComparator(this);
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
        
        double ys = (double) that.y - this.y;
        double xs = (double) that.x - this.x;
        
        if (ys > -0.01 && ys < 0.01) return 0.0;
        if (xs > -0.01 && xs < 0.01) return Double.POSITIVE_INFINITY;
        
        return (ys / xs);
    }

    public int compareTo(Point that) {
        if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
            return -1;
        } else if (this.y == that.y && this.x == that.x) {
            return 0;
        } else {
            return 1;
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    
    private class SlopeComparator implements Comparator<Point>
    {
        private Point p;
        
        public SlopeComparator(Point p) {
            this.p = p;
        }
        
        @Override
        public int compare(Point p1, Point p2) {
            if (p1.x == p2.x && p1.y == p2.y) return 0;

            double slope1 = p.slopeTo(p1);
            double slope2 = p.slopeTo(p2);
            
            if (slope1 < slope2) {
                return -1;
            } else if (slope1 > slope2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}