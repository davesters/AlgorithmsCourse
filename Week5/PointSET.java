import java.util.LinkedList;
    
public class PointSET {
    
    private SET<Point2D> pointSet;
    
    public PointSET() {
        pointSet = new SET<Point2D>();
    }
    
    public boolean isEmpty() {
        return pointSet.isEmpty();
    }
    
    public int size() {
        return pointSet.size();
    }
    
    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        
        pointSet.add(p);
    }
    
    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        
        return pointSet.contains(p);
    }
    
    public void draw() {
        
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        
        LinkedList<Point2D> points = new LinkedList<Point2D>();
        return points;
    }
    
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        
        return null;
    }
}