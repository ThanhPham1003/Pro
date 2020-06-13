package User;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class ConsoleReader {
    private Scanner s;
    private String user;
    private String username;
    private String password;
    //public InetSocketAddress is = new InetSocketAddress(1900);
    private SocketChannel ssChannel;
    private ClientAnswer clientAnswer;
    private Receiver receiver;
    public ConsoleReader(SocketChannel ssChannel,Receiver receiver)
    {
        this.ssChannel=ssChannel;
        this.receiver=receiver;
    };
    public void Interaction() throws IOException {
        boolean flag = true;
        while (flag)
        {
            s = new Scanner(System.in);
        System.out.println("Login your account or Register the new account?");
        user = s.nextLine().trim();
        if (user.toLowerCase().equals("login")) {
            System.out.println("Enter your Username: ");
            username = s.nextLine().trim();
            System.out.println("Enter your Password: ");
            password = s.nextLine().trim();
            clientAnswer = new ClientAnswer("LOGIN", username, password);
            Sender sd = new Sender(ssChannel);
            sd.SendCA(clientAnswer);
            flag = false;
        } else if (user.toLowerCase().equals("register")) {
            System.out.println("Enter your Username: ");
            username = s.nextLine().trim();
            System.out.println("Enter your Password: ");
            password = s.nextLine().trim();
            clientAnswer = new ClientAnswer("REGISTER", username, password);
            Sender sd = new Sender(ssChannel);
            sd.SendCA(clientAnswer);
            flag=false;
        } else System.out.println("You need to select answer \"Login\" or \"Register\" .");
    }
    }
    public void SendCommand() throws IOException {
        String command;
        Scanner commandReader = new Scanner(System.in);
        System.out.println("Choose one of the following commands: ");
        command = commandReader.nextLine().trim();
        if (command.equals("exit")) {
            System.exit(1);
        } else {
            Sender sd = new Sender(ssChannel);
            sd.Send(command,clientAnswer);
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
}
