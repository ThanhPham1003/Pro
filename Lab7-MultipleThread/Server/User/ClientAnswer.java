package User;
import Object.*;
import java.io.Serializable;

public class ClientAnswer implements Serializable {

    private String command;
    private HumanBeing person;
    private String key;
    private Double speed;
    private Mood m;
    private String username;
    private String password;
    public ClientAnswer(String username,String password)
    {
        this.username=username;
        this.password=password;
    }
    public ClientAnswer(String command, String username,String password)
    {
        this.command=command;
        this.username=username;
        this.password=password;
    }
    public ClientAnswer(String command, HumanBeing person, String username, String password)
    {
        this.command = command;
        this.person=person;
        this.username=username;
        this.password=password;
    }
    public ClientAnswer(String command, String key, String username, String password)
    {
        this.command = command;
        this.key=key;
        this.username=username;
        this.password=password;
    }
    public ClientAnswer(String command, Double speed, String username, String password)
    {
        this.command = command;
        this.speed=speed;
        this.username=username;
        this.password=password;
    }
    public ClientAnswer(String command, Mood m, String username, String password)
    {
        this.command = command;
        this.m=m;
        this.username=username;
        this.password=password;
    }
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getCommand() {
        return command;
    }

    public HumanBeing getPerson() {
        return person;
    }

    public Double getSpeed() {
        return speed;
    }

    public String getKey() {
        return key;
    }

    public Mood getM() {
        return m;
    }
}
