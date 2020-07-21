package Client;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSide {
    public static void main(String[] args) {
        int port = 1003;
        String username = null;
        String password = null;
        while (true)
        {
            try
            {
                Socket s = new Socket();
                s.connect(new InetSocketAddress(port));
                Receiver r = new Receiver(s);
                ConsoleReader cr = new ConsoleReader(s,r);
                cr.Interaction();
                new Thread(cr.getReceiver()).start();
                Thread.sleep(1000);
                System.out.println(cr.getReceiver().getFromServer());

            } catch (IOException | InterruptedException e) {
                System.out.println("Have error with main.");
            }
        }
    }
}
