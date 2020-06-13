package User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;


public class ClientSide {
    public static void main(String[] args) throws IOException, InterruptedException {
        InetSocketAddress is = new InetSocketAddress(1911);
        try {
            SocketChannel ssChannel = SocketChannel.open(is);
            Receiver r = new Receiver(ssChannel);
            ConsoleReader c = new ConsoleReader(ssChannel, r);
            c.Interaction();
            new Thread(c.getReceiver()).start();
            Thread.sleep(1000);
            System.out.println(c.getReceiver().getFromServer());
            if (c.getReceiver().getFromServer().contains("Connected")) {
                while (true) {
                    c.SendCommand();
                    new Thread(c.getReceiver()).start();
                    Thread.sleep(1000);
                    System.out.println(c.getReceiver().getFromServer());
                }
            }
        }catch (ConnectException e)
        {
            System.out.println("Error connection between Client and Server: " +e.getMessage());
        }



       /* InetSocketAddress is = new InetSocketAddress(1900);

        ClientAnswer ca = new ClientAnswer("LOGIN","sf","fsd");

        Sender s = new Sender(is);
        s.SendCA(ca);
        Receiver r = new Receiver(is);
        new Thread(r).start();
        Thread.sleep(1000);
        if(r.getFromServer().contains("Connected"))
        {
            s.Send("insert_key",ca);
        }
      //  System.out.println(r.getFromServer());

        //s.SendCommand("sfa");
      /* ConsoleReader c = new ConsoleReader();
        boolean flag = false;
        while (!flag)
        {
            String answer;
            c.Interaction();
            answer = c.ReceiveAnswer();
            if(answer.toLowerCase().contains("connected")) flag = true;
            System.out.println(answer);

        }
        while (true){
            try{
                c.SendCommand();
                System.out.println(c.ReceiveAnswer());

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }*/

    }
}
