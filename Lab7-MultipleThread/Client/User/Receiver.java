package User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SocketChannel;

public class Receiver implements Runnable, Serializable {
    private InetSocketAddress is;
    private Socket s;
    private String fromServer;
    public Receiver(Socket s){
        this.s=s;
    }
    /**
     * Run to receive data from server.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            fromServer = (String)ois.readObject();
            //System.out.println(fromServer);
        }catch (IOException e)
        {
            System.out.println("Data tranfer error.");
        }catch (ClassNotFoundException e)
        {
            System.out.println("Class doesn't exist.");
        }
    }

   public String getFromServer() {
    return fromServer;
  }
}
