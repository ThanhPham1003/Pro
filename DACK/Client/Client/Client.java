package Client;

import java.io.Serializable;

public class Client implements Serializable{
    private String username;
    private String password;
    private String command;
    public Client(String username, String password,String command)
    {
        this.username=username;
        this.password=password;
        this.command=command;
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
}
