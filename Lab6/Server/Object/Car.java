package Object;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 * Car of Human Being
 * @author Pham Chi Thanh
 */
public class Car implements Serializable {
    private String name;
    private boolean cool;
    public Car(){};
    public Car(String name,boolean cool)
    {
        this.name=name;
        this.cool=cool;
    }

    public String getName() {
        return name;
    }
    public boolean getCool()
    {
        return cool;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setCool(boolean cool) {
        this.cool = cool;
    }

    public boolean isCool() {
        return cool;
    }


    public Car getCar(String car)
    {
        String a,b, t;
        String name1;
        boolean cool1;
        t=car.substring(1,car.length()-1);
        StringTokenizer st = new StringTokenizer(t," , ");
        a=st.nextToken();
        b=st.nextToken();
        StringTokenizer st1 = new StringTokenizer(a,"=");
        a=st1.nextToken();
        name1=st1.nextToken();
        StringTokenizer st2 = new StringTokenizer(b,"=");
        b=st2.nextToken();
        cool1=Boolean.parseBoolean(st2.nextToken());
        return new Car(name1,cool1);
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return cool == car.cool &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cool);
    }
    public String xml()
    {
        return "\"("+name+" , " + cool+")\"";
    }
}
