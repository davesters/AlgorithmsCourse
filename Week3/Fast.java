import java.util.Arrays;
import java.util.LinkedList;

public class Fast {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        
        In reader = new In((String) args[0]);
        
        int N = reader.readInt();
        Point[] points = new Point[N];
        Point[] pointsOrdered = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = reader.readInt();
            int y = reader.readInt();
            points[i] = new Point(x, y);
            pointsOrdered[i] = new Point(x, y);
        }
        reader.close();
        
        run(points, pointsOrdered);
    }

    private static void run(Point[] points, Point[] pointsOrig) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        Arrays.sort(points);
        
        for (int a = 0; a < points.length; a++) {
            pointsOrig[a].draw();
            Point currPoint = pointsOrig[a];

            Arrays.sort(points, currPoint.SLOPE_ORDER);
            
            LinkedList<Point> pointsToPrint = new LinkedList<Point>();
            boolean first = true;
            int count = 1;
            
            for (int b = 0; b < points.length - 1; b++) {
                if (currPoint.compareTo(points[b]) == 0) continue;

                if (((Double) currPoint.slopeTo(points[b]))
                        .equals(currPoint.slopeTo(points[b + 1]))) {
                    count++;

                    if (first) {
                        count++;
                        pointsToPrint.add(points[b]);
                        first = false;
                    }
                    pointsToPrint.add(points[b + 1]);
                } else {
                    if (count >= 4) {
                        printLine(pointsToPrint, currPoint);
                        count = 1;
                        break;
                    }
                    count = 1;
                    pointsToPrint = new LinkedList<Point>();
                    first = true;
                }
            }
            if (count >= 4) printLine(pointsToPrint, currPoint);
        }
    }
    
    private static void printLine(LinkedList<Point> points, Point currPoint) {
        points.add(currPoint);
        Point[] tempPoints = points.toArray(new Point[0]);
        Arrays.sort(tempPoints);
        
        if (currPoint.compareTo(tempPoints[0]) != 0) return;
        
        StdOut.print(tempPoints[0].toString());
        for (int x = 1; x < tempPoints.length; x++) {
            StdOut.print(" -> " + tempPoints[x].toString());
        }
        StdOut.println();
        
        tempPoints[0].drawTo(tempPoints[tempPoints.length - 1]);
    }
}