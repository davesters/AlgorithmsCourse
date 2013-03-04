import java.util.Arrays;

public class Brute {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException();
        }
        
        In reader = new In((String) args[0]);
        
        int N = reader.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point(reader.readInt(), reader.readInt());
        }
        reader.close();
        
        run(points);
    }

    private static void run(Point[] points) {        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        
        for (int a = 0; a < points.length - 3; a++) {
            
            for (int b = a + 1; b < points.length - 2; b++) {
                points[a].draw();
                
                for (int c = b + 1; c < points.length - 1; c++) {
                    if (!((Double) points[a].slopeTo(points[b]))
                            .equals(points[a].slopeTo(points[c]))) {
                        continue;
                    }
                    
                    for (int d = c + 1; d < points.length; d++) {
                        
                        if (((Double) points[a].slopeTo(points[c]))
                                .equals(points[a].slopeTo(points[d]))) {
                            printLine(
                                      points[a],
                                      points[b],
                                      points[c],
                                      points[d]);
                        }
                    }
                }
            }
        }
    }
    
    private static void printLine(Point p1, Point p2, Point p3, Point p4) {
        Point[] tempPoints = { p1, p2, p3, p4 };
        Arrays.sort(tempPoints);
        
        StdOut.printf(
                      "%s -> %s -> %s -> %s",
                      tempPoints[0].toString(),
                      tempPoints[1].toString(),
                      tempPoints[2].toString(),
                      tempPoints[3].toString());
        StdOut.println();
        
        tempPoints[0].drawTo(tempPoints[3]);
    }
}