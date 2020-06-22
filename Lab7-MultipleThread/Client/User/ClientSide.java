package User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;


public class ClientSide {
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length<1)
        {
            System.out.println("Please enter the port at the command prompt.");
            System.exit(0);
        }
        int port = 0;
        try
        {
            port= Integer.parseInt(args[0]);
        }catch (NumberFormatException e)
        {
            System.out.println("Port should be a number.");
            System.exit(0);
        }
        boolean flag = false;
        String user=null;
        String password=null;

        while (true) {
            try {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(port));
                Receiver r = new Receiver(s);
                ConsoleReader c = new ConsoleReader(s, r);
                if (!flag) {
                    c.Interaction();
                    new Thread(c.getReceiver()).start();
                    Thread.sleep(1000);
                    System.out.println(c.getReceiver().getFromServer());
                    if (c.getReceiver().getFromServer().contains("Connected")) {
                        flag = true;
                        user=c.getUsername();
                        password=c.getPassword();
                    }
                } else {
                    ClientAnswer clientAnswer = new ClientAnswer(user,password);
                    c.SendCommand(clientAnswer);
                    new Thread(c.getReceiver()).start();
                    Thread.sleep(1000);
                    System.out.println(c.getReceiver().getFromServer());
                }
            } catch (ConnectException e) {
                System.out.println("NO signal from Server.");
                System.exit(0);
            }
        }
    }
}

       /* InetSocketAddress is = new InetSocketAddress(1911);
        try {
            SocketChannel ssChannel = SocketChannel.open(is);
            Receiver r = new Receiver(ssChannel);
            ConsoleReader c = new ConsoleReader(ssChannel, r);
            while (true) {
                c.Interaction();
                new Thread(c.getReceiver()).start();
                Thread.sleep(1000);
                System.out.println(c.getReceiver().getFromServer());
                if (c.getReceiver().getFromServer().contains("Connected")) {
                    break;
                }

            }
                while (true) {
                    c.SendCommand();
                    new Thread(c.getReceiver()).start();
                    Thread.sleep(1000);
                    System.out.println(c.getReceiver().getFromServer());
                }
        }catch (ConnectException e)
        {
            System.out.println("NO signal from Server.");
        }

    }*/
