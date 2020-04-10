import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Coordinates of Human Being
 * @author Pham Chi Thanh
 */
public class Coordinates {
    private double x;
    private double y;

    public Coordinates(){};
    public Coordinates(double x, double y)
    {
        this.x=x;
        this.y=y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    public Coordinates getCoordinates(String coordinates)
    {
        String a,b, t;
        double x1;
        double y1;
        t=coordinates.substring(1,coordinates.length()-1);
        StringTokenizer st = new StringTokenizer(t," , ");
        a=st.nextToken();
        b=st.nextToken();
        StringTokenizer st1 = new StringTokenizer(a,"=");
        a=st1.nextToken();
        x1=Double.parseDouble(st1.nextToken());
        StringTokenizer st2 = new StringTokenizer(b,"=");
        b=st2.nextToken();
        y1=Double.parseDouble(st2.nextToken());
        return new Coordinates(x1,y1);
    }
    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 &&
                Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    public String xml()
    {
        return "\"("+x+" , " + y+")\"";
    }
}
