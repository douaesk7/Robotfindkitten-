
//import java.util.*;
public class Point {
    //ATTRIBUTS
    private final int x, y;
    //CONSTRUCTEUR
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //METHODES
    public boolean egal(int x, int y) {
        return x == this.x && y == this.y;
    }
    
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}



