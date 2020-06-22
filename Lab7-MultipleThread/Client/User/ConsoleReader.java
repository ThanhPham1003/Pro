package User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ConsoleReader {
    private Scanner s;
    private String user;
    private String username;
    private String password;
    //public InetSocketAddress is = new InetSocketAddress(1900);
    private Socket ss;
    private ClientAnswer clientAnswer;
    private Receiver receiver;
    public ConsoleReader(Socket s, Receiver receiver)
    {
        this.ss=s;
        this.receiver=receiver;
    };
    public void Interaction() throws IOException {
        boolean flag = true;
        try {

            while (flag) {
                s = new Scanner(System.in);
                System.out.println("Login your account or Register the new account?");
                user = s.nextLine().trim();
                if (user.toLowerCase().equals("login")) {
                    System.out.println("Enter your Username: ");
                    username = s.nextLine().trim();
                    System.out.println("Enter your Password: ");
                    password = s.nextLine().trim();
                    clientAnswer = new ClientAnswer("LOGIN", username, password);
                    Sender sd = new Sender(ss);
                    sd.SendCA(clientAnswer);
                    flag = false;
                } else if (user.toLowerCase().equals("register")) {
                    System.out.println("Enter your Username: ");
                    username = s.nextLine().trim();
                    System.out.println("Enter your Password: ");
                    password = s.nextLine().trim();
                    clientAnswer = new ClientAnswer("REGISTER", username, password);
                    Sender sd = new Sender(ss);
                    sd.SendCA(clientAnswer);
                    flag = false;
                } else System.out.println("You need to select answer \"Login\" or \"Register\" .");
            }
        }catch (SocketException e)
        {
            System.out.println("Connection reset by peer: socket write error.");
        }
    }
    public void SendCommand(ClientAnswer clientAnswer) throws IOException {
        String command;
        Scanner commandReader = new Scanner(System.in);
        System.out.println("Choose one of the following commands: ");
        command = commandReader.nextLine().trim();
        if (command.equals("exit")) {
            System.exit(1);
        } else {
            try {
                Sender sd = new Sender(ss);
                sd.Send(command, clientAnswer);
            } catch (SocketException e) {
                System.out.println("Connection reset by peer: socket write error.");
            }
        }
    }

    public Receiver getReceiver() {
        return receiver;
    }
    /*public String ReceiveAnswer() throws InterruptedException {
        new Thread(rc).start();
        Thread.sleep(1000);
       return rc.getFromServer();
    }*/

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
