package Client;
import javax.imageio.IIOException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleReader {
    private Scanner s;
    private String user;
    private String username;
    private String password;
    private Socket ss;
    private Receiver receiver;
    public ConsoleReader(Socket s, Receiver receiver)
    {
        this.ss=s;
        this.receiver=receiver;
    };
    public void Interaction() throws IOException
    {
        boolean flag = true;
        try{
            while (flag)
            {
                s = new Scanner(System.in);
                System.out.println("Login your account or Register the new account?");
                user = s.nextLine().trim();
                if(user.toLowerCase().equals("login")){
                    System.out.println("Enter your username: ");
                    username = s.nextLine().trim();
                    System.out.println("Enter your password: ");
                    password= s.nextLine().trim();
                    Client client = new Client(username,password,"Login");
                    Sender sd = new Sender(ss);
                    sd.sendClient(client);
                    flag = false;
                }
                else if(user.toLowerCase().equals("register")){
                    System.out.println("Enter your username: ");
                    username = s.nextLine().trim();
                    System.out.println("Enter your password: ");
                    password= s.nextLine().trim();
                    Client client = new Client(username,password,"Register");
                    Sender sd = new Sender(ss);
                    sd.sendClient(client);
                    flag = false;
                }else System.out.println("You need to select answer \"Login\" or \"Register\" .");
            }
        }catch (IOException e)
        {
            System.err.println("Have error in processing send request.");
        }
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
